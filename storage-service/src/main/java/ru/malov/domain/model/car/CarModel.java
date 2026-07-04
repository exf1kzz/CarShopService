package ru.malov.domain.model.car;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import ru.malov.domain.model.component.Interior;
import ru.malov.domain.model.component.SteeringWheel;
import ru.malov.domain.model.component.Transmission;
import ru.malov.domain.model.component.Wheels;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@RequiredArgsConstructor
public class CarModel {
    private final UUID id;
    private final String brand;
    private final String model;
    private final BigDecimal price;
    private final Wheels wheels;
    private final Transmission transmission;
    private final SteeringWheel steeringWheel;
    private final Interior interior;
}
