package ru.malov.messaging;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.malov.messaging.dto.OrderApprovalResultEvent;
import ru.malov.persistence.entity.message.ProcessedMessageEntity;
import ru.malov.persistence.repository.ProcessedMessageJpaRepository;
import ru.malov.service.OrderService;

import java.time.Instant;

@Component
@RequiredArgsConstructor
public class OrderApprovalResultListener {

    private final ObjectMapper objectMapper;
    private final ProcessedMessageJpaRepository processedRepo;
    private final OrderService orderService;

    @RabbitListener(queues = RabbitConfig.Q_ORDER_RESULT)
    @Transactional
    public void onResult(String rawJson) throws Exception {
        OrderApprovalResultEvent event = objectMapper.readValue(rawJson, OrderApprovalResultEvent.class);

        if (processedRepo.existsById(event.getEventId())) {
            return;
        }

        boolean approved = "APPROVED".equalsIgnoreCase(event.getResult());
        boolean stock = "STOCK".equalsIgnoreCase(event.getOrderType());

        if (stock) {
            if (approved) {
                orderService.ReadyForPickUpStockOrder(event.getOrderId());
            } else {
                orderService.CancelStockOrder(event.getOrderId());
            }
        } else {
            if (approved) {
                orderService.ReadyForPickUpCustomOrder(event.getOrderId());
            } else {
                orderService.CancelCustomOrder(event.getOrderId());
            }
        }

        ProcessedMessageEntity processed = new ProcessedMessageEntity();
        processed.setEventId(event.getEventId());
        processed.setProcessedAt(Instant.now());
        processed.setConsumerName("order-approval-result-listener");
        processedRepo.save(processed);
    }
}
