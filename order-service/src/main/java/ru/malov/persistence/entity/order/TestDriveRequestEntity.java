package ru.malov.persistence.entity.order;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import ru.malov.persistence.entity.BaseEntity;
import ru.malov.persistence.entity.user.UserEntity;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "test_drive_requests")
@Getter
@Setter
public class TestDriveRequestEntity extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "client_id")
    private UserEntity client;

    @Column(name = "car_id", nullable = false)
    private UUID carId;

    private LocalDateTime date;
}
