package ru.malov.domain.model.component;

import lombok.Getter;

import java.util.List;
import java.util.UUID;

@Getter
public abstract class BaseComponent {
    protected final UUID id;
    protected final ComponentOption baseOption;
    protected final List<ComponentOption> availableOptions;

    protected BaseComponent(UUID id, ComponentOption baseOption, List<ComponentOption> availableOptions) {
        this.id = id;
        this.baseOption = baseOption;
        this.availableOptions = availableOptions;
    }
}
