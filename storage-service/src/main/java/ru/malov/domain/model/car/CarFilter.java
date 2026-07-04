package ru.malov.domain.model.car;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import ru.malov.domain.enums.*;

import java.math.BigDecimal;

@Getter
@RequiredArgsConstructor
public class CarFilter {
    private final String brand;
    private final String model;

    private final BodyType bodyType;
    private final Color color;
    private final FuelType fuelType;
    private final TransmissionType transmissionType;
    private final DriveType driveType;

    private final Integer minPower;
    private final Integer maxPower;

    private final Double minEnginePower;
    private final Double maxEnginePower;

    private final BigDecimal minPrice;
    private final BigDecimal maxPrice;
}
