package ru.malov.repository.abstractions;

import ru.malov.domain.model.component.SteeringWheel;

import java.util.List;
import java.util.UUID;

public interface SteeringWheelRepository {

    SteeringWheel Save(SteeringWheel steeringWheel);

    SteeringWheel FindById(UUID id);

    List<SteeringWheel> FindAll();

    void Delete(UUID id);
}
