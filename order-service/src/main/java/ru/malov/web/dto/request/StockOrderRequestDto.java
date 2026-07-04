package ru.malov.web.dto.request;

import lombok.Data;

import java.util.UUID;

@Data
public class StockOrderRequestDto {
    private UUID carId;
}
