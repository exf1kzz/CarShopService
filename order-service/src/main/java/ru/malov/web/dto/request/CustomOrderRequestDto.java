package ru.malov.web.dto.request;

import lombok.Data;

import java.util.UUID;

@Data
public class CustomOrderRequestDto {
    private UUID configurationId;
}
