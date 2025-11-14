package com.example.springrestapi.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name = "stock", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"item_id", "warehouse_id"})
})
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Stock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "item_id", nullable = false)
    private Long itemId;

    @Column(name = "warehouse_id", nullable = false)
    private Long warehouseId;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @Column(name = "last_updated")
    private LocalDateTime lastUpdated;
}
