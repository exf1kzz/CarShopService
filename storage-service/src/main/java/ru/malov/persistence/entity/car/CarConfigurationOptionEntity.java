package ru.malov.persistence.entity.car;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import ru.malov.persistence.entity.BaseEntity;
import ru.malov.persistence.entity.component.ComponentOptionEntity;

import java.util.UUID;

@Entity
@Table(name = "car_configuration_options")
@Getter
@Setter
public class CarConfigurationOptionEntity extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "configuration_id")
    private CarConfigurationEntity configuration;

    private UUID componentId;

    @ManyToOne
    @JoinColumn(name = "option_id")
    private ComponentOptionEntity option;
}
