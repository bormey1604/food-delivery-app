package com.techgirl.order_service.model;

import java.math.BigDecimal;
import java.util.UUID;

public record MenuResponse(
        UUID id,
        String name,
        BigDecimal price
) {
    public static MenuResponse instance(UUID id, String name, BigDecimal price) {
        return new MenuResponse(id, name, price);
    }
}