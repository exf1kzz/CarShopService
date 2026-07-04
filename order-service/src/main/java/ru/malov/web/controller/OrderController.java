package ru.malov.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.malov.service.OrderService;
import ru.malov.web.dto.request.CustomOrderRequestDto;
import ru.malov.web.dto.request.StockOrderRequestDto;
import ru.malov.web.dto.response.CustomOrderResponseDto;
import ru.malov.web.dto.response.StockOrderResponseDto;
import ru.malov.web.mapper.OrderApiMapper;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService service;
    private final OrderApiMapper mapper;

    @PostMapping("/stock")
    @PreAuthorize("hasRole('USER')")
    public StockOrderResponseDto createStock(@RequestBody StockOrderRequestDto dto) {
        return mapper.toStockDto(service.CreateStockOrder(UUID.randomUUID(), dto.getCarId()));
    }

    @PostMapping("/{id}/approve-stock")
    @PreAuthorize("hasRole('MANAGER')")
    public void approveStock(@PathVariable UUID id) {
        service.ApproveStockOrder(id);
    }

    @PostMapping("/{id}/wait-for-payment-stock")
    @PreAuthorize("hasAnyRole('MANAGER', 'ADMIN')")
    public void waitForPaymentStock(@PathVariable UUID id) {
        service.StockOrderWaitForPayment(id);
    }

    @PostMapping("/{id}/pay-stock")
    @PreAuthorize("@orderSecurity.isStockOrderOwner(#id)")
    public void payStock(@PathVariable UUID id) {
        service.PayStockOrder(id);
    }

    @PostMapping("/{id}/ready-for-pickup-stock")
    @PreAuthorize("hasRole('MANAGER')")
    public void readyForPickUpStock(@PathVariable UUID id) {
        service.ReadyForPickUpStockOrder(id);
    }

    @PostMapping("/{id}/complete-stock")
    @PreAuthorize("hasRole('MANAGER')")
    public void completeStock(@PathVariable UUID id) {
        service.CompleteStockOrder(id);
    }

    @PostMapping("/{id}/cancel-stock")
    @PreAuthorize("@orderSecurity.isStockOrderOwner(#id)")
    public void cancelStock(@PathVariable UUID id) {
        service.CancelStockOrder(id);
    }

    @GetMapping("/stock/{id}")
    @PreAuthorize("@orderSecurity.isStockOrderOwner(#id) or hasAnyRole('MANAGER', 'ADMIN')")
    public StockOrderResponseDto getStock(@PathVariable UUID id) {
        return mapper.toStockDto(service.GetStockOrder(id));
    }

    @GetMapping("/stock/get-all")
    @PreAuthorize("hasAnyRole('USER', 'MANAGER', 'ADMIN')")
    public List<StockOrderResponseDto> getStockOrders() {
        return service.GetStockOrders().stream()
                .map(mapper::toStockDto)
                .toList();
    }

    @PostMapping("/custom")
    @PreAuthorize("hasRole('USER')")
    public CustomOrderResponseDto createCustom(@RequestBody CustomOrderRequestDto dto) {
        return mapper.toCustomDto(service.CreateCustomOrder(UUID.randomUUID(), dto.getConfigurationId()));
    }

    @PostMapping("/{id}/approve-custom")
    @PreAuthorize("hasRole('WAREHOUSE_MANAGER')")
    public void approveCustom(@PathVariable UUID id) {
        service.ApproveCustomOrder(id);
    }

    @PostMapping("/{id}/wait-for-payment-custom")
    @PreAuthorize("hasAnyRole('WAREHOUSE_MANAGER', 'ADMIN')")
    public void waitForPaymentCustom(@PathVariable UUID id) {
        service.CustomOrderWaitForPayment(id);
    }

    @PostMapping("/{id}/pay-custom")
    @PreAuthorize("@orderSecurity.isCustomOrderOwner(#id)")
    public void payCustom(@PathVariable UUID id) {
        service.PayCustomOrder(id);
    }

    @PostMapping("/{id}/wait-for-delivery-custom")
    @PreAuthorize("hasRole('WAREHOUSE_MANAGER')")
    public void waitForDeliveryCustom(@PathVariable UUID id) {
        service.CustomOrderWaitForDelivery(id);
    }

    @PostMapping("/{id}/ready-for-pickup-custom")
    @PreAuthorize("hasRole('WAREHOUSE_MANAGER')")
    public void readyForPickUpCustom(@PathVariable UUID id) {
        service.ReadyForPickUpCustomOrder(id);
    }

    @PostMapping("/{id}/complete-custom")
    @PreAuthorize("hasRole('WAREHOUSE_MANAGER')")
    public void completeCustom(@PathVariable UUID id) {
        service.CompleteCustomOrder(id);
    }

    @PostMapping("/{id}/cancel-custom")
    @PreAuthorize("@orderSecurity.isCustomOrderOwner(#id)")
    public void cancelCustom(@PathVariable UUID id) {
        service.CancelCustomOrder(id);
    }

    @GetMapping("/custom/{id}")
    @PreAuthorize("@orderSecurity.isCustomOrderOwner(#id) or hasAnyRole('MANAGER', 'ADMIN')")
    public CustomOrderResponseDto getCustom(@PathVariable UUID id) {
        return mapper.toCustomDto(service.GetCustomOrder(id));
    }

    @GetMapping("/custom/get-all")
    @PreAuthorize("hasAnyRole('USER', 'MANAGER', 'ADMIN')")
    public List<CustomOrderResponseDto> getCustomOrders() {
        return service.GetCustomOrders().stream()
                .map(mapper::toCustomDto)
                .toList();
    }
}
