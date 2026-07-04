package ru.malov.web.dto.response;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Map;
import java.util.UUID;

@Data
public class CarConfigurationResponseDto {
    private UUID carModelId;
    private Map<UUID, UUID> selectedOptionsByComponentId;
    private BigDecimal totalPrice;
}
