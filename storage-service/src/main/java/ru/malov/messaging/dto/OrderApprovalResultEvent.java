package ru.malov.messaging.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.Instant;
import java.util.UUID;

@Data
@AllArgsConstructor
public class OrderApprovalResultEvent {
    private UUID eventId;
    private String traceId;
    private UUID orderId;
    private String orderType;
    private String result;
    private Instant occurredAt;
}
