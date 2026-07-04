package ru.malov.persistence.mapper;

import org.mapstruct.Mapper;
import ru.malov.config.MapperConfig;
import ru.malov.domain.model.order.TestDriveRequest;
import ru.malov.persistence.entity.order.TestDriveRequestEntity;

@Mapper(
        config = MapperConfig.class,
        uses = {UserMapper.class}
)
public interface TestDriveMapper {

    TestDriveRequest toDomain(TestDriveRequestEntity entity);

    TestDriveRequestEntity toEntity(TestDriveRequest domain);
}
