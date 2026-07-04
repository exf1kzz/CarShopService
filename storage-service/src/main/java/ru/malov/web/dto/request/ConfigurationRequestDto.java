package ru.malov.web.dto.request;

import lombok.Data;

import java.util.Map;
import java.util.UUID;

@Data
public class ConfigurationRequestDto {
    private UUID carModelId;
    private Map<UUID, UUID> selectedOptionsByComponentId;
}
