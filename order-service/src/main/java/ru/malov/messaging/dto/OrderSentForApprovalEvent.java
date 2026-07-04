package ru.malov.messaging.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderSentForApprovalEvent {
    private UUID eventId;
    private String traceId;
    private UUID orderId;
    private String orderType;
    private Instant occurredAt;
}
