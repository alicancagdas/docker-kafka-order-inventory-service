package com.communication.order_service.service;

import com.communication.order_service.model.Order;
import com.communication.order_service.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    public OrderService(OrderRepository orderRepository, KafkaTemplate<String, String> kafkaTemplate) {
        this.orderRepository = orderRepository;
        this.kafkaTemplate = kafkaTemplate;
    }

    public Order createOrder(Order order) {
        order.setOrderNumber(UUID.randomUUID().toString());
        order.setOrderTime(LocalDateTime.now());
        Order createdOrder = orderRepository.save(order);

        // Kafka'ya mesaj gönder
        String message = String.format(
                "{\"orderType\": \"%s\", \"productNumber\": \"%s\", \"quantity\": %d}",
                "CREATE", // Order type is CREATE
                createdOrder.getProductNumber(),
                createdOrder.getQuantity()
        );

        kafkaTemplate.send("order_topic", message);
        return createdOrder;
    }

    public Order getOrderByOrderNumber(String orderNumber) {
        return orderRepository.findByOrderNumber(orderNumber);
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order updateOrder(String orderNumber, Order orderDetails) {
        Order order = orderRepository.findByOrderNumber(orderNumber);
        if (order != null) {
            order.setProductNumber(orderDetails.getProductNumber());
            order.setQuantity(orderDetails.getQuantity());
            return orderRepository.save(order);
        }
        return null;
    }

    public void deleteOrder(String orderNumber) {
        Order order = orderRepository.findByOrderNumber(orderNumber);
        if (order != null) {
            orderRepository.delete(order);

            // Sipariş iptal mesajı gönder
            String message = String.format(
                    "{\"orderType\": \"%s\", \"productNumber\": \"%s\", \"quantity\": %d}",
                    "CANCEL", // Order type is CANCEL
                    order.getProductNumber(),
                    order.getQuantity()
            );

            kafkaTemplate.send("order_topic", message);
        }
    }
}
