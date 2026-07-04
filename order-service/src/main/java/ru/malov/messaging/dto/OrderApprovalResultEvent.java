package ru.malov.messaging.dto;

import lombok.Data;

import java.time.Instant;
import java.util.UUID;

@Data
public class OrderApprovalResultEvent {
    private UUID eventId;
    private String traceId;
    private UUID orderId;
    private String orderType;
    private String result;
    private Instant occurredAt;
}
