-- ChickSupplier Table
CREATE TABLE IF NOT EXISTS chick_supplier (
    chick_supplier_id INT AUTO_INCREMENT PRIMARY KEY,
    supplier_name VARCHAR(255) NOT NULL,
    supplier_phone VARCHAR(50),
    supplier_email VARCHAR(255),
    supplier_address TEXT
);

-- ChickInventryCostService Table
CREATE TABLE IF NOT EXISTS chick_inventry_cost (
    chick_inventry_cost_id INT AUTO_INCREMENT PRIMARY KEY,
    price_per_chick DECIMAL(10, 2) NOT NULL,
    chick_supplier_id INT,
    CONSTRAINT FK_chick_inventry_cost_supplier FOREIGN KEY (chick_supplier_id) REFERENCES chick_supplier(chick_supplier_id)
);

-- ChickStorage Table
CREATE TABLE IF NOT EXISTS chick_storage (
    chick_breed_id INT AUTO_INCREMENT PRIMARY KEY,
    breed VARCHAR(255) NOT NULL,
    chick_quantity INT NOT NULL,
    age INT NOT NULL,
    date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

-- ChickInventory Table
CREATE TABLE IF NOT EXISTS chick_inventory (
    chick_inventory_id INT AUTO_INCREMENT PRIMARY KEY,
    chick_inventory_code VARCHAR(255) NOT NULL,
    available_quantity INT NOT NULL,
    expense_id INT NOT NULL,
    chick_breed_id INT,
    chick_inventry_cost_id INT,
    CONSTRAINT FK_chick_inventory_storage FOREIGN KEY (chick_breed_id) REFERENCES chick_storage(chick_breed_id),
    CONSTRAINT FK_chick_inventory_cost FOREIGN KEY (chick_inventry_cost_id) REFERENCES chick_inventry_cost(chick_inventry_cost_id)
);

-- ChickBlock Table
CREATE TABLE IF NOT EXISTS chick_block (
    chick_block_id INT AUTO_INCREMENT PRIMARY KEY,
    placement_id INT NOT NULL,
    block_quantity INT NOT NULL,
    chick_breed_id INT,
    CONSTRAINT FK_chick_block_storage FOREIGN KEY (chick_breed_id) REFERENCES chick_storage(chick_breed_id)
);
