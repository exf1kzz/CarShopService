package ru.malov.repository.abstractions;

import ru.malov.domain.model.order.StockOrder;

import java.util.List;
import java.util.UUID;

public interface StockOrderRepository {

    StockOrder Save(StockOrder order);

    StockOrder FindById(UUID id);

    List<StockOrder> findAll();

    void delete(StockOrder order);
}
