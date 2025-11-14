package com.example.springrestapi.controller;

import com.example.springrestapi.model.Supplier;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/suppliers")
public class SupplierController {
    private static List<Supplier> suppliers = new ArrayList<>();
    private static int supplierIdCounter = 1;

    static {
        suppliers.add(new Supplier(supplierIdCounter++, "Tech Supplies Inc", "John Anderson", "john@techsupplies.com", "+1-555-0101", "123 Tech St, Silicon Valley"));
        suppliers.add(new Supplier(supplierIdCounter++, "Global Electronics", "Maria Garcia", "maria@globalelectronics.com", "+1-555-0102", "456 Electronics Ave, Austin"));
        suppliers.add(new Supplier(supplierIdCounter++, "Premium Components", "David Chen", "david@premiumcomp.com", "+1-555-0103", "789 Component Blvd, Seattle"));
    }

    @GetMapping
    public List<Supplier> getAllSuppliers() {
        return suppliers;
    }

    @GetMapping("/{id}")
    public Optional<Supplier> getSupplierById(@PathVariable int id) {
        return suppliers.stream().filter(s -> s.getId() == id).findFirst();
    }

    @PostMapping
    public Supplier createSupplier(@RequestBody Supplier supplier) {
        supplier.setId(supplierIdCounter++);
        suppliers.add(supplier);
        return supplier;
    }

    @PutMapping("/{id}")
    public Optional<Supplier> updateSupplier(@PathVariable int id, @RequestBody Supplier updatedSupplier) {
        return suppliers.stream()
            .filter(s -> s.getId() == id)
            .findFirst()
            .map(supplier -> {
                supplier.setName(updatedSupplier.getName());
                supplier.setContactPerson(updatedSupplier.getContactPerson());
                supplier.setEmail(updatedSupplier.getEmail());
                supplier.setPhone(updatedSupplier.getPhone());
                supplier.setAddress(updatedSupplier.getAddress());
                return supplier;
            });
    }

    @DeleteMapping("/{id}")
    public boolean deleteSupplier(@PathVariable int id) {
        return suppliers.removeIf(s -> s.getId() == id);
    }
}
