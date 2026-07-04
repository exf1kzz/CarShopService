package ru.malov.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.malov.persistence.entity.component.WheelsEntity;

import java.util.UUID;

public interface WheelsJpaRepository extends JpaRepository<WheelsEntity, UUID> {
}
