package com.communication.inventory_service.service;
import com.communication.inventory_service.model.StockItem;
import com.communication.inventory_service.repository.StockItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventoryService {

    private final StockItemRepository stockItemRepository;

    @Autowired
    public InventoryService(StockItemRepository stockItemRepository) {
        this.stockItemRepository = stockItemRepository;
    }

    public StockItem addStock(StockItem stockItem) {
        return stockItemRepository.save(stockItem);
    }

    public StockItem getStockByProductNumber(String productNumber) {
        return stockItemRepository.findByProductNumber(productNumber);
    }

    public List<StockItem> getAllStocks() {
        return stockItemRepository.findAll();
    }

    public boolean decreaseStock(String productNumber, int quantity) {
        StockItem item = stockItemRepository.findByProductNumber(productNumber);
        if (item != null && item.getQuantity() >= quantity) {
            item.setQuantity(item.getQuantity() - quantity);
            stockItemRepository.save(item);
            return true;
        }
        return false;
    }

    public void increaseStock(String productNumber, int quantity) {
        StockItem item = stockItemRepository.findByProductNumber(productNumber);
        if (item != null) {
            item.setQuantity(item.getQuantity() + quantity);
            stockItemRepository.save(item);
        }
    }
}
