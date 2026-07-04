package ru.malov.repository.abstractions;

import ru.malov.domain.model.car.CarModel;
import ru.malov.domain.model.component.BaseComponent;

import java.util.List;
import java.util.UUID;

public interface CarModelRepository {

    CarModel Save(CarModel carModel);

    CarModel FindById(UUID id);

    List<CarModel> FindAll();

    void Delete(UUID id);

    CarModel AddComponent(UUID id, BaseComponent component);
}
