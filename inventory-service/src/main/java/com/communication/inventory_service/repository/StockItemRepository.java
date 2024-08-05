package com.communication.inventory_service.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.communication.inventory_service.model.StockItem;

public interface StockItemRepository extends MongoRepository<StockItem, String> {
    StockItem findByProductNumber(String productNumber);
}
