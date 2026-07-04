package ru.malov.messaging;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.malov.messaging.dto.OrderSentForApprovalEvent;
import ru.malov.persistence.entity.outbox.OutboxEventEntity;
import ru.malov.persistence.repository.OutboxEventJpaRepository;

import java.time.Instant;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OutboxService {

    private final OutboxEventJpaRepository repository;
    private final ObjectMapper mapper;

    public void saveOrderSent(UUID orderId, String orderType, String traceId) {
        try {
            var event = new OrderSentForApprovalEvent(
                    UUID.randomUUID(),
                    traceId,
                    orderId,
                    orderType,
                    Instant.now()
            );

            var e = new OutboxEventEntity();
            e.setId(event.getEventId());
            e.setAggregateId(orderId);
            e.setEventType("OrderSentForApproval");
            e.setPayload(mapper.writeValueAsString(event));
            e.setStatus("NEW");
            e.setCreatedAt(Instant.now());
            e.setRetryCount(0);
            repository.save(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
