package ru.malov.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.malov.persistence.entity.car.CarEntity;

import java.util.UUID;

public interface CarJpaRepository extends JpaRepository<CarEntity, UUID> {
}
