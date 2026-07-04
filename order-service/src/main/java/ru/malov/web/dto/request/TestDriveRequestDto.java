package ru.malov.web.dto.request;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class TestDriveRequestDto {
    private UUID carId;
    private LocalDateTime date;
}
