package ru.malov.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.malov.service.ConfiguratorService;
import ru.malov.web.dto.request.ConfigurationRequestDto;
import ru.malov.web.dto.response.CarConfigurationResponseDto;
import ru.malov.web.mapper.ConfiguratorApiMapper;

import java.util.Map;

@RestController
@RequestMapping("api/configurator")
@RequiredArgsConstructor
@PreAuthorize("hasAnyRole('USER', 'MANAGER', 'WAREHOUSE_MANAGER', 'ADMIN')")
public class ConfiguratorController {

    private final ConfiguratorService configuratorService;
    private final ConfiguratorApiMapper mapper;

    @PostMapping("/build")
    public CarConfigurationResponseDto build(@RequestBody ConfigurationRequestDto request) {

        var config = configuratorService.BuildConfiguration(mapper.toDomain(request));

        CarConfigurationResponseDto dto = new CarConfigurationResponseDto();

        dto.setCarModelId(config.getCarModel().getId());
        dto.setTotalPrice(config.getTotalPrice());
        dto.setSelectedOptionsByComponentId(config.getSelectOptions().entrySet().stream()
                .collect(java.util.stream.Collectors.toMap(Map.Entry::getKey, e -> e.getValue().getId())));

        return dto;
    }
}
