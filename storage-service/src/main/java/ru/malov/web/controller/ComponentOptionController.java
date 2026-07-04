package ru.malov.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.malov.service.ComponentOptionService;
import ru.malov.web.dto.request.CreateComponentOptionRequestDto;
import ru.malov.web.dto.response.ComponentOptionResponseDto;
import ru.malov.web.mapper.ComponentOptionApiMapper;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/component-options")
@RequiredArgsConstructor
public class ComponentOptionController {

    private final ComponentOptionService service;
    private final ComponentOptionApiMapper mapper;

    @GetMapping
    @PreAuthorize("hasAnyRole('WAREHOUSE_MANAGER', 'ADMIN')")
    public List<ComponentOptionResponseDto> getAll() {
        return mapper.toDto(service.getAll());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('WAREHOUSE_MANAGER', 'ADMIN')")
    public ComponentOptionResponseDto getById(@PathVariable UUID id) {
        return mapper.toDto(service.getById(id));
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('WAREHOUSE_MANAGER', 'ADMIN')")
    public ComponentOptionResponseDto create(@RequestBody CreateComponentOptionRequestDto dto) {
        return mapper.toDto(service.create(dto));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('WAREHOUSE_MANAGER', 'ADMIN')")
    public void deleteById(@PathVariable UUID id) {
        service.delete(id);
    }
}
