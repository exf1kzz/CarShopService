package ru.malov.domain.model.order.stock;

import ru.malov.domain.enums.StockOrderStatus;
import ru.malov.domain.model.order.StockOrder;

public class StockOrderPaidState extends BaseStockOrderState {

    @Override
    public void MarkReadyForPickup(StockOrder order) {
        order.SetState(new StockOrderReadyForPickupState());
    }

    @Override
    public StockOrderStatus GetStatus() {
        return StockOrderStatus.PAID;
    }
}
