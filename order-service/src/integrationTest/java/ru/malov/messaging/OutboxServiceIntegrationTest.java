package ru.malov.messaging;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import ru.malov.persistence.entity.outbox.OutboxEventEntity;
import ru.malov.persistence.repository.OutboxEventJpaRepository;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@Testcontainers(disabledWithoutDocker = true)
@SpringBootTest
class OutboxServiceIntegrationTest {

    @Container
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:16")
            .withDatabaseName("order_it_db")
            .withUsername("postgres")
            .withPassword("postgres");

    @DynamicPropertySource
    static void props(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgres::getJdbcUrl);
        registry.add("spring.datasource.username", postgres::getUsername);
        registry.add("spring.datasource.password", postgres::getPassword);
        registry.add("spring.jpa.hibernate.ddl-auto", () -> "none");
        registry.add("spring.liquibase.change-log", () -> "classpath:db/changelog/master.xml");
        registry.add("spring.security.oauth2.resourceserver.jwt.issuer-uri", () -> "http://dummy");
        registry.add("spring.task.scheduling.enabled", () -> "false");
        registry.add("spring.rabbitmq.listener.simple.auto-startup", () -> "false");
        registry.add("spring.rabbitmq.listener.direct.auto-startup", () -> "false");
    }

    private final OutboxService outboxService;
    private final OutboxEventJpaRepository outboxRepository;

    OutboxServiceIntegrationTest(OutboxService outboxService, OutboxEventJpaRepository outboxRepository) {
        this.outboxService = outboxService;
        this.outboxRepository = outboxRepository;
    }

    @Test
    void saveOrderSent_shouldPersistToRealDb() {
        UUID orderId = UUID.randomUUID();

        outboxService.saveOrderSent(orderId, "STOCK", "trace-it");

        OutboxEventEntity saved = outboxRepository.findAll().stream()
                .filter(e -> orderId.equals(e.getAggregateId()))
                .findFirst()
                .orElseThrow();

        assertEquals("OrderSentForApproval", saved.getEventType());
        assertEquals("NEW", saved.getStatus());
        assertNotNull(saved.getPayload());
        assertTrue(saved.getPayload().contains("orderId"));
    }
}