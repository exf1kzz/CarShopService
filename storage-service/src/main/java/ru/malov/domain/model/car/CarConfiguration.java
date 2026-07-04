package ru.malov.domain.model.car;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import ru.malov.domain.model.component.ComponentOption;

import java.math.BigDecimal;
import java.util.Map;
import java.util.UUID;

@Getter
@RequiredArgsConstructor
public class CarConfiguration {
    private final CarModel carModel;
    private final Map<UUID, ComponentOption> selectOptions;
    private final BigDecimal totalPrice;
}
