package ru.malov.repository.abstractions;

import ru.malov.domain.model.car.Car;
import ru.malov.domain.model.component.BaseComponent;

import java.util.List;
import java.util.UUID;

public interface CarRepository {

    Car Save(Car car);

    Car FindById(UUID id);

    List<Car> FindAll();

    void Delete(UUID id);
}
