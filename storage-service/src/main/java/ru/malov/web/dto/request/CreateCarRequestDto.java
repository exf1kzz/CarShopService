package ru.malov.web.dto.request;

import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
public class CreateCarRequestDto {
    private UUID modelId;
    private String bodyType;
    private String color;
    private String fuelType;
    private String transmissionType;
    private String driveType;
    private Integer enginePower;
    private Integer engineVolume;
    private BigDecimal price;
    private String status;
}
