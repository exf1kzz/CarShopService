package ru.malov.messaging;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.malov.persistence.repository.OutboxEventJpaRepository;

import java.time.Instant;

@Component
@RequiredArgsConstructor
public class OutboxPublisher {

    private final OutboxEventJpaRepository repository;
    private final RabbitTemplate rabbitTemplate;

    @Scheduled(fixedDelay = 3000)
    public void publish() {
        for (var e : repository.findTop50ByStatusOrderByCreatedAtAsc("NEW")) {
            try {
                rabbitTemplate.convertAndSend(RabbitConfig.EXCHANGE, RabbitConfig.RK_ORDER_SENT, e.getPayload());
                e.setStatus("SENT");
                e.setSentAt(Instant.now());
            } catch (Exception ex) {
                e.setStatus("FAILED");
                e.setRetryCount(e.getRetryCount() + 1);
            }

            repository.save(e);
        }
    }
}
