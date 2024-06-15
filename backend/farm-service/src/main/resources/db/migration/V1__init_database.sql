-- V1__init_database.sql

CREATE TABLE IF NOT EXISTS farm (
    farm_id INT AUTO_INCREMENT PRIMARY KEY,
    farm_name VARCHAR(255) NOT NULL,
    farm_location VARCHAR(255) NOT NULL,
    farm_code VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS placement (
    placement_id INT AUTO_INCREMENT PRIMARY KEY,
    placement_code VARCHAR(255) NOT NULL,
    farm_id INT,
    FOREIGN KEY (farm_id) REFERENCES farm(farm_id)
);

CREATE TABLE IF NOT EXISTS chick_mortality (
    mortality_id INT AUTO_INCREMENT PRIMARY KEY,
    mortality_date DATE NOT NULL,
    quantity INT NOT NULL,
    description VARCHAR(255),
    placement_id INT,
    FOREIGN KEY (placement_id) REFERENCES placement(placement_id)
);

CREATE TABLE IF NOT EXISTS chick_manure (
    chick_manure_sell_id INT AUTO_INCREMENT PRIMARY KEY,
    income_id INT,
    quantity DOUBLE NOT NULL,
    farm_id INT,
    FOREIGN KEY (farm_id) REFERENCES farm(farm_id)
);

CREATE TABLE IF NOT EXISTS broiler_sales (
    broiler_sale_id INT AUTO_INCREMENT PRIMARY KEY,
    sale_date DATE NOT NULL,
    quantity DOUBLE NOT NULL,
    income_id INT,
    placement_id INT,
    FOREIGN KEY (placement_id) REFERENCES placement(placement_id)
);
