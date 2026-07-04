package ru.malov.domain.model.component;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Map;
import java.util.UUID;

@Getter
@RequiredArgsConstructor
public class ConfigurationRequest {
    private final UUID carModelId;
    private final Map<UUID, UUID> selectedOptionsByComponentId;
}
