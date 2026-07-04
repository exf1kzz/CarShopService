package ru.malov.domain.model.component;

import java.util.List;
import java.util.UUID;

public class Interior extends BaseComponent {

    public Interior(UUID id, ComponentOption baseOption, List<ComponentOption> availableOptions) {
        super(id, baseOption, availableOptions);
    }
}
