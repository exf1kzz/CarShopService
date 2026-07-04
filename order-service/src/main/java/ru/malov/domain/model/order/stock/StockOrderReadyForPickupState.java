package ru.malov.domain.model.order.stock;

import ru.malov.domain.enums.StockOrderStatus;
import ru.malov.domain.model.order.StockOrder;

public class StockOrderReadyForPickupState extends BaseStockOrderState {

    @Override
    public void Complete(StockOrder order) {
        order.SetState(new StockOrderCompletedState());
    }

    @Override
    public StockOrderStatus GetStatus() {
        return StockOrderStatus.READY_FOR_PICKUP;
    }
}
