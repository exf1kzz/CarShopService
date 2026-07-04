package ru.malov.persistence.entity.outbox;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "outbox_events")
@Getter
@Setter
public class OutboxEventEntity {

    @Id
    private UUID id;

    @Column(nullable = false)
    private UUID aggregateId;

    @Column(nullable = false)
    private String eventType;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String payload;

    @Column(nullable = false)
    private String status;

    @Column(nullable = false)
    private Instant createdAt;

    private Instant sentAt;

    @Column(nullable = false)
    private int retryCount;
}
