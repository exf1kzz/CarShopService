package ru.malov.domain.model.order.custom;

import ru.malov.domain.enums.CustomOrderStatus;

public class CustomOrderCancelledState extends BaseCustomOrderState {

    @Override
    public CustomOrderStatus GetStatus() {
        return CustomOrderStatus.CANCELLED;
    }
}
