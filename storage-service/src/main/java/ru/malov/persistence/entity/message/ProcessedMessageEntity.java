package ru.malov.persistence.entity.message;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "processed_messages")
@Getter
@Setter
public class ProcessedMessageEntity {
    @Id
    private UUID eventId;
    private Instant processedAt;
    private String consumerName;
}
