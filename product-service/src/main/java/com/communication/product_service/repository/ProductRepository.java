package com.communication.product_service.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.communication.product_service.model.Product;

public interface ProductRepository extends MongoRepository<Product, String> {
    Product findByProductNumber(String productNumber);
}
