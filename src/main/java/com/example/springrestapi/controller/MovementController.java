package com.example.springrestapi.controller;

import com.example.springrestapi.model.Movement;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/movements")
public class MovementController {
    private static List<Movement> movements = new ArrayList<>();
    private static int movementIdCounter = 1;

    static {
        movements.add(new Movement(movementIdCounter++, 1, 1, 2, 25, "TRANSFER", LocalDateTime.now(), "Transfer from main"));
        movements.add(new Movement(movementIdCounter++, 2, 1, 3, 30, "TRANSFER", LocalDateTime.now(), "Distribution"));
    }

    @GetMapping
    public List<Movement> getAllMovements() {
        return movements;
    }

    @GetMapping("/{id}")
    public Optional<Movement> getMovementById(@PathVariable int id) {
        return movements.stream().filter(m -> m.getId() == id).findFirst();
    }

    @PostMapping
    public Movement createMovement(@RequestBody Movement movement) {
        if (movement.getQuantity() <= 0) {
            throw new IllegalArgumentException("Quantity must be positive");
        }
        if (movement.getFromWarehouse() == movement.getToWarehouse()) {
            throw new IllegalArgumentException("Source and destination warehouses must be different");
        }
        movement.setId(movementIdCounter++);
        movement.setMovementDate(LocalDateTime.now());
        movements.add(movement);
        return movement;
    }

    @PutMapping("/{id}")
    public Optional<Movement> updateMovement(@PathVariable int id, @RequestBody Movement updatedMovement) {
        if (updatedMovement.getQuantity() <= 0) {
            throw new IllegalArgumentException("Quantity must be positive");
        }
        if (updatedMovement.getFromWarehouse() == updatedMovement.getToWarehouse()) {
            throw new IllegalArgumentException("Source and destination warehouses must be different");
        }
        return movements.stream()
            .filter(m -> m.getId() == id)
            .findFirst()
            .map(movement -> {
                movement.setItemId(updatedMovement.getItemId());
                movement.setFromWarehouse(updatedMovement.getFromWarehouse());
                movement.setToWarehouse(updatedMovement.getToWarehouse());
                movement.setQuantity(updatedMovement.getQuantity());
                movement.setMovementType(updatedMovement.getMovementType());
                movement.setNotes(updatedMovement.getNotes());
                movement.setMovementDate(LocalDateTime.now());
                return movement;
            });
    }

    @DeleteMapping("/{id}")
    public boolean deleteMovement(@PathVariable int id) {
        return movements.removeIf(m -> m.getId() == id);
    }
}
