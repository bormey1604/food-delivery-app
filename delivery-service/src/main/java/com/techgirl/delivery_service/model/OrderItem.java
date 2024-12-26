package com.techgirl.delivery_service.model;

import java.math.BigDecimal;

public record OrderItem(
        String itemName,
        BigDecimal price
) {
    public static OrderItem instance(String itemName, BigDecimal price) {
        return new OrderItem(itemName, price);
    }
}
