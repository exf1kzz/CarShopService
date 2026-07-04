package ru.malov.repository.abstractions;

import ru.malov.domain.model.order.CustomOrder;

import java.util.List;
import java.util.UUID;

public interface CustomOrderRepository {

    CustomOrder Save(CustomOrder order);

    CustomOrder FindById(UUID id);

    List<CustomOrder> FindAll();

    void Delete(CustomOrder order);
}
