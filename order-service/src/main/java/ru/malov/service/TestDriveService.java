package ru.malov.service;

import org.springframework.stereotype.Service;
import ru.malov.domain.exeption.DomainValidationException;
import ru.malov.domain.model.order.TestDriveRequest;
import ru.malov.domain.model.user.User;
import ru.malov.repository.abstractions.TestDriveRepository;
import ru.malov.repository.abstractions.UserRepository;
import ru.malov.security.service.CurrentUserService;
import ru.malov.security.service.TestDriveSecurityService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class TestDriveService {
    private final TestDriveRepository _testDriveRepository;
    private final UserRepository _userRepository;
    private final CurrentUserService _currentUserService;
    private final TestDriveSecurityService _testDriveSecurityService;

    public TestDriveService(TestDriveRepository testDriveRepository,
                            UserRepository userRepository,
                            CurrentUserService currentUserService,
                            TestDriveSecurityService testDriveSecurityService) {
        this._testDriveRepository = testDriveRepository;
        this._userRepository = userRepository;
        this._currentUserService = currentUserService;
        this._testDriveSecurityService = testDriveSecurityService;
    }

    public TestDriveRequest CreateRequest(UUID id, UUID carId, LocalDateTime dateTime) {
        UUID userId = _currentUserService.getUserId();
        User client = _userRepository.FindById(userId);

        if (dateTime.isBefore(LocalDateTime.now())) {
            throw new DomainValidationException("Test drive date can not be in the past");
        }

        TestDriveRequest request = new TestDriveRequest(id, client, carId, dateTime);

        _testDriveRepository.Save(request);
        return request;
    }

    public List<TestDriveRequest> GetAllRequests() {
        UUID userId = _currentUserService.getUserId();

        if (_testDriveSecurityService.isManagerOrAdmin()) {
            return _testDriveRepository.FindAll();
        }

        return _testDriveRepository.FindAll().stream()
                .filter(r -> r.getClient().getId().equals(userId))
                .toList();
    }

    public List<TestDriveRequest> GetRequestsByClient(UUID clientId) {
        return _testDriveRepository.FindAll().stream()
                .filter(request -> request.getClient().getId().equals(clientId))
                .toList();
    }

    public TestDriveRequest GetRequestById(UUID id) {
        return _testDriveRepository.FindById(id);
    }

    public List<TestDriveRequest> GetRequestsByCar(UUID carId) {
        return _testDriveRepository.FindAll().stream()
                .filter(request -> request.getCarId().equals(carId))
                .toList();
    }

    public void DeleteRequest(UUID id) {
        _testDriveRepository.Delete(id);
    }
}
