package ru.malov.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.UUID;

@MappedSuperclass
@Getter
@Setter
public abstract class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, updatable = false)
    private Instant createdAt;

    @Column(nullable = false)
    private Instant updatedAt;

    @Column(nullable = false)
    private boolean removed;

    @PrePersist
    public void onCreated() {
        createdAt = Instant.now();
        updatedAt = Instant.now();
        removed = false;
    }

    @PreUpdate
    public void onUpdated() {
        updatedAt = Instant.now();
    }
}
