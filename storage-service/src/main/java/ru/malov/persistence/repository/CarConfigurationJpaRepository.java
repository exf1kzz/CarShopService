package ru.malov.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.malov.persistence.entity.car.CarConfigurationEntity;

import java.util.UUID;

public interface CarConfigurationJpaRepository extends JpaRepository<CarConfigurationEntity, UUID> {
}
