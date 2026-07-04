package ru.malov.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.malov.service.AssemblyOrderService;
import ru.malov.web.dto.request.AssemblyOrderCreateRequestDto;
import ru.malov.web.dto.request.AssemblyOrderUpdateRequestDto;
import ru.malov.web.dto.response.AssemblyOrderResponseDto;
import ru.malov.web.mapper.AssemblyOrderApiMapper;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/assembly-orders")
@RequiredArgsConstructor
public class AssemblyOrderController {

    private final AssemblyOrderService service;
    private final AssemblyOrderApiMapper mapper;

    @PostMapping
    @PreAuthorize("hasAnyRole('WAREHOUSE_ADMIN', 'ADMIN')")
    public AssemblyOrderResponseDto create(@RequestBody AssemblyOrderCreateRequestDto dto) {
        return mapper.toDto(service.create(dto));
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('WAREHOUSE_ADMIN', 'ADMIN')")
    public List<AssemblyOrderResponseDto> getAll() {
        return mapper.toDto(service.getAll());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('WAREHOUSE_ADMIN', 'ADMIN')")
    public AssemblyOrderResponseDto getById(@PathVariable UUID id) {
        return mapper.toDto(service.getById(id));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('WAREHOUSE_ADMIN', 'ADMIN')")
    public AssemblyOrderResponseDto update(@PathVariable UUID id, @RequestBody AssemblyOrderUpdateRequestDto dto) {
        return mapper.toDto(service.update(id, dto));
    }

    @PostMapping("/{id}/approve")
    @PreAuthorize("hasAnyRole('WAREHOUSE_ADMIN', 'ADMIN')")
    public AssemblyOrderResponseDto approve(@PathVariable UUID id) {
        return mapper.toDto(service.approve(id));
    }

    @PostMapping("/{id}/reject")
    @PreAuthorize("hasAnyRole('WAREHOUSE_ADMIN', 'ADMIN')")
    public AssemblyOrderResponseDto reject(@PathVariable UUID id) {
        return mapper.toDto(service.reject(id));
    }
}
