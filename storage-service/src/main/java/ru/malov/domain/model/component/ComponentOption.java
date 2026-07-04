package ru.malov.domain.model.component;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.util.Set;
import java.util.UUID;

@Getter
@RequiredArgsConstructor
public class ComponentOption {
    private final UUID id;
    private final String name;
    private final BigDecimal additionalPrice;
    private final Set<UUID> compatibleCarModelsIds;
}
