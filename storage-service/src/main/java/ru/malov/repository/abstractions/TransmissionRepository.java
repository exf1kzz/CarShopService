package ru.malov.repository.abstractions;

import ru.malov.domain.model.component.Transmission;

import java.util.List;
import java.util.UUID;

public interface TransmissionRepository {

    Transmission Save(Transmission transmission);

    Transmission FindById(UUID id);

    List<Transmission> FindAll();

    void Delete(UUID id);
}
