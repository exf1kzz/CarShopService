package ru.malov.web.mapper;

import org.mapstruct.Mapper;
import ru.malov.domain.model.component.ComponentOption;
import ru.malov.web.dto.response.ComponentOptionResponseDto;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ComponentOptionApiMapper {
    ComponentOptionResponseDto toDto(ComponentOption option);
    List<ComponentOptionResponseDto> toDto(List<ComponentOption> options);
}
