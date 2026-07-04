package ru.malov.domain.model.car;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import ru.malov.domain.enums.*;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@RequiredArgsConstructor
public class Car {
    private final UUID id;
    private final CarModel model;

    private final BodyType bodyType;
    private final Color color;
    private final FuelType fuelType;
    private final TransmissionType transmissionType;
    private final DriveType driveType;

    private final int enginePower;
    private final int engineVolume;

    private final BigDecimal price;
    private final CarStatus status;
}
