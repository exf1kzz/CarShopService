package ru.malov.persistence.entity.car;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import ru.malov.persistence.entity.BaseEntity;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "car_configurations")
@Getter
@Setter
public class CarConfigurationEntity extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "car_model_id")
    private CarModelEntity carModel;

    private BigDecimal totalPrice;

    @OneToMany(mappedBy = "configuration", cascade = CascadeType.ALL)
    private List<CarConfigurationOptionEntity> selectedOptions;
}
