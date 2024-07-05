-- Create table feed_supplier
CREATE TABLE IF NOT EXISTS  feed_supplier (
    feed_supplier_id INT PRIMARY KEY AUTO_INCREMENT,
    supplier_name VARCHAR(255),
    supplier_phone VARCHAR(255),
    supplier_email VARCHAR(255),
    supplier_address VARCHAR(255)
);

-- Create table feed_inventry_cost
CREATE TABLE IF NOT EXISTS feed_inventry_cost (
    feed_inventry_cost_id INT PRIMARY KEY AUTO_INCREMENT,
    cost_per_unit DECIMAL(19, 2),
    feed_supllier_id INT,
    FOREIGN KEY (feed_supllier_id) REFERENCES feed_supplier(feed_supplier_id)
);

-- Create table feed_storage
CREATE TABLE IF NOT EXISTS feed_storage (
    feed_id INT PRIMARY KEY AUTO_INCREMENT,
    feed_type VARCHAR(255),
    initial_feed_quantity INT,
    date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

-- Create table feed_inventory
CREATE TABLE IF NOT EXISTS feed_inventory (
    feed_inventory_id INT PRIMARY KEY AUTO_INCREMENT,
    feed_inventory_code VARCHAR(255),
    available_quantity INT,
    expense_id INT,
    feed_id INT,
    feed_inventry_cost_id INT,
    FOREIGN KEY (feed_id) REFERENCES feed_storage(feed_id),
    FOREIGN KEY (feed_inventry_cost_id) REFERENCES feed_inventry_cost(feed_inventry_cost_id)
);

-- Create table feed_block
CREATE TABLE IF NOT EXISTS feed_block (
    feed_block_id INT PRIMARY KEY AUTO_INCREMENT,
    placement_id INT,
    block_quantity INT,
    feed_id INT,
    FOREIGN KEY (feed_id) REFERENCES feed_storage(feed_id)
);
