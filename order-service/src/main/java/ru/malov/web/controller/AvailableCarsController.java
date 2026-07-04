package ru.malov.web.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.malov.service.AvailableCarsService;
import ru.malov.web.dto.response.AvailableCarResponseDto;

import java.util.List;

@RestController
@RequestMapping("/api/v1/cars")
public class AvailableCarsController {

    private final AvailableCarsService service;

    public AvailableCarsController(AvailableCarsService service) {
        this.service = service;
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('USER','MANAGER','ADMIN')")
    public List<AvailableCarResponseDto> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('USER','MANAGER','ADMIN')")
    public ResponseEntity<AvailableCarResponseDto> getById(@PathVariable String id) {
        AvailableCarResponseDto dto = service.getById(id);

        if (dto == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(dto);
    }
}
