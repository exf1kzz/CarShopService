package ru.malov.persistence.entity.car;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import ru.malov.persistence.entity.BaseEntity;
import ru.malov.persistence.entity.component.InteriorEntity;
import ru.malov.persistence.entity.component.SteeringWheelEntity;
import ru.malov.persistence.entity.component.TransmissionEntity;
import ru.malov.persistence.entity.component.WheelsEntity;

import java.math.BigDecimal;

@Entity
@Table(name = "car_models")
@Getter
@Setter
public class CarModelEntity extends BaseEntity {

    private String brand;

    private String model;

    private BigDecimal price;

    @OneToOne
    @JoinColumn(name = "wheels_id")
    private WheelsEntity wheels;

    @OneToOne
    @JoinColumn(name = "transmission_id")
    private TransmissionEntity transmission;

    @OneToOne
    @JoinColumn(name = "steering_wheel_id")
    private SteeringWheelEntity steeringWheel;

    @OneToOne
    @JoinColumn(name = "interior_id")
    private InteriorEntity interior;
}
