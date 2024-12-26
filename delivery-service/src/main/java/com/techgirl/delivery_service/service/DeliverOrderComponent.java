package com.techgirl.delivery_service.service;

import com.techgirl.delivery_service.model.OrderItem;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class DeliverOrderComponent {

    public void deliverOrder(List<OrderItem> orderItems) {
        BigDecimal totalPrice = BigDecimal.ZERO;
        for (OrderItem orderItem : orderItems) {
            totalPrice = totalPrice.add(orderItem.price());
        }
        System.out.println("Total price: " + totalPrice);
    }

}