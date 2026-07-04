package ru.malov.repository.abstractions;

import ru.malov.domain.model.component.ComponentOption;

import java.util.List;
import java.util.UUID;

public interface ComponentOptionRepository {

    ComponentOption Save(ComponentOption option);

    ComponentOption FindById(UUID id);

    List<ComponentOption> FindAll();

    void Delete(UUID id);
}
