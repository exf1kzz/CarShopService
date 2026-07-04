package ru.malov.persistence.mapper;

import org.mapstruct.Mapper;
import ru.malov.config.MapperConfig;
import ru.malov.domain.model.component.Interior;
import ru.malov.domain.model.component.SteeringWheel;
import ru.malov.domain.model.component.Transmission;
import ru.malov.domain.model.component.Wheels;
import ru.malov.persistence.entity.component.InteriorEntity;
import ru.malov.persistence.entity.component.SteeringWheelEntity;
import ru.malov.persistence.entity.component.TransmissionEntity;
import ru.malov.persistence.entity.component.WheelsEntity;

@Mapper(config = MapperConfig.class, uses = ComponentOptionMapper.class)
public interface ComponentMapper {

    Wheels toDomain(WheelsEntity entity);
    WheelsEntity toEntity(Wheels domain);

    Interior toDomain(InteriorEntity entity);
    InteriorEntity toEntity(Interior domain);

    Transmission toDomain(TransmissionEntity entity);
    TransmissionEntity toEntity(Transmission domain);

    SteeringWheel toDomain(SteeringWheelEntity entity);
    SteeringWheelEntity toEntity(SteeringWheel domain);
}
