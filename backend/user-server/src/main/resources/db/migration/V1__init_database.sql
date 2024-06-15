-- Create User table
CREATE TABLE IF NOT EXISTS user (
    user_id INT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    phone VARCHAR(50),
    password VARCHAR(255) NOT NULL,
    role VARCHAR(50) NOT NULL
);

-- Create Manager table
CREATE TABLE IF NOT EXISTS manager (
    user_id INT PRIMARY KEY,
    farm_id INT,
    CONSTRAINT fk_manager_user FOREIGN KEY (user_id) REFERENCES user (user_id) ON DELETE CASCADE
);

-- Create Veterinarian table
CREATE TABLE IF NOT EXISTS veterinarian (
    user_id INT PRIMARY KEY,
    farm_id INT,
    CONSTRAINT fk_veterinarian_user FOREIGN KEY (user_id) REFERENCES user (user_id) ON DELETE CASCADE
);