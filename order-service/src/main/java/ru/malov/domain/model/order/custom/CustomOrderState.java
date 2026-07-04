package ru.malov.domain.model.order.custom;

import ru.malov.domain.enums.CustomOrderStatus;
import ru.malov.domain.model.order.CustomOrder;

public interface CustomOrderState {
    void ApproveByWarehouse(CustomOrder order);

    void WaitForPayment(CustomOrder order);

    void Pay(CustomOrder order);

    void WaitForDelivery(CustomOrder order);

    void MarkReadyForPickup(CustomOrder order);

    void Complete(CustomOrder order);

    void Cancel(CustomOrder order);

    CustomOrderStatus GetStatus();
}
