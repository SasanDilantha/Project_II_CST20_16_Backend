
-- Insert sample data into User table
INSERT INTO user (first_name, last_name, email, phone, password, role) VALUES
('John', 'Doe', 'john.doe@example.com', '123-456-7890', 'password123', 'MGR'),
('Jane', 'Smith', 'jane.smith@example.com', '098-765-4321', 'password456', 'VET'),
('Sasan', 'Dilantha', 'sasandialntha@ymail.com', '0719236152', 'pass123', 'ADMIN'),
('Tharindu', 'Deshapreya', 'evil@gmail.com', '123-456-7890', 'tharidu', 'MGR'),
('Hashini', 'Sulakshana', 'hashini@ymail.com', '0322250706', 'password12347', 'VET');

-- Insert sample data into Manager table
INSERT INTO manager (user_id, farm_id) VALUES
(1, 101),
(4, 1);
-- Insert sample data into Veterinarian table
INSERT INTO veterinarian (user_id, farm_id) VALUES
(2, 202),
(5, 2);