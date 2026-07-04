package ru.malov.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.malov.persistence.entity.component.TransmissionEntity;

import java.util.UUID;

public interface TransmissionJpaRepository extends JpaRepository<TransmissionEntity, UUID> {
}
