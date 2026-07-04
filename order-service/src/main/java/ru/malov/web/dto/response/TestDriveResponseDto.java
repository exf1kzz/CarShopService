package ru.malov.web.dto.response;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class TestDriveResponseDto {
    private UUID id;
    private UUID clientId;
    private UUID carId;
    private LocalDateTime date;
}
