package ru.malov.web.dto.response;

import lombok.Data;

import java.util.UUID;

@Data
public class CustomOrderResponseDto extends OrderResponseDto{
    private UUID configurationId;
}
