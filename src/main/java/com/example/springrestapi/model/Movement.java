package com.example.springrestapi.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name = "movement", checks = {
    @CheckConstraint(name = "check_quantity_positive", expression = "quantity > 0"),
    @CheckConstraint(name = "check_different_warehouses", expression = "from_warehouse_id != to_warehouse_id")
})
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Movement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "item_id", nullable = false)
    private Long itemId;

    @Column(name = "from_warehouse_id", nullable = false)
    private Long fromWarehouseId;

    @Column(name = "to_warehouse_id", nullable = false)
    private Long toWarehouseId;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @Column(name = "movement_date")
    private LocalDateTime movementDate;

    @Column(name = "notes", length = 500)
    private String notes;
}
