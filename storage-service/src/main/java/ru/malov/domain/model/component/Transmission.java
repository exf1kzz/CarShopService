package ru.malov.domain.model.component;

import java.util.List;
import java.util.UUID;

public class Transmission extends BaseComponent {
    public Transmission(UUID id, ComponentOption baseOption, List<ComponentOption> availableOptions) {
        super(id, baseOption, availableOptions);
    }
}
