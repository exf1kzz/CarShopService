package ru.malov.domain.model.order.custom;

import ru.malov.domain.enums.CustomOrderStatus;
import ru.malov.domain.model.order.CustomOrder;

public class CustomOrderCreatedState extends BaseCustomOrderState {

    @Override
    public void ApproveByWarehouse(CustomOrder order) {
        order.SetState(new CustomOrderWarehouseApprovedState());
    }

    @Override
    public void Cancel(CustomOrder order) {
        order.SetState(new CustomOrderCancelledState());
    }

    @Override
    public CustomOrderStatus GetStatus() {
        return CustomOrderStatus.CREATED;
    }
}
