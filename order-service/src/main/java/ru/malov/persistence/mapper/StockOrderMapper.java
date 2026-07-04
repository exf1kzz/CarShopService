package ru.malov.persistence.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.malov.domain.model.order.StockOrder;
import ru.malov.domain.model.order.stock.StockOrderStateFactory;
import ru.malov.persistence.entity.order.StockOrderEntity;

@Component
@RequiredArgsConstructor
public class StockOrderMapper {

    private final UserMapper userMapper;

    public StockOrder toDomain(StockOrderEntity entity) {

        StockOrder order = new StockOrder(
                entity.getId(),
                userMapper.toDomain(entity.getClient()),
                userMapper.toDomain(entity.getManager()),
                entity.getCarId()
        );

        order.SetState(StockOrderStateFactory.fromStatus(entity.getStatus()));

        return order;
    }

    public StockOrderEntity toEntity(StockOrder domain) {

        StockOrderEntity entity = new StockOrderEntity();

        entity.setId(domain.getId());
        entity.setClient(userMapper.toEntity(domain.getClient()));
        entity.setManager(userMapper.toEntity(domain.getManager()));
        entity.setCarId(domain.getCarId());
        entity.setStatus(domain.getState().GetStatus());

        return entity;
    }
}
