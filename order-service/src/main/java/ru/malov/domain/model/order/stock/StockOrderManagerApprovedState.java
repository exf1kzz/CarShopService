package ru.malov.domain.model.order.stock;

import ru.malov.domain.enums.StockOrderStatus;
import ru.malov.domain.model.order.StockOrder;

public class StockOrderManagerApprovedState extends BaseStockOrderState {

    @Override
    public void WaitForPayment(StockOrder order) {
        order.SetState(new StockOrderWaitingPaymentState());
    }

    @Override
    public void Cancel(StockOrder order) {
        order.SetState(new StockOrderCancelledState());
    }

    @Override
    public StockOrderStatus GetStatus() {
        return StockOrderStatus.MANAGER_APPROVED;
    }
}
