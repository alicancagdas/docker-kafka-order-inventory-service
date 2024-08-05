package com.communication.inventory_service.controller;
import com.communication.inventory_service.model.StockItem;
import com.communication.inventory_service.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/inventory")
public class InventoryController {

    private final InventoryService inventoryService;

    @Autowired
    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @PostMapping
    public ResponseEntity<StockItem> addStock(@RequestBody StockItem stockItem) {
        StockItem addedStock = inventoryService.addStock(stockItem);
        return ResponseEntity.ok(addedStock);
    }

    @GetMapping("/{productNumber}")
    public ResponseEntity<StockItem> getStock(@PathVariable String productNumber) {
        StockItem stockItem = inventoryService.getStockByProductNumber(productNumber);
        return ResponseEntity.ok(stockItem);
    }

    @GetMapping
    public ResponseEntity<List<StockItem>> getAllStocks() {
        List<StockItem> stocks = inventoryService.getAllStocks();
        return ResponseEntity.ok(stocks);
    }
}
