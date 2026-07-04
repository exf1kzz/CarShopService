package ru.malov.domain.model.order.custom;

import ru.malov.domain.enums.CustomOrderStatus;
import ru.malov.domain.model.order.CustomOrder;

public class CustomOrderWaitingDeliveryState extends BaseCustomOrderState {

    @Override
    public void MarkReadyForPickup(CustomOrder order) {
        order.SetState(new CustomOrderReadyForPickupState());
    }

    @Override
    public CustomOrderStatus GetStatus() {
        return CustomOrderStatus.WAITING_DELIVERY;
    }
}
