-- Income Table
CREATE TABLE IF NOT EXISTS income (
    income_id INT AUTO_INCREMENT PRIMARY KEY,
    date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    income_value DECIMAL(10, 2) NOT NULL,
    description VARCHAR(255),
    farm_code VARCHAR(50)
);

-- Expenses Table
CREATE TABLE IF NOT EXISTS expenses (
    expense_id INT AUTO_INCREMENT PRIMARY KEY,
    date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    expense_value DECIMAL(10, 2) NOT NULL,
    description VARCHAR(255),
    farm_code VARCHAR(50)
);
