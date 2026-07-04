package ru.malov.web.dto.request;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CarFilterRequestDto {
    private String brand;
    private String model;
    private String bodyType;
    private String color;
    private String fuelType;
    private String transmissionType;
    private String driveType;
    private Integer minPower;
    private Integer maxPower;
    private Integer minEngineVolume;
    private Integer maxEngineVolume;
    private BigDecimal minPrice;
    private BigDecimal maxPrice;
}
