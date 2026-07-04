package ru.malov.persistence.entity.order;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import ru.malov.domain.enums.StockOrderStatus;

import java.util.UUID;

@Entity
@Table(name = "stock_orders")
@Getter
@Setter
public class StockOrderEntity extends OrderEntity {

    @Column(name = "car_id", nullable = false)
    private UUID carId;

    @Enumerated(EnumType.STRING)
    private StockOrderStatus status;
}
