package ru.malov.web.dto.response;

import lombok.Data;

@Data
public class AvailableCarResponseDto {
    private String id;
    private String brand;
    private String model;
    private String bodyType;
    private String color;
    private String fuelType;
    private String transmissionType;
    private String driveType;
    private int enginePower;
    private int engineVolume;
    private String price;
    private String status;
}
