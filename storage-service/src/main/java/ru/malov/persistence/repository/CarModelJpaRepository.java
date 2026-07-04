package ru.malov.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.malov.persistence.entity.car.CarModelEntity;

import java.util.UUID;

public interface CarModelJpaRepository extends JpaRepository<CarModelEntity, UUID> {
}
