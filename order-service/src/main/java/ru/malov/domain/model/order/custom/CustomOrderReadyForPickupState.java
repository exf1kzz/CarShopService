package ru.malov.domain.model.order.custom;

import ru.malov.domain.enums.CustomOrderStatus;
import ru.malov.domain.model.order.CustomOrder;

public class CustomOrderReadyForPickupState extends BaseCustomOrderState {

    @Override
    public void Complete(CustomOrder order) {
        order.SetState(new CustomOrderCompleteState());
    }

    @Override
    public CustomOrderStatus GetStatus() {
        return CustomOrderStatus.READY_TO_PICKUP;
    }
}
