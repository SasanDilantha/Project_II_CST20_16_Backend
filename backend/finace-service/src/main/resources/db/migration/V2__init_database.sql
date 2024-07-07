-- Insert sample data for Income table
INSERT INTO income (date, income_value, description, farm_code)
VALUES
    ('2024-06-12 08:00:00', 1500.00, 'Egg sales', 'FARM001'),
    ('2024-06-11 10:30:00', 1200.50, 'Meat sales', 'FARM002'),
    ('2024-06-10 15:45:00', 800.75, 'Manure sales', 'FARM003');

-- Insert sample data for Expenses table
INSERT INTO expenses (date, expense_value, description, farm_code)
VALUES
    ('2024-06-12 10:00:00', 500.00, 'Feed purchase', 'FARM001'),
    ('2024-06-11 12:15:00', 300.25, 'Equipment maintenance', 'FARM002'),
    ('2024-06-10 14:30:00', 200.50, 'Utilities', 'FARM003');
