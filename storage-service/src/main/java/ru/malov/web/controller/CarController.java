package ru.malov.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.malov.service.CarService;
import ru.malov.web.dto.request.CarFilterRequestDto;
import ru.malov.web.dto.request.CreateCarRequestDto;
import ru.malov.web.dto.response.CarResponseDto;
import ru.malov.web.mapper.CarApiMapper;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/cars")
@RequiredArgsConstructor
public class CarController {

    private final CarService carService;
    private final CarApiMapper mapper;

    @GetMapping
    @PreAuthorize("hasAnyRole('USER', 'MANAGER', 'WAREHOUSE_MANAGER', 'ADMIN')")
    public List<CarResponseDto> getAll() {
        return mapper.toDto(carService.GetAllCars());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('USER', 'MANAGER', 'WAREHOUSE_MANAGER', 'ADMIN')")
    public CarResponseDto getById(@PathVariable UUID id) {
        return mapper.toDto(carService.GetCarById(id));
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('WAREHOUSE_MANAGER', 'ADMIN')")
    public CarResponseDto create(@RequestBody CreateCarRequestDto request) {
        return mapper.toDto(carService.CreateCar(request));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('WAREHOUSE_MANAGER', 'ADMIN')")
    public void delete(@PathVariable UUID id) {
        carService.DeleteCar(id);
    }

    @PostMapping("/filter")
    @PreAuthorize("hasAnyRole('USER', 'MANAGER', 'WAREHOUSE_MANAGER', 'ADMIN')")
    public List<CarResponseDto> filter(@RequestBody CarFilterRequestDto request) {
        return mapper.toDto(carService.FilterCars(mapper.toFilter(request)));
    }
}
