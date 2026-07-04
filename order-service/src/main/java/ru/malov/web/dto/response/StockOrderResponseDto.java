package ru.malov.web.dto.response;

import lombok.Data;

import java.util.UUID;

@Data
public class StockOrderResponseDto extends OrderResponseDto {
    private UUID carId;
}
