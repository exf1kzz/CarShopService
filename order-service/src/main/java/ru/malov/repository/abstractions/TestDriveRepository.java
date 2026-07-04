package ru.malov.repository.abstractions;

import ru.malov.domain.model.order.TestDriveRequest;

import java.util.List;
import java.util.UUID;

public interface TestDriveRepository {

    TestDriveRequest Save(TestDriveRequest request);

    TestDriveRequest FindById(UUID id);

    List<TestDriveRequest> FindAll();

    void Delete(UUID id);
}
