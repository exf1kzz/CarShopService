package ru.malov.web.mapper;

import org.mapstruct.Mapper;
import ru.malov.domain.model.car.CarConfiguration;
import ru.malov.domain.model.component.ConfigurationRequest;
import ru.malov.web.dto.request.ConfigurationRequestDto;
import ru.malov.web.dto.response.CarConfigurationResponseDto;

@Mapper(componentModel = "spring")
public interface ConfiguratorApiMapper {

    ConfigurationRequest toDomain(ConfigurationRequestDto dto);
}
