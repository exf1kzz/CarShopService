package ru.malov.domain.model.order;

import lombok.Getter;
import ru.malov.domain.model.order.custom.CustomOrderCreatedState;
import ru.malov.domain.model.order.custom.CustomOrderState;
import ru.malov.domain.model.user.User;

import java.util.UUID;

@Getter
public class CustomOrder extends Order {
    private final UUID configurationId;
    private CustomOrderState state;

    public CustomOrder(UUID id, User client, User manager, UUID configurationId) {
        super(id, client, manager);
        this.configurationId = configurationId;
        this.state = new CustomOrderCreatedState();
    }

    public void SetState(CustomOrderState state) {
        this.state = state;
    }

    public void ApprovedByWarehouse() {
        state.ApproveByWarehouse(this);
    }

    public void WaitForPayment() {
        state.WaitForPayment(this);
    }

    public void Pay() {
        state.Pay(this);
    }

    public void WaitForDelivery() {
        state.WaitForDelivery(this);
    }

    public void MarkReadyForPickup() {
        state.MarkReadyForPickup(this);
    }

    public void Complete() {
        state.Complete(this);
    }

    public void Cancel() {
        state.Cancel(this);
    }
}
