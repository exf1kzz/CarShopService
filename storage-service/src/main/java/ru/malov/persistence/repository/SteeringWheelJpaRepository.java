package ru.malov.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.malov.persistence.entity.component.SteeringWheelEntity;

import java.util.UUID;

public interface SteeringWheelJpaRepository extends JpaRepository<SteeringWheelEntity, UUID> {
}
