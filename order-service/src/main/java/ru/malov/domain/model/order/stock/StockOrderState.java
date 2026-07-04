package ru.malov.domain.model.order.stock;

import ru.malov.domain.enums.StockOrderStatus;
import ru.malov.domain.model.order.StockOrder;

public interface StockOrderState {
    void ApproveByManager(StockOrder order);

    void WaitForPayment(StockOrder order);

    void Pay(StockOrder order);

    void MarkReadyForPickup(StockOrder order);

    void Complete(StockOrder order);

    void Cancel(StockOrder order);

    StockOrderStatus GetStatus();
}
