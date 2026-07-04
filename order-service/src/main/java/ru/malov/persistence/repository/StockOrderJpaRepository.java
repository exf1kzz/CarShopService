package ru.malov.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.malov.persistence.entity.order.StockOrderEntity;

import java.util.UUID;

public interface StockOrderJpaRepository extends JpaRepository<StockOrderEntity, UUID> {
}
