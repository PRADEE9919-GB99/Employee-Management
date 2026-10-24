CREATE DATABASE IF NOT EXISTS employee_management;


USE employee_management;

CREATE TABLE address (
    address_id INT AUTO_INCREMENT PRIMARY KEY,
    city VARCHAR(255),
    state VARCHAR(255),
    country VARCHAR(255)
);
INSERT INTO address (city, state, country)
VALUES
('Mysuru', 'Karnataka', 'India'),
('Bengaluru', 'Karnataka', 'India'),
('Hyderabad', 'Telangana', 'India');

SELECT * FROM address;