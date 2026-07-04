package ru.malov.messaging;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.malov.messaging.dto.OrderSentForApprovalEvent;
import ru.malov.persistence.entity.message.ProcessedMessageEntity;
import ru.malov.persistence.repository.ProcessedMessageJpaRepository;
import ru.malov.service.AssemblyOrderService;
import ru.malov.web.dto.request.AssemblyOrderCreateRequestDto;

import java.time.Instant;

@Component
@RequiredArgsConstructor
public class StorageOrderApprovalListener {

    private final ObjectMapper objectMapper;
    private final ProcessedMessageJpaRepository processedRepo;
    private final AssemblyOrderService assemblyOrderService;

    @RabbitListener(queues = RabbitConfig.Q_STORAGE_APPROVAL)
    @Transactional
    public void onOrderSentForApproval(String rawJson) throws Exception {
        OrderSentForApprovalEvent event = objectMapper.readValue(rawJson, OrderSentForApprovalEvent.class);

        if (processedRepo.existsById(event.getEventId())) {
            return;
        }

        AssemblyOrderCreateRequestDto createDto = new AssemblyOrderCreateRequestDto();
        createDto.setSourceOrderId(event.getOrderId());
        createDto.setSourceOrderType(event.getOrderType());
        createDto.setTraceId(event.getTraceId());
        createDto.setStatus("CREATED");
        assemblyOrderService.create(createDto);

        ProcessedMessageEntity processed = new ProcessedMessageEntity();
        processed.setEventId(event.getEventId());
        processed.setProcessedAt(Instant.now());
        processed.setConsumerName("storage-order-approval-listener");
        processedRepo.save(processed);
    }
}
