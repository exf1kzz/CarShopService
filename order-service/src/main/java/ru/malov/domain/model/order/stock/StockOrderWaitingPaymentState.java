package ru.malov.domain.model.order.stock;

import ru.malov.domain.enums.StockOrderStatus;
import ru.malov.domain.model.order.StockOrder;

public class StockOrderWaitingPaymentState extends BaseStockOrderState {

    @Override
    public void Pay(StockOrder order) {
        order.SetState(new StockOrderPaidState());
    }

    @Override
    public void Cancel(StockOrder order) {
        order.SetState(new StockOrderCancelledState());
    }

    @Override
    public StockOrderStatus GetStatus() {
        return StockOrderStatus.WAITING_PAYMENT;
    }
}
