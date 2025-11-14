-- Create Supplier Table
CREATE TABLE IF NOT EXISTS supplier (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL UNIQUE,
    contact_person VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    phone VARCHAR(20) NOT NULL,
    address VARCHAR(500),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Create Item Table
CREATE TABLE IF NOT EXISTS item (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    sku VARCHAR(50) NOT NULL UNIQUE,
    price DECIMAL(10, 2) NOT NULL CHECK (price >= 0),
    supplier_id INTEGER NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_item_supplier FOREIGN KEY (supplier_id) REFERENCES supplier(id) ON DELETE CASCADE
);

-- Create Warehouse Table
CREATE TABLE IF NOT EXISTS warehouse (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL UNIQUE,
    location VARCHAR(500) NOT NULL,
    manager VARCHAR(255) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Create Stock Table
CREATE TABLE IF NOT EXISTS stock (
    id SERIAL PRIMARY KEY,
    item_id INTEGER NOT NULL,
    warehouse_id INTEGER NOT NULL,
    quantity INTEGER NOT NULL CHECK (quantity >= 0),
    last_updated TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_stock_item FOREIGN KEY (item_id) REFERENCES item(id) ON DELETE CASCADE,
    CONSTRAINT fk_stock_warehouse FOREIGN KEY (warehouse_id) REFERENCES warehouse(id) ON DELETE CASCADE,
    CONSTRAINT uk_stock_item_warehouse UNIQUE (item_id, warehouse_id)
);

-- Create Movement Table
CREATE TABLE IF NOT EXISTS movement (
    id SERIAL PRIMARY KEY,
    item_id INTEGER NOT NULL,
    from_warehouse INTEGER NOT NULL,
    to_warehouse INTEGER NOT NULL,
    quantity INTEGER NOT NULL CHECK (quantity > 0),
    movement_type VARCHAR(50) NOT NULL,
    movement_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    notes TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_movement_item FOREIGN KEY (item_id) REFERENCES item(id) ON DELETE CASCADE,
    CONSTRAINT fk_movement_from FOREIGN KEY (from_warehouse) REFERENCES warehouse(id),
    CONSTRAINT fk_movement_to FOREIGN KEY (to_warehouse) REFERENCES warehouse(id),
    CONSTRAINT ck_movement_warehouses CHECK (from_warehouse != to_warehouse)
);

-- Create Indexes
CREATE INDEX idx_item_supplier ON item(supplier_id);
CREATE INDEX idx_stock_item ON stock(item_id);
CREATE INDEX idx_stock_warehouse ON stock(warehouse_id);
CREATE INDEX idx_movement_item ON movement(item_id);
CREATE INDEX idx_movement_date ON movement(movement_date);

-- Insert test Suppliers
INSERT INTO supplier (name, contact_person, email, phone, address) VALUES
    ('Tech Supplies Inc', 'John Anderson', 'john@techsupplies.com', '+1-555-0101', '123 Tech St, Silicon Valley')
ON CONFLICT (email) DO NOTHING;
INSERT INTO supplier (name, contact_person, email, phone, address) VALUES
    ('Global Electronics', 'Maria Garcia', 'maria@globalelectronics.com', '+1-555-0102', '456 Electronics Ave, Austin')
ON CONFLICT (email) DO NOTHING;
INSERT INTO supplier (name, contact_person, email, phone, address) VALUES
    ('Premium Components', 'David Chen', 'david@premiumcomp.com', '+1-555-0103', '789 Component Blvd, Seattle')
ON CONFLICT (email) DO NOTHING;

-- Insert test Warehouses
INSERT INTO warehouse (name, location, manager) VALUES
    ('Main Warehouse', 'New York', 'John Smith')
ON CONFLICT (name) DO NOTHING;
INSERT INTO warehouse (name, location, manager) VALUES
    ('East Warehouse', 'Boston', 'Jane Doe')
ON CONFLICT (name) DO NOTHING;
INSERT INTO warehouse (name, location, manager) VALUES
    ('West Warehouse', 'Los Angeles', 'Bob Johnson')
ON CONFLICT (name) DO NOTHING;

-- Insert test Items
INSERT INTO item (name, description, sku, price, supplier_id) VALUES
    ('Laptop', 'High-end laptop 15-inch', 'SKU001', 999.99, 1)
ON CONFLICT (sku) DO NOTHING;
INSERT INTO item (name, description, sku, price, supplier_id) VALUES
    ('Wireless Mouse', 'Ergonomic wireless mouse', 'SKU002', 25.50, 1)
ON CONFLICT (sku) DO NOTHING;
INSERT INTO item (name, description, sku, price, supplier_id) VALUES
    ('Mechanical Keyboard', 'RGB mechanical keyboard', 'SKU003', 75.00, 2)
ON CONFLICT (sku) DO NOTHING;
INSERT INTO item (name, description, sku, price, supplier_id) VALUES
    ('USB-C Cable', 'Premium USB-C cable 2m', 'SKU004', 15.99, 3)
ON CONFLICT (sku) DO NOTHING;

-- Insert test Stock data
INSERT INTO stock (item_id, warehouse_id, quantity) VALUES (1, 1, 100) ON CONFLICT DO NOTHING;
INSERT INTO stock (item_id, warehouse_id, quantity) VALUES (2, 1, 50) ON CONFLICT DO NOTHING;
INSERT INTO stock (item_id, warehouse_id, quantity) VALUES (3, 2, 75) ON CONFLICT DO NOTHING;
INSERT INTO stock (item_id, warehouse_id, quantity) VALUES (4, 1, 200) ON CONFLICT DO NOTHING;
INSERT INTO stock (item_id, warehouse_id, quantity) VALUES (1, 2, 25) ON CONFLICT DO NOTHING;
