package ru.malov.web.dto.request;

import lombok.Data;

import java.util.UUID;

@Data
public class AssemblyOrderCreateRequestDto {
    private UUID sourceOrderId;
    private String status;
    private String sourceOrderType;
    private String traceId;
}
