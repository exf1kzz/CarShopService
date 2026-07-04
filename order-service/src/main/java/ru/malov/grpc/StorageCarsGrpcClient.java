package ru.malov.grpc;

import io.grpc.Status;
import io.grpc.StatusRuntimeException;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Component;
import ru.malov.domain.exeption.StorageUnavailableException;
import ru.malov.grpc.cars.v1.CarAvailabilityServiceGrpc;
import ru.malov.grpc.cars.v1.CarItem;
import ru.malov.grpc.cars.v1.GetAvailableCarByIdRequest;
import ru.malov.grpc.cars.v1.GetAvailableCarsRequest;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Component
public class StorageCarsGrpcClient {

    @GrpcClient("storage-service")
    private CarAvailabilityServiceGrpc.CarAvailabilityServiceBlockingStub stub;

    public List<CarItem> getAvailableCars() {
        try {
            return stub.withDeadlineAfter(1500, TimeUnit.MILLISECONDS)
                    .getAvailableCars(GetAvailableCarsRequest.newBuilder().build())
                    .getCarsList();
        } catch (StatusRuntimeException e) {
            handleAvailabilityErrors(e);
            throw e;
        }
    }

    public CarItem getAvailableCarById(String id) {
        try {
            return stub.withDeadlineAfter(1500, TimeUnit.MILLISECONDS)
                    .getAvailableCarById(GetAvailableCarByIdRequest.newBuilder().setId(id).build())
                    .getCar();
        } catch (StatusRuntimeException e) {
            handleAvailabilityErrors(e);
            throw e;
        }
    }

    private void handleAvailabilityErrors(StatusRuntimeException e) {
        if (e.getStatus().getCode() == Status.Code.UNAVAILABLE ||
        e.getStatus().getCode() == Status.Code.DEADLINE_EXCEEDED) {
            throw new StorageUnavailableException("StorageService is unavailable", e);
        }
    }
}
