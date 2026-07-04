package ru.malov.domain.model.order.custom;

import ru.malov.domain.enums.CustomOrderStatus;

public class CustomOrderCompleteState extends BaseCustomOrderState {

    @Override
    public CustomOrderStatus GetStatus() {
        return CustomOrderStatus.COMPLETED;
    }
}
