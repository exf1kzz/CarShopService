package ru.malov.web.mapper;

import org.mapstruct.Mapper;
import ru.malov.persistence.entity.assembly.AssemblyOrderEntity;
import ru.malov.web.dto.response.AssemblyOrderResponseDto;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AssemblyOrderApiMapper {

    AssemblyOrderResponseDto toDto(AssemblyOrderEntity entity);

    List<AssemblyOrderResponseDto> toDto(List<AssemblyOrderEntity> entities);
}
