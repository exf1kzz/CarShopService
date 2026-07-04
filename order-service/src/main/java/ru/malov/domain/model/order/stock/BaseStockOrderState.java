package ru.malov.domain.model.order.stock;

import ru.malov.domain.exeption.DomainValidationException;
import ru.malov.domain.model.order.StockOrder;

public abstract class BaseStockOrderState implements StockOrderState {

    @Override
    public void ApproveByManager(StockOrder order) {
        throw new DomainValidationException("Invalid state transition");
    }

    @Override
    public void WaitForPayment(StockOrder order) {
        throw new DomainValidationException("Invalid state transition");
    }

    @Override
    public void Pay(StockOrder order) {
        throw new DomainValidationException("Invalid state transition");
    }

    @Override
    public void MarkReadyForPickup(StockOrder order) {
        throw new DomainValidationException("Invalid state transition");
    }

    @Override
    public void Complete(StockOrder order) {
        throw new DomainValidationException("Invalid state transition");
    }

    @Override
    public void Cancel(StockOrder order) {
        throw new DomainValidationException("Invalid state transition");
    }
}
