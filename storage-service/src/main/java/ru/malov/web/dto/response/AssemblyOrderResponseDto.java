package ru.malov.web.dto.response;

import lombok.Data;

import java.time.Instant;
import java.util.UUID;

@Data
public class AssemblyOrderResponseDto {
    private UUID id;
    private UUID sourceOrderId;
    private String status;
    private String sourceOrderType;
    private String traceId;
    private Instant createdAt;
    private Instant updatedAt;
    private boolean removed;
}
