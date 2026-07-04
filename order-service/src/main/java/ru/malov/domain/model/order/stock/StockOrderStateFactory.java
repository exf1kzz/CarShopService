package ru.malov.domain.model.order.stock;

import ru.malov.domain.enums.StockOrderStatus;

public class StockOrderStateFactory {

    public static StockOrderState fromStatus(StockOrderStatus status) {

        return switch (status) {

            case CREATED -> new StockOrderCreatedState();

            case MANAGER_APPROVED -> new StockOrderManagerApprovedState();

            case WAITING_PAYMENT -> new StockOrderWaitingPaymentState();

            case PAID -> new StockOrderPaidState();

            case READY_FOR_PICKUP -> new StockOrderReadyForPickupState();

            case COMPLETED -> new StockOrderCompletedState();

            case CANCELLED -> new StockOrderCancelledState();
        };
    }
}
