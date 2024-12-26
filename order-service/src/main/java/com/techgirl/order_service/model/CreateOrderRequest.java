package com.techgirl.order_service.model;

import java.util.List;
import java.util.UUID;

public record CreateOrderRequest(
        List<UUID> itemIds
) {
}