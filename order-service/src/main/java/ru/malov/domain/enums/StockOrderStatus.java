package ru.malov.domain.enums;

public enum StockOrderStatus {
    CREATED,
    MANAGER_APPROVED,
    WAITING_PAYMENT,
    PAID,
    READY_FOR_PICKUP,
    COMPLETED,
    CANCELLED
}
