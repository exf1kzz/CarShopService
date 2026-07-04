package ru.malov.domain.model.order.custom;

import ru.malov.domain.enums.CustomOrderStatus;
import ru.malov.domain.model.order.CustomOrder;

public class CustomOrderPaidState extends BaseCustomOrderState {

    @Override
    public void WaitForDelivery(CustomOrder order) {
        order.SetState(new CustomOrderWaitingDeliveryState());
    }

    @Override
    public CustomOrderStatus GetStatus() {
        return CustomOrderStatus.PAID;
    }
}
