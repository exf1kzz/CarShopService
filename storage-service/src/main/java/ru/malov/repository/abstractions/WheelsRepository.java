package ru.malov.repository.abstractions;

import ru.malov.domain.model.component.Wheels;

import java.util.List;
import java.util.UUID;

public interface WheelsRepository {

    Wheels Save(Wheels wheels);

    Wheels FindById(UUID id);

    List<Wheels> FindAll();

    void Delete(UUID id);
}
