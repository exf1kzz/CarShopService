package ru.malov.persistence.mapper;

import org.mapstruct.Mapper;
import ru.malov.config.MapperConfig;
import ru.malov.domain.model.component.ComponentOption;
import ru.malov.persistence.entity.component.ComponentOptionEntity;

@Mapper(config = MapperConfig.class)
public interface ComponentOptionMapper {

    ComponentOption toDomain(ComponentOptionEntity entity);

    ComponentOptionEntity toEntity(ComponentOption domain);
}
