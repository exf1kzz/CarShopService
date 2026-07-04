package ru.malov.domain.model.order.custom;

import ru.malov.domain.enums.CustomOrderStatus;
import ru.malov.domain.model.order.CustomOrder;

public class CustomOrderWaitForPaymentState extends BaseCustomOrderState {

    @Override
    public void Pay(CustomOrder order) {
        order.SetState(new CustomOrderPaidState());
    }

    @Override
    public void Cancel(CustomOrder order) {
        order.SetState(new CustomOrderCancelledState());
    }

    @Override
    public CustomOrderStatus GetStatus() {
        return CustomOrderStatus.WAITING_PAYMENT;
    }
}
