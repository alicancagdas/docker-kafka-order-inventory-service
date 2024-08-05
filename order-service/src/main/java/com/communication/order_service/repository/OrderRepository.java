package com.communication.order_service.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.communication.order_service.model.Order;

public interface OrderRepository extends MongoRepository<Order, String> {
    Order findByOrderNumber(String orderNumber);
}
