package ru.malov.repository.abstractions;

import ru.malov.domain.model.component.Interior;

import java.util.List;
import java.util.UUID;

public interface InteriorRepository {

    Interior Save(Interior interior);

    Interior FindById(UUID id);

    List<Interior> FindAll();

    void Delete(UUID id);
}
