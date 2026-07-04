package ru.malov.security.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ru.malov.domain.model.order.TestDriveRequest;
import ru.malov.repository.abstractions.TestDriveRepository;

import java.util.UUID;

@Service("testDriveSecurity")
@RequiredArgsConstructor
public class TestDriveSecurityService {

    private final TestDriveRepository repository;
    private final CurrentUserService currentUserService;

    public boolean isOwner(UUID id) {
        TestDriveRequest request = repository.FindById(id);

        return request.getClient()
                .getId()
                .equals(currentUserService.getUserId());
    }

    public boolean isManagerOrAdmin() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        return auth.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_MANAGER") || a.getAuthority().equals("ROLE_ADMIN"));
    }
}
