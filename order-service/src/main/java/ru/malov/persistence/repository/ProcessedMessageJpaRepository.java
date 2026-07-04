package ru.malov.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.malov.persistence.entity.message.ProcessedMessageEntity;

import java.util.UUID;

public interface ProcessedMessageJpaRepository extends JpaRepository<ProcessedMessageEntity, UUID> {
}
