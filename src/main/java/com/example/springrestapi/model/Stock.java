package com.example.springrestapi.model;

import java.time.LocalDateTime;

public class Stock {
    private int id;
    private int itemId;
    private int warehouseId;
    private int quantity;
    private LocalDateTime lastUpdated;

    public Stock() {}

    public Stock(int id, int itemId, int warehouseId, int quantity, LocalDateTime lastUpdated) {
        this.id = id;
        this.itemId = itemId;
        this.warehouseId = warehouseId;
        this.quantity = quantity;
        this.lastUpdated = lastUpdated;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getItemId() { return itemId; }
    public void setItemId(int itemId) { this.itemId = itemId; }
    public int getWarehouseId() { return warehouseId; }
    public void setWarehouseId(int warehouseId) { this.warehouseId = warehouseId; }
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
    public LocalDateTime getLastUpdated() { return lastUpdated; }
    public void setLastUpdated(LocalDateTime lastUpdated) { this.lastUpdated = lastUpdated; }
}
