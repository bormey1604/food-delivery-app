package com.techgirl.delivery_service.model;

import java.util.List;

public record OrderCreateEvent(
        List<OrderItem> orderItems
) {
    public static OrderCreateEvent instance(List<OrderItem> orderItems) {
        return new OrderCreateEvent(orderItems);
    }
}