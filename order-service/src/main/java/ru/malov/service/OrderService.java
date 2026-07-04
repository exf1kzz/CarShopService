package ru.malov.service;

import org.springframework.stereotype.Service;
import ru.malov.domain.exeption.EntityNotFoundException;
import ru.malov.domain.model.order.CustomOrder;
import ru.malov.domain.model.order.StockOrder;
import ru.malov.domain.model.user.User;
import ru.malov.messaging.OutboxService;
import ru.malov.repository.abstractions.CustomOrderRepository;
import ru.malov.repository.abstractions.StockOrderRepository;
import ru.malov.repository.abstractions.UserRepository;
import ru.malov.security.service.CurrentUserService;
import ru.malov.security.service.OrderSecurityService;

import java.util.List;
import java.util.UUID;

@Service
public class OrderService {
    private final StockOrderRepository _stockOrderRepository;
    private final CustomOrderRepository _customOrderRepository;
    private final UserRepository _userRepository;
    private final CurrentUserService _currentUserService;
    private final OrderSecurityService _orderSecurityService;
    private final OutboxService outboxService;

    public OrderService(StockOrderRepository stockOrderRepository,
                        CustomOrderRepository customOrderRepository,
                        UserRepository userRepository,
                        CurrentUserService currentUserService,
                        OrderSecurityService orderSecurityService, OutboxService outboxService) {
        this._stockOrderRepository = stockOrderRepository;
        this._customOrderRepository = customOrderRepository;
        this._userRepository = userRepository;
        this._currentUserService = currentUserService;
        this._orderSecurityService = orderSecurityService;
        this.outboxService = outboxService;
    }

    public StockOrder CreateStockOrder(UUID orderId, UUID carId) {
        UUID userId = _currentUserService.getUserId();
        User client = _userRepository.FindById(userId);

        if (client == null) {
            throw new EntityNotFoundException("User not found");
        }

        User manager = AssignManager();
        StockOrder order = new StockOrder(orderId, client, manager, carId);

        return _stockOrderRepository.Save(order);
    }

    public void ApproveStockOrder(UUID orderId) {
        StockOrder order = _stockOrderRepository.FindById(orderId);
        order.ApproveByManager();
        _stockOrderRepository.Save(order);
    }

    public void StockOrderWaitForPayment(UUID orderId) {
        StockOrder order = _stockOrderRepository.FindById(orderId);
        order.WaitForPayment();
        _stockOrderRepository.Save(order);
    }

    public void PayStockOrder(UUID orderId) {
        StockOrder order = _stockOrderRepository.FindById(orderId);
        order.Pay();
        _stockOrderRepository.Save(order);
        outboxService.saveOrderSent(order.getId(), "STOCK", UUID.randomUUID().toString());
    }

    public void ReadyForPickUpStockOrder(UUID orderId) {
        StockOrder order = _stockOrderRepository.FindById(orderId);
        order.MarkReadyForPickup();
        _stockOrderRepository.Save(order);
    }

    public void CompleteStockOrder(UUID orderId) {
        StockOrder order = _stockOrderRepository.FindById(orderId);
        order.Complete();
        _stockOrderRepository.Save(order);
    }

    public void CancelStockOrder(UUID orderId) {
        StockOrder order = _stockOrderRepository.FindById(orderId);
        order.Cancel();
        _stockOrderRepository.Save(order);
    }

    public StockOrder GetStockOrder(UUID orderId) {
        return _stockOrderRepository.FindById(orderId);
    }

    public List<StockOrder> GetStockOrders() {
        UUID userId = _currentUserService.getUserId();

        if (_orderSecurityService.isManagerOrAdmin()) {
            return _stockOrderRepository.findAll();
        }

        return _stockOrderRepository.findAll().stream()
                .filter(order -> order.getClient().getId().equals(userId))
                .toList();
    }

    public CustomOrder CreateCustomOrder(UUID orderId, UUID configurationId) {
        UUID userId = _currentUserService.getUserId();
        User client = _userRepository.FindById(userId);

        if (client == null) {
            throw new EntityNotFoundException("User not found");
        }

        User manager = AssignManager();
        CustomOrder order = new CustomOrder(orderId, client, manager, configurationId);

        return _customOrderRepository.Save(order);
    }

    public void ApproveCustomOrder(UUID orderId) {
        CustomOrder order = _customOrderRepository.FindById(orderId);
        order.ApprovedByWarehouse();
        _customOrderRepository.Save(order);
    }

    public void CustomOrderWaitForPayment(UUID orderId) {
        CustomOrder order = _customOrderRepository.FindById(orderId);
        order.WaitForPayment();
        _customOrderRepository.Save(order);
    }

    public void PayCustomOrder(UUID orderId) {
        CustomOrder order = _customOrderRepository.FindById(orderId);
        order.Pay();
        _customOrderRepository.Save(order);
        outboxService.saveOrderSent(order.getId(), "CUSTOM", UUID.randomUUID().toString());
    }

    public void CustomOrderWaitForDelivery(UUID orderId) {
        CustomOrder order = _customOrderRepository.FindById(orderId);
        order.WaitForDelivery();
        _customOrderRepository.Save(order);
    }

    public void ReadyForPickUpCustomOrder(UUID orderId) {
        CustomOrder order = _customOrderRepository.FindById(orderId);
        order.MarkReadyForPickup();
        _customOrderRepository.Save(order);
    }

    public void CompleteCustomOrder(UUID orderId) {
        CustomOrder order = _customOrderRepository.FindById(orderId);
        order.Complete();
        _customOrderRepository.Save(order);
    }

    public void CancelCustomOrder(UUID orderId) {
        CustomOrder order = _customOrderRepository.FindById(orderId);
        order.Cancel();
        _customOrderRepository.Save(order);
    }

    public CustomOrder GetCustomOrder(UUID orderId) {
        return _customOrderRepository.FindById(orderId);
    }

    public List<CustomOrder> GetCustomOrders() {
        UUID userId = _currentUserService.getUserId();

        if (_orderSecurityService.isManagerOrAdmin()) {
            return _customOrderRepository.FindAll();
        }

        return _customOrderRepository.FindAll().stream()
                .filter(order -> order.getClient().getId().equals(userId))
                .toList();
    }

    private static final UUID DEFAULT_MANAGER_ID =
            UUID.fromString("22222222-2222-2222-2222-222222222222");

    private User AssignManager() {
        User manager = _userRepository.FindById(DEFAULT_MANAGER_ID);
        if (manager == null) {
            throw new EntityNotFoundException("Manager not found");
        }
        return manager;
    }
}
