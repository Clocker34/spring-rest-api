package com.example.springrestapi.controller;

import com.example.springrestapi.model.Stock;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/stocks")
public class StockController {
    private static List<Stock> stocks = new ArrayList<>();
    private static int stockIdCounter = 1;

    static {
        stocks.add(new Stock(stockIdCounter++, 1, 1, 100, LocalDateTime.now()));
        stocks.add(new Stock(stockIdCounter++, 2, 1, 50, LocalDateTime.now()));
        stocks.add(new Stock(stockIdCounter++, 3, 2, 75, LocalDateTime.now()));
    }

    @GetMapping
    public List<Stock> getAllStocks() {
        return stocks;
    }

    @GetMapping("/{id}")
    public Optional<Stock> getStockById(@PathVariable int id) {
        return stocks.stream().filter(s -> s.getId() == id).findFirst();
    }

    @PostMapping
    public Stock createStock(@RequestBody Stock stock) {
        if (stock.getQuantity() < 0) {
            throw new IllegalArgumentException("Quantity cannot be negative");
        }
        stock.setId(stockIdCounter++);
        stock.setLastUpdated(LocalDateTime.now());
        stocks.add(stock);
        return stock;
    }

    @PutMapping("/{id}")
    public Optional<Stock> updateStock(@PathVariable int id, @RequestBody Stock updatedStock) {
        if (updatedStock.getQuantity() < 0) {
            throw new IllegalArgumentException("Quantity cannot be negative");
        }
        return stocks.stream()
            .filter(s -> s.getId() == id)
            .findFirst()
            .map(stock -> {
                stock.setItemId(updatedStock.getItemId());
                stock.setWarehouseId(updatedStock.getWarehouseId());
                stock.setQuantity(updatedStock.getQuantity());
                stock.setLastUpdated(LocalDateTime.now());
                return stock;
            });
    }

    @DeleteMapping("/{id}")
    public boolean deleteStock(@PathVariable int id) {
        return stocks.removeIf(s -> s.getId() == id);
    }
}
