package com.communication.inventory_service.model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "stock_items")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StockItem {
    @Id
    private String id;
    private String productNumber;
    private int quantity;



}
