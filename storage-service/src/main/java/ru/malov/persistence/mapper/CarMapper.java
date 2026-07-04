package ru.malov.persistence.mapper;

import org.mapstruct.Mapper;
import ru.malov.config.MapperConfig;
import ru.malov.domain.model.car.Car;
import ru.malov.persistence.entity.car.CarEntity;

@Mapper(
        config = MapperConfig.class,
        uses = {CarModelMapper.class}
)
public interface CarMapper {

    CarEntity toEntity(Car car);

    Car toDomain(CarEntity entity);
}
