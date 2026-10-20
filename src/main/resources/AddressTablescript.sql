CREATE DATABASE IF NOT EXISTS employee_management;
USE employee_management;

CREATE TABLE address (
    address_id INT PRIMARY KEY AUTO_INCREMENT,
    city VARCHAR(100),
    state VARCHAR(100),
    country VARCHAR(100)
);

-- 1. ADDRESS
INSERT INTO address (address_id, city, state, country)
VALUES
(1, 'Mysore', 'Karnataka', 'India'),
(2, 'Bengaluru', 'Karnataka', 'India'),
(3, 'Pune', 'Maharashtra', 'India');