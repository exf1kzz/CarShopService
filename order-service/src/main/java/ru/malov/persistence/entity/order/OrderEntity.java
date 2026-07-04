package ru.malov.persistence.entity.order;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import ru.malov.persistence.entity.BaseEntity;
import ru.malov.persistence.entity.user.UserEntity;

@Entity
@Table(name = "orders")
@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@Setter
public abstract class OrderEntity extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "client_id")
    private UserEntity client;

    @ManyToOne
    @JoinColumn(name = "manager_id")
    private UserEntity manager;
}
