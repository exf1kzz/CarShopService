package ru.malov.service;

import org.springframework.stereotype.Service;
import ru.malov.domain.enums.*;
import ru.malov.domain.exeption.EntityNotFoundException;
import ru.malov.domain.model.car.Car;
import ru.malov.domain.model.car.CarFilter;
import ru.malov.domain.model.car.CarModel;
import ru.malov.repository.abstractions.CarModelRepository;
import ru.malov.repository.abstractions.CarRepository;
import ru.malov.web.dto.request.CreateCarRequestDto;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CarService {

    private final CarRepository _carRepository;
    private final CarModelRepository _carModelRepository;

    public CarService(CarRepository carRepository, CarModelRepository carModelRepository) {
        this._carRepository = carRepository;
        this._carModelRepository = carModelRepository;
    }

    public List<Car> GetAllCars() {
        return _carRepository.FindAll();
    }

    public Car GetCarById(UUID id) {
        return _carRepository.FindById(id);
    }

    public Car CreateCar(CreateCarRequestDto dto) {
        CarModel model = _carModelRepository.FindById(dto.getModelId());

        if (model == null) {
            throw new EntityNotFoundException("Model not found");
        }

        Car car = new Car(
                UUID.randomUUID(),
                model,
                BodyType.valueOf(dto.getBodyType().toUpperCase()),
                Color.valueOf(dto.getColor().toUpperCase()),
                FuelType.valueOf(dto.getFuelType().toUpperCase()),
                TransmissionType.valueOf(dto.getTransmissionType().toUpperCase()),
                DriveType.valueOf(dto.getDriveType().toUpperCase()),
                dto.getEnginePower(),
                dto.getEngineVolume(),
                dto.getPrice(),
                CarStatus.valueOf(dto.getStatus().toUpperCase())
        );

        return _carRepository.Save(car);
    }

    public void DeleteCar(UUID id) {
        _carRepository.Delete(id);
    }

    public List<Car> FilterCars(CarFilter filter) {
        return _carRepository.FindAll().stream()
                .filter(car -> filter.getBrand() == null || car.getModel().getBrand().equalsIgnoreCase(filter.getBrand()))
                .filter(car -> filter.getModel() == null || car.getModel().getModel().equalsIgnoreCase(filter.getModel()))
                .filter(car -> filter.getBodyType() == null || car.getBodyType() == filter.getBodyType())
                .filter(car -> filter.getColor() == null || car.getColor() == filter.getColor())
                .filter(car -> filter.getFuelType() == null || car.getFuelType() == filter.getFuelType())
                .filter(car -> filter.getTransmissionType() == null || car.getTransmissionType() == filter.getTransmissionType())
                .filter(car -> filter.getDriveType() == null || car.getDriveType() == filter.getDriveType())
                .filter(car -> filter.getMinPower() == null || car.getEnginePower() >= filter.getMinPower())
                .filter(car -> filter.getMaxPower() == null || car.getEnginePower() <= filter.getMaxPower())
                .filter(car -> filter.getMinEnginePower() == null || car.getEngineVolume() >= filter.getMinEnginePower())
                .filter(car -> filter.getMaxEnginePower() == null || car.getEngineVolume() <= filter.getMaxEnginePower())
                .filter(car -> filter.getMinPrice() == null || car.getPrice().compareTo(filter.getMinPrice()) >= 0)
                .filter(car -> filter.getMaxPrice() == null || car.getPrice().compareTo(filter.getMaxPrice()) <= 0)
                .collect(Collectors.toList());
    }
}
