package ru.malov.security.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ru.malov.domain.model.order.CustomOrder;
import ru.malov.domain.model.order.StockOrder;
import ru.malov.repository.abstractions.CustomOrderRepository;
import ru.malov.repository.abstractions.StockOrderRepository;

import java.util.UUID;

@Service("orderSecurity")
@RequiredArgsConstructor
public class OrderSecurityService {

    private final StockOrderRepository stockOrderRepository;
    private final CustomOrderRepository customOrderRepository;
    private final CurrentUserService currentUserService;

    public boolean isStockOrderOwner(UUID orderId) {
        UUID userId = currentUserService.getUserId();
        StockOrder order = stockOrderRepository.FindById(orderId);

        if (order == null) {
            return false;
        }

        return order.getClient().getId().equals(userId);
    }

    public boolean isCustomOrderOwner(UUID orderId) {
        UUID userId = currentUserService.getUserId();
        CustomOrder order = customOrderRepository.FindById(orderId);

        if (order == null) {
            return false;
        }

        return order.getClient().getId().equals(userId);
    }

    public boolean isManagerOrAdmin() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        return auth.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_MANAGER") || a.getAuthority().equals("ROLE_ADMIN"));
    }
}
