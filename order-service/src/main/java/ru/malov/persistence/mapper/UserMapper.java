package ru.malov.persistence.mapper;

import org.mapstruct.Mapper;
import ru.malov.config.MapperConfig;
import ru.malov.domain.model.user.User;
import ru.malov.persistence.entity.user.UserEntity;

@Mapper(config = MapperConfig.class)
public interface UserMapper {

    User toDomain(UserEntity entity);

    UserEntity toEntity(User user);
}
