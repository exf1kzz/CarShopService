package ru.malov.persistence.entity.car;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import ru.malov.domain.enums.*;
import ru.malov.persistence.entity.BaseEntity;

import java.math.BigDecimal;

@Entity
@Table(name = "cars")
@Getter
@Setter
public class CarEntity extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "model_id")
    private CarModelEntity model;

    @Enumerated(EnumType.STRING)
    private BodyType bodyType;

    @Enumerated(EnumType.STRING)
    private Color color;

    @Enumerated(EnumType.STRING)
    private FuelType fuelType;

    @Enumerated(EnumType.STRING)
    private TransmissionType transmissionType;

    @Enumerated(EnumType.STRING)
    private DriveType driveType;

    private int enginePower;

    private int engineVolume;

    private BigDecimal price;

    @Enumerated(EnumType.STRING)
    private CarStatus status;
}
