USE employee_management;

CREATE TABLE manager (
    manager_id INT AUTO_INCREMENT PRIMARY KEY,
    employee_id INT NOT NULL UNIQUE,

    CONSTRAINT fk_manager_employee
        FOREIGN KEY (employee_id)
        REFERENCES employee(employee_id)
);