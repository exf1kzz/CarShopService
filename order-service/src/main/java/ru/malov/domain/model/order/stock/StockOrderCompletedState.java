package ru.malov.domain.model.order.stock;

import ru.malov.domain.enums.StockOrderStatus;

public class StockOrderCompletedState extends BaseStockOrderState {

    @Override
    public StockOrderStatus GetStatus() {
        return StockOrderStatus.COMPLETED;
    }
}
