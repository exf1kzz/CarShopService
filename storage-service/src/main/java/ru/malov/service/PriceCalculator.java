package ru.malov.service;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ru.malov.domain.model.car.Car;
import ru.malov.domain.model.component.ComponentOption;

import java.math.BigDecimal;
import java.util.List;

@Service
public class PriceCalculator {

    public BigDecimal CalculateTotalPrice(BigDecimal basePrice, List<ComponentOption> selectedOptions) {
        BigDecimal totalPrice = basePrice;

        if (selectedOptions != null && !selectedOptions.isEmpty()) {
            for (ComponentOption option : selectedOptions) {
                if (option.getAdditionalPrice() != null) {
                    totalPrice = totalPrice.add(option.getAdditionalPrice());
                }
            }
        }

        return totalPrice;
    }
}
