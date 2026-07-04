package ru.malov.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.malov.persistence.entity.order.CustomOrderEntity;

import java.util.UUID;

public interface CustomOrderJpaRepository extends JpaRepository<CustomOrderEntity, UUID> {
}
