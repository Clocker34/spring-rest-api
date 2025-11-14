package com.example.springrestapi.controller;

import com.example.springrestapi.model.Warehouse;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/warehouses")
public class WarehouseController {
    private static List<Warehouse> warehouses = new ArrayList<>();
    private static int warehouseIdCounter = 1;

    static {
        warehouses.add(new Warehouse(warehouseIdCounter++, "Main Warehouse", "New York", "John Smith"));
        warehouses.add(new Warehouse(warehouseIdCounter++, "East Warehouse", "Boston", "Jane Doe"));
        warehouses.add(new Warehouse(warehouseIdCounter++, "West Warehouse", "Los Angeles", "Bob Johnson"));
    }

    @GetMapping
    public List<Warehouse> getAllWarehouses() {
        return warehouses;
    }

    @GetMapping("/{id}")
    public Optional<Warehouse> getWarehouseById(@PathVariable int id) {
        return warehouses.stream().filter(w -> w.getId() == id).findFirst();
    }

    @PostMapping
    public Warehouse createWarehouse(@RequestBody Warehouse warehouse) {
        warehouse.setId(warehouseIdCounter++);
        warehouses.add(warehouse);
        return warehouse;
    }

    @PutMapping("/{id}")
    public Optional<Warehouse> updateWarehouse(@PathVariable int id, @RequestBody Warehouse updatedWarehouse) {
        return warehouses.stream()
            .filter(w -> w.getId() == id)
            .findFirst()
            .map(warehouse -> {
                warehouse.setName(updatedWarehouse.getName());
                warehouse.setLocation(updatedWarehouse.getLocation());
                warehouse.setManager(updatedWarehouse.getManager());
                return warehouse;
            });
    }

    @DeleteMapping("/{id}")
    public boolean deleteWarehouse(@PathVariable int id) {
        return warehouses.removeIf(w -> w.getId() == id);
    }
}
