package com.techgirl.delivery_service.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.techgirl.delivery_service.model.OrderCreateEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class DeliveryListener {

    private final ObjectMapper objectMapper;
    private final DeliverOrderComponent deliverOrderComponent;

    @RabbitListener(queues = "${spring.rabbitmq.queue}")
    public void handleDeliveryEvents(String event) throws JsonProcessingException {
        try {
            log.info("Delivery event received: {}", event);
            OrderCreateEvent orderCreateEvent = objectMapper.readValue(event, OrderCreateEvent.class);
            deliverOrderComponent.deliverOrder(orderCreateEvent.orderItems());
        } catch (Exception exception) {
            log.error("Event handling error", exception);
            throw exception;
        }
    }

}
