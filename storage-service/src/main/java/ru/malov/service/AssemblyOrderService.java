package ru.malov.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.malov.messaging.RabbitConfig;
import ru.malov.messaging.dto.OrderApprovalResultEvent;
import ru.malov.persistence.entity.assembly.AssemblyOrderEntity;
import ru.malov.persistence.repository.AssemblyOrderJpaRepository;
import ru.malov.web.dto.request.AssemblyOrderCreateRequestDto;
import ru.malov.web.dto.request.AssemblyOrderUpdateRequestDto;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AssemblyOrderService {

    private final AssemblyOrderJpaRepository repository;
    private final RabbitTemplate rabbitTemplate;
    private final ObjectMapper objectMapper;

    public AssemblyOrderEntity create(AssemblyOrderCreateRequestDto dto) {
        AssemblyOrderEntity entity = new AssemblyOrderEntity();
        entity.setSourceOrderId(dto.getSourceOrderId());
        entity.setStatus(dto.getStatus() == null ? "CREATED" : dto.getStatus());
        entity.setSourceOrderType(dto.getSourceOrderType());
        entity.setTraceId(dto.getTraceId());
        return repository.save(entity);
    }

    public List<AssemblyOrderEntity> getAll() {
        return repository.findAll();
    }

    public AssemblyOrderEntity getById(UUID id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Assembly order not found"));
    }

    public AssemblyOrderEntity update(UUID id, AssemblyOrderUpdateRequestDto dto) {
        AssemblyOrderEntity entity = getById(id);

        if (dto.getStatus() != null) {
            entity.setStatus(dto.getStatus());
        }

        if (dto.getRemoved() != null) {
            entity.setRemoved(dto.getRemoved());
        }

        return repository.save(entity);
    }

    @Transactional
    public AssemblyOrderEntity approve(UUID id) {
        AssemblyOrderEntity entity = getById(id);
        entity.setStatus("ASSEMBLED");
        AssemblyOrderEntity saved = repository.save(entity);
        publishResult(saved, "APPROVED");
        return saved;
    }

    @Transactional
    public AssemblyOrderEntity reject(UUID id) {
        AssemblyOrderEntity entity = getById(id);
        entity.setStatus("FAIL");
        AssemblyOrderEntity saved = repository.save(entity);
        publishResult(saved, "REJECTED");
        return saved;
    }

    public void delete(UUID id) {
        repository.deleteById(id);
    }

    private void publishResult(AssemblyOrderEntity entity, String result) {
        try {
            OrderApprovalResultEvent event = new OrderApprovalResultEvent(
                    UUID.randomUUID(),
                    entity.getTraceId(),
                    entity.getSourceOrderId(),
                    entity.getSourceOrderType(),
                    result,
                    Instant.now()
            );

            String routingKey = "APPROVED".equalsIgnoreCase(result)
                    ? RabbitConfig.RK_ORDER_APPROVED
                    : RabbitConfig.RK_ORDER_REJECTED;

            rabbitTemplate.convertAndSend(
                    RabbitConfig.EXCHANGE,
                    routingKey,
                    objectMapper.writeValueAsString(event)
            );
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
