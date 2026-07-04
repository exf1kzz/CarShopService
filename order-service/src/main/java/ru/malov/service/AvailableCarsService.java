package ru.malov.service;

import org.springframework.stereotype.Service;
import ru.malov.grpc.StorageCarsGrpcClient;
import ru.malov.grpc.cars.v1.CarItem;
import ru.malov.web.dto.response.AvailableCarResponseDto;

import java.util.List;

@Service
public class AvailableCarsService {

    private final StorageCarsGrpcClient client;

    public AvailableCarsService(StorageCarsGrpcClient client) {
        this.client = client;
    }

    public List<AvailableCarResponseDto> getAll() {
        return client.getAvailableCars().stream().map(this::toDto).toList();
    }

    public AvailableCarResponseDto getById(String id) {
        return toDto(client.getAvailableCarById(id));
    }

    private AvailableCarResponseDto toDto(CarItem car) {
        AvailableCarResponseDto dto = new AvailableCarResponseDto();
        dto.setId(car.getId());
        dto.setBrand(car.getBrand());
        dto.setModel(car.getModel());
        dto.setBodyType(car.getBodyType());
        dto.setColor(car.getColor());
        dto.setFuelType(car.getFuelType());
        dto.setTransmissionType(car.getTransmissionType());
        dto.setDriveType(car.getDriveType());
        dto.setEnginePower(car.getEnginePower());
        dto.setEngineVolume(car.getEngineVolume());
        dto.setPrice(car.getPrice());
        dto.setStatus(car.getStatus());
        return dto;
    }
}
