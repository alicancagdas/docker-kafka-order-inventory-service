package com.communication.product_service.service;

import com.communication.product_service.model.Product;
import com.communication.product_service.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    public Product getProductByProductNumber(String productNumber) {
        return productRepository.findByProductNumber(productNumber);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product updateProduct(String productNumber, Product productDetails) {
        Product product = productRepository.findByProductNumber(productNumber);
        if (product != null) {
            product.setName(productDetails.getName());
            product.setPrice(productDetails.getPrice());
            return productRepository.save(product);
        }
        return null;
    }

    public void deleteProduct(String productNumber) {
        Product product = productRepository.findByProductNumber(productNumber);
        if (product != null) {
            productRepository.delete(product);
        }
    }
}
