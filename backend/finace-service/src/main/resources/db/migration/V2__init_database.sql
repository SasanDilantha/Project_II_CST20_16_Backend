-- Insert sample data for Income table
INSERT INTO income (income_id, date, income_value, description, farm_code,income_type)
VALUES
    (1, '2024-06-12 08:00:00', 1500.00, 'Chicken sales', 'COL-7814','SELL_CHICKEN'),
    (2, '2024-06-11 10:30:00', 1200.50, 'Get fund from ABC company to develop the farm', 'KAN-7023', 'OTHER'),
    (3, '2024-06-10 15:45:00', 800.75, 'Manure sales', 'KAG-7211','SELL_MANURE');

-- Insert sample data for Expenses table
INSERT INTO expenses (expense_id, date, expense_value, description, farm_code,expense_type) VALUES
    (1, '2024-06-14 13:46:28', 7537.50, NULL, 'KAN-7023', 'GET_CHICK'),
    (2, '2024-06-14 14:48:17', 7537.50, NULL, 'KAG-7211','GET_FEED'),
    (3, '2024-06-14 14:50:03', 7537.50, 'To cleaning the farm', 'COL-7814', 'OTHER');
