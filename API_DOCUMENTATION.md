# Warehouse Management API Documentation

## Overview

This is a comprehensive REST API for a warehouse management system built with Spring Boot 3.5.7 and PostgreSQL. The system manages items, warehouses, stocks, movements between warehouses, and suppliers.

## Project Structure

### Main Entities

1. **Supplier** - Suppliers of items
2. **Item** - Products in the warehouse
3. **Warehouse** - Physical warehouse locations
4. **Stock** - Item quantities in warehouses
5. **Movement** - Transfer records between warehouses

## Core Features

- Full CRUD operations for all entities
- Stock management with quantity validation
- Warehouse-to-warehouse inventory transfers
- Supplier management
- Transaction support for complex operations
- RESTful API endpoints
- PostgreSQL database integration

## Base URL

```
http://localhost:8080/api
```

## Entities

### Item
- **GET** `/api/items` - Get all items
- **GET** `/api/items/{id}` - Get item by ID
- **POST** `/api/items` - Create new item
- **PUT** `/api/items/{id}` - Update item
- **DELETE** `/api/items/{id}` - Delete item

### Warehouse
- **GET** `/api/warehouses` - Get all warehouses
- **GET** `/api/warehouses/{id}` - Get warehouse by ID
- **POST** `/api/warehouses` - Create new warehouse
- **PUT** `/api/warehouses/{id}` - Update warehouse
- **DELETE** `/api/warehouses/{id}` - Delete warehouse

### Stock
- **GET** `/api/stocks` - Get all stock records
- **GET** `/api/stocks/{id}` - Get stock by ID
- **POST** `/api/stocks` - Create stock record
- **PUT** `/api/stocks/{id}` - Update stock
- **DELETE** `/api/stocks/{id}` - Delete stock

### Movement
- **GET** `/api/movements` - Get all movements
- **GET** `/api/movements/{id}` - Get movement by ID
- **POST** `/api/movements` - Create movement
- **PUT** `/api/movements/{id}` - Update movement
- **DELETE** `/api/movements/{id}` - Delete movement

### Supplier
- **GET** `/api/suppliers` - Get all suppliers
- **GET** `/api/suppliers/{id}` - Get supplier by ID
- **POST** `/api/suppliers` - Create supplier
- **PUT** `/api/suppliers/{id}` - Update supplier
- **DELETE** `/api/suppliers/{id}` - Delete supplier

## Example Requests

### Create Item
```json
POST /api/items
Content-Type: application/json

{
  "name": "Laptop",
  "description": "High-end laptop 15-inch",
  "sku": "SKU001",
  "price": 999.99,
  "supplierId": 1
}
```

### Create Movement (Transfer)
```json
POST /api/movements
Content-Type: application/json

{
  "itemId": 1,
  "fromWarehouse": 1,
  "toWarehouse": 2,
  "quantity": 25,
  "movementType": "TRANSFER",
  "notes": "Stock redistribution"
}
```

### Create Stock
```json
POST /api/stocks
Content-Type: application/json

{
  "itemId": 1,
  "warehouseId": 1,
  "quantity": 100
}
```

## Database

PostgreSQL database with the following tables:
- supplier
- item (with foreign key to supplier)
- warehouse
- stock (with composite unique key on item_id + warehouse_id)
- movement (with checks to ensure from_warehouse != to_warehouse)

## Environment Variables

See `.env.example` for required configuration:
- DB_URL
- DB_USERNAME
- DB_PASSWORD
- SPRING_PROFILES_ACTIVE

## Business Logic

1. **Stock Validation**: Quantities cannot be negative
2. **Movement Constraints**: Cannot transfer to same warehouse
3. **Stock Consistency**: Total stock across warehouses remains consistent
4. **Uniqueness Constraints**: SKU must be unique, supplier name and email must be unique

## Future Enhancements

1. User authentication and authorization
2. Inventory forecasting
3. Automatic reordering based on minimum stock levels
4. Supplier performance analytics
5. Batch operations
6. Event notifications
7. Audit logging
