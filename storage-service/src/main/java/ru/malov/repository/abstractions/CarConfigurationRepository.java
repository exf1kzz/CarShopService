package ru.malov.repository.abstractions;

import ru.malov.domain.model.car.CarConfiguration;
import ru.malov.domain.model.car.CarModel;

import java.util.List;
import java.util.UUID;

public interface CarConfigurationRepository {

    CarConfiguration Save(CarConfiguration configuration);

    CarConfiguration FindById(UUID id);

    List<CarConfiguration> FindAll();

    void Delete(UUID id);
}
