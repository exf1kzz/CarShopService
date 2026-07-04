package ru.malov.persistence.mapper;

import org.mapstruct.Mapper;
import ru.malov.config.MapperConfig;
import ru.malov.domain.model.car.CarModel;
import ru.malov.persistence.entity.car.CarModelEntity;

@Mapper(
        config = MapperConfig.class,
        uses = {ComponentMapper.class}
)
public interface CarModelMapper {

    CarModel toDomain(CarModelEntity entity);

    CarModelEntity toEntity(CarModel model);
}
