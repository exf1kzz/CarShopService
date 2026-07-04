package ru.malov.web.dto.response;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Set;
import java.util.UUID;

@Data
public class ComponentOptionResponseDto {
    private UUID id;
    private String name;
    private BigDecimal additionalPrice;
    private Set<UUID> compatibleModelsIds;
}
