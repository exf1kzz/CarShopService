package ru.malov.domain.model.order.custom;

import ru.malov.domain.exeption.DomainValidationException;
import ru.malov.domain.model.order.CustomOrder;

public abstract class BaseCustomOrderState implements CustomOrderState {

    @Override
    public void ApproveByWarehouse(CustomOrder order) {
        throw new DomainValidationException("Invalid state transition");

    }

    @Override
    public void WaitForPayment(CustomOrder order) {
        throw new DomainValidationException("Invalid state transition");
    }

    @Override
    public void Pay(CustomOrder order) {
        throw new DomainValidationException("Invalid state transition");
    }

    @Override
    public void WaitForDelivery(CustomOrder order) {
        throw new DomainValidationException("Invalid state transition");
    }

    @Override
    public void MarkReadyForPickup(CustomOrder order) {
        throw new DomainValidationException("Invalid state transition");
    }

    @Override
    public void Complete(CustomOrder order) {
        throw new DomainValidationException("Invalid state transition");
    }

    @Override
    public void Cancel(CustomOrder order) {
        throw new DomainValidationException("Invalid state transition");
    }
}
