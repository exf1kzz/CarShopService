package ru.malov.domain.model.order.stock;

import ru.malov.domain.enums.StockOrderStatus;
import ru.malov.domain.model.order.StockOrder;

public class StockOrderCreatedState extends BaseStockOrderState {

    @Override
    public void ApproveByManager(StockOrder order) {
        order.SetState(new StockOrderManagerApprovedState());
    }

    @Override
    public void Cancel(StockOrder order) {
        order.SetState(new StockOrderCancelledState());
    }

    @Override
    public StockOrderStatus GetStatus() {
        return StockOrderStatus.CREATED;
    }
}
