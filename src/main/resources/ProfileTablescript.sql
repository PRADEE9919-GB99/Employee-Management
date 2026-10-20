USE employee_management;

CREATE TABLE profile (
    profile_id INT PRIMARY KEY AUTO_INCREMENT,
    start_date DATE,
    end_date DATE,
    designation VARCHAR(100),
    employee_phone_no VARCHAR(20),
    address_id INT UNIQUE,
    employee_id INT NOT NULL UNIQUE,

    CONSTRAINT fk_profile_address
        FOREIGN KEY (address_id)
        REFERENCES address(address_id),

    CONSTRAINT fk_profile_employee
        FOREIGN KEY (employee_id)
        REFERENCES employee(employee_id)
);

-- 5. PROFILE
INSERT INTO profile
(profile_id, start_date, end_date, designation,
 employee_phone_no, address_id, employee_id)
VALUES
(1, '2025-01-06', NULL, 'QA Manager', '9876543210', 1, 1),
(2, '2025-02-10', NULL, 'QA Analyst', '9876543211', 2, 2),
(3, '2024-08-01', NULL, 'Development Manager', '9876543212', 3, 3);