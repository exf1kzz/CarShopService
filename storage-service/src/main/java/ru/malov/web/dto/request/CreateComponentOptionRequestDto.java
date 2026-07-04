package ru.malov.web.dto.request;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Set;
import java.util.UUID;

@Data
public class CreateComponentOptionRequestDto {
    private String name;
    private BigDecimal additionalPrice;
    private Set<UUID> compatibleCarModelsIds;
}
