package ru.malov.domain.model.order.custom;

import ru.malov.domain.enums.CustomOrderStatus;
import ru.malov.domain.model.order.CustomOrder;

public class CustomOrderWarehouseApprovedState extends BaseCustomOrderState {

    @Override
    public void WaitForPayment(CustomOrder order) {
        order.SetState(new CustomOrderWaitForPaymentState());
    }

    @Override
    public void Cancel(CustomOrder order) {
        order.SetState(new CustomOrderCancelledState());
    }

    @Override
    public CustomOrderStatus GetStatus() {
        return CustomOrderStatus.WAREHOUSE_APPROVED;
    }
}
