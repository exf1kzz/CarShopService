package ru.malov.domain.model.order.custom;

import ru.malov.domain.enums.CustomOrderStatus;

public class CustomOrderStateFactory {

    public static CustomOrderState fromStatus(CustomOrderStatus status) {

        return switch (status) {

            case CREATED -> new CustomOrderCreatedState();

            case WAREHOUSE_APPROVED -> new CustomOrderWarehouseApprovedState();

            case WAITING_PAYMENT -> new CustomOrderWaitForPaymentState();

            case PAID -> new CustomOrderPaidState();

            case WAITING_DELIVERY -> new CustomOrderWaitingDeliveryState();

            case READY_TO_PICKUP -> new CustomOrderReadyForPickupState();

            case COMPLETED -> new CustomOrderCompleteState();

            case CANCELLED -> new CustomOrderCancelledState();
        };
    }
}
