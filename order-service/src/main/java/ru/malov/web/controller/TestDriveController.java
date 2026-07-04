package ru.malov.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.malov.service.TestDriveService;
import ru.malov.web.dto.request.TestDriveRequestDto;
import ru.malov.web.dto.response.TestDriveResponseDto;
import ru.malov.web.mapper.TestDriveApiMapper;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/test-drives")
@RequiredArgsConstructor
public class TestDriveController {

    private final TestDriveService service;
    private final TestDriveApiMapper mapper;

    @PostMapping
    @PreAuthorize("hasRole('USER')")
    public TestDriveResponseDto create(@RequestBody TestDriveRequestDto dto) {
        return mapper.toDto(service.CreateRequest(UUID.randomUUID(), dto.getCarId(), dto.getDate()));
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('USER', 'MANAGER', 'ADMIN')")
    public List<TestDriveResponseDto> getAll() {
        return mapper.toDto(service.GetAllRequests());
    }

    @GetMapping("/client/{clientId}")
    @PreAuthorize("hasAnyRole('MANAGER', 'ADMIN')")
    public List<TestDriveResponseDto> getByClientId(@PathVariable UUID clientId) {
        return mapper.toDto(service.GetRequestsByClient(clientId));
    }

    @GetMapping("/{id}")
    @PreAuthorize("@testDriveSecurity.isOwner(#id) or hasAnyRole('MANAGER', 'ADMIN')")
    public TestDriveResponseDto getById(@PathVariable UUID id) {
        return mapper.toDto(service.GetRequestById(id));
    }

    @GetMapping("/car/{carId}")
    @PreAuthorize("hasAnyRole('MANAGER', 'ADMIN')")
    public List<TestDriveResponseDto> getByCarId(@PathVariable UUID carId) {
        return mapper.toDto(service.GetRequestsByCar(carId));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("@testDriveSecurity.isOwner(#id) or hasRole('ADMIN')")
    public void delete(@PathVariable UUID id) {
        service.DeleteRequest(id);
    }
}
