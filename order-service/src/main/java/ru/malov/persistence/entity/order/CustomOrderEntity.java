package ru.malov.persistence.entity.order;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import ru.malov.domain.enums.CustomOrderStatus;

import java.util.UUID;

@Entity
@Table(name = "custom_orders")
@Getter
@Setter
public class CustomOrderEntity extends OrderEntity {

    @Column(name = "configuration_id", nullable = false)
    private UUID configurationId;

    @Enumerated(EnumType.STRING)
    private CustomOrderStatus status;
}
