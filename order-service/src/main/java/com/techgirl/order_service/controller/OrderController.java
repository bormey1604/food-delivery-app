package com.techgirl.order_service.controller;

import com.techgirl.order_service.model.*;
import com.techgirl.order_service.repository.MenuRepository;
import com.techgirl.order_service.service.OrderEventPublisher;
import com.techgirl.order_service.utils.MenuItemMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class OrderController {

    private final MenuRepository menuRepository;
    private final OrderEventPublisher orderEventPublisher;

    @GetMapping("/menu")
    public List<MenuResponse> getMenu() {
        return MenuItemMapper.toResponse(menuRepository.findAll());
    }

    @PostMapping("/menu")
    public MenuResponse addItem(@RequestBody CreateMenuItemRequest createMenuItemRequest) {
        MenuEntity menuEntity = MenuEntity.builder()
                .name(createMenuItemRequest.name())
                .price(createMenuItemRequest.price())
                .build();
        menuRepository.save(menuEntity);
        return MenuItemMapper.toResponse(menuEntity);
    }

    @PostMapping("/order")
    public void createOrder(@RequestBody CreateOrderRequest request) {
        List<MenuEntity> menu = menuRepository.findAllByIdIn(request.itemIds());
        List<OrderItem> orderItems = new ArrayList<>();
        menu.forEach(
                menuItem -> orderItems.add(OrderItem.instance(menuItem.getName(), menuItem.getPrice()))
        );
        orderEventPublisher.publishOrderCreatedEvent(OrderCreateEvent.instance(orderItems));
    }

}