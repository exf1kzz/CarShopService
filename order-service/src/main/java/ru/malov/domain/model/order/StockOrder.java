package ru.malov.domain.model.order;

import lombok.Getter;
import ru.malov.domain.model.order.stock.StockOrderCreatedState;
import ru.malov.domain.model.order.stock.StockOrderState;
import ru.malov.domain.model.user.User;

import java.util.UUID;

@Getter
public class StockOrder extends Order {
    private final UUID carId;
    private StockOrderState state;

    public StockOrder(UUID id, User client, User manager, UUID carId) {
        super(id, client, manager);
        this.carId = carId;
        this.state = new StockOrderCreatedState();
    }

    public void SetState(StockOrderState state) {
        this.state = state;
    }

    public void ApproveByManager() {
        state.ApproveByManager(this);
    }

    public void WaitForPayment() {
        state.WaitForPayment(this);
    }

    public void Pay() {
        state.Pay(this);
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
