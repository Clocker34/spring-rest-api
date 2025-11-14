package com.example.springrestapi.controller;

import com.example.springrestapi.model.Item;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/items")
public class ItemController {
    private static List<Item> items = new ArrayList<>();
    private static int itemIdCounter = 1;

    static {
        items.add(new Item(itemIdCounter++, "Laptop", "High-end laptop", "SKU001", 999.99, 1));
        items.add(new Item(itemIdCounter++, "Mouse", "Wireless mouse", "SKU002", 25.50, 1));
        items.add(new Item(itemIdCounter++, "Keyboard", "Mechanical keyboard", "SKU003", 75.00, 1));
    }

    @GetMapping
    public List<Item> getAllItems() {
        return items;
    }

    @GetMapping("/{id}")
    public Optional<Item> getItemById(@PathVariable int id) {
        return items.stream().filter(item -> item.getId() == id).findFirst();
    }

    @PostMapping
    public Item createItem(@RequestBody Item item) {
        item.setId(itemIdCounter++);
        items.add(item);
        return item;
    }

    @PutMapping("/{id}")
    public Optional<Item> updateItem(@PathVariable int id, @RequestBody Item updatedItem) {
        return items.stream()
            .filter(item -> item.getId() == id)
            .findFirst()
            .map(item -> {
                item.setName(updatedItem.getName());
                item.setDescription(updatedItem.getDescription());
                item.setSku(updatedItem.getSku());
                item.setPrice(updatedItem.getPrice());
                item.setSupplierId(updatedItem.getSupplierId());
                return item;
            });
    }

    @DeleteMapping("/{id}")
    public boolean deleteItem(@PathVariable int id) {
        return items.removeIf(item -> item.getId() == id);
    }
}
