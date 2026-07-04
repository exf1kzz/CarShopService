package ru.malov.grpc;

import io.grpc.Status;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import ru.malov.domain.enums.CarStatus;
import ru.malov.domain.model.car.Car;
import ru.malov.grpc.cars.v1.*;
import ru.malov.service.CarService;

import java.util.List;
import java.util.UUID;

@GrpcService
public class CarAvailabilityGrpcService extends CarAvailabilityServiceGrpc.CarAvailabilityServiceImplBase {

    private final CarService carService;

    public CarAvailabilityGrpcService(CarService carService) {
        this.carService = carService;
    }

    @Override
    public void getAvailableCars(GetAvailableCarsRequest request, StreamObserver<GetAvailableCarsResponse> responseObserver) {
        List<Car> availableCars = carService.GetAllCars().stream()
                .filter(this::isAvailableForSale)
                .toList();

        GetAvailableCarsResponse.Builder response = GetAvailableCarsResponse.newBuilder();
        availableCars.forEach(car -> response.addCars(toGrpc(car)));

        responseObserver.onNext(response.build());
        responseObserver.onCompleted();
    }

    @Override
    public void getAvailableCarById(GetAvailableCarByIdRequest request, StreamObserver<GetAvailableCarByIdResponse> responseObserver) {
        UUID id;
        try {
            id = UUID.fromString(request.getId());
        } catch (Exception e) {
            responseObserver.onError(Status.INVALID_ARGUMENT.withDescription("Invalid car id").asRuntimeException());
            return;
        }

        Car car = carService.GetCarById(id);
        if (car == null || !isAvailableForSale(car)) {
            responseObserver.onError(Status.NOT_FOUND.withDescription("Car not found").asRuntimeException());
            return;
        }

        GetAvailableCarByIdResponse response = GetAvailableCarByIdResponse.newBuilder()
                .setCar(toGrpc(car))
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    private boolean isAvailableForSale(Car car) {
        return car.getStatus() == CarStatus.AVAILABLE || car.getStatus() == CarStatus.TEST_DRIVE_AVAILABLE;
    }

    private CarItem toGrpc(Car car) {
        return CarItem.newBuilder()
                .setId(car.getId().toString())
                .setBrand(car.getModel().getBrand())
                .setModel(car.getModel().getModel())
                .setBodyType(car.getBodyType().name())
                .setColor(car.getColor().name())
                .setFuelType(car.getFuelType().name())
                .setTransmissionType(car.getTransmissionType().name())
                .setDriveType(car.getDriveType().name())
                .setEnginePower(car.getEnginePower())
                .setEngineVolume(car.getEngineVolume())
                .setPrice(car.getPrice().toString())
                .setStatus(car.getStatus().name())
                .build();
    }
}
