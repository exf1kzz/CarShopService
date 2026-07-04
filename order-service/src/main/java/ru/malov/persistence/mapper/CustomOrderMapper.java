package ru.malov.persistence.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.malov.domain.model.order.CustomOrder;
import ru.malov.domain.model.order.custom.CustomOrderStateFactory;
import ru.malov.persistence.entity.order.CustomOrderEntity;

@Component
@RequiredArgsConstructor
public class CustomOrderMapper {

    private final UserMapper userMapper;

    public CustomOrder toDomain(CustomOrderEntity entity) {

        CustomOrder order = new CustomOrder(
                entity.getId(),
                userMapper.toDomain(entity.getClient()),
                userMapper.toDomain(entity.getManager()),
                entity.getConfigurationId()
        );

        order.SetState(CustomOrderStateFactory.fromStatus(entity.getStatus()));

        return order;
    }

    public CustomOrderEntity toEntity(CustomOrder domain) {

        CustomOrderEntity entity = new CustomOrderEntity();

        entity.setId(domain.getId());
        entity.setClient(userMapper.toEntity(domain.getClient()));
        entity.setManager(userMapper.toEntity(domain.getManager()));
        entity.setConfigurationId(domain.getConfigurationId());
        entity.setStatus(domain.getState().GetStatus());

        return entity;
    }
}
