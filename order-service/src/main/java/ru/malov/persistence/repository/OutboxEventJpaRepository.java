package ru.malov.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.malov.persistence.entity.outbox.OutboxEventEntity;

import java.util.List;
import java.util.UUID;

public interface OutboxEventJpaRepository extends JpaRepository<OutboxEventEntity, UUID> {
    List<OutboxEventEntity> findTop50ByStatusOrderByCreatedAtAsc(String status);
}
