package com.communication.inventory_service.consumner;

import com.communication.inventory_service.service.InventoryService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class InventoryConsumer {

    private final InventoryService inventoryService;

    @Autowired
    public InventoryConsumer(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @KafkaListener(topics = "order_topic", groupId = "inventory-group")
    public void consumeOrder(String message) {
        // Mesajı parse et ve işlem yap
        try {
            JSONObject order = new JSONObject(message);
            String orderType = order.getString("orderType");
            String productNumber = order.getString("productNumber");
            int quantity = order.getInt("quantity");

            if ("CREATE".equals(orderType)) {
                if (!inventoryService.decreaseStock(productNumber, quantity)) {
                    System.out.println("Stok yetersiz!");
                }
            } else if ("CANCEL".equals(orderType)) {
                inventoryService.increaseStock(productNumber, quantity);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Mesaj işlenirken hata oluştu: " + message);
        }
    }
}
