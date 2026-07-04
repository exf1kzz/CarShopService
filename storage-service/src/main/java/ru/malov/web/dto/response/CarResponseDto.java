package ru.malov.web.dto.response;

import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
public class CarResponseDto {
    private UUID id;
    private String brand;
    private String model;
    private String bodyType;
    private String color;
    private String fuelType;
    private String transmissionType;
    private String driveType;
    private int enginePower;
    private int engineVolume;
    private BigDecimal price;
    private String status;
}
