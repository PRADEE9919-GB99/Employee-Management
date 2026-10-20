USE employee_management;

CREATE TABLE employee (
    employee_id INT PRIMARY KEY AUTO_INCREMENT,
    employee_name VARCHAR(100),
    email VARCHAR(150),
    gender VARCHAR(20) NOT NULL,
    team VARCHAR(50) NOT NULL,
    department_id BIGINT,
    manager_id INT,

    CONSTRAINT fk_employee_department
        FOREIGN KEY (department_id)
        REFERENCES department(department_id)
);
-- 3. EMPLOYEE
-- Manager IDs are initially NULL because manager records
-- have not been created yet.
INSERT INTO employee
(employee_id, employee_name, email, gender, team, department_id, manager_id)
VALUES
(1, 'Arun Kumar', 'arun@example.com', 'MALE', 'QA', 1, NULL),
(2, 'Priya Sharma', 'priya@example.com', 'FEMALE', 'QA', 1, NULL),
(3, 'Rahul Patil', 'rahul@example.com', 'MALE', 'DEVELOPMENT', 2, NULL),
(4, 'Sneha Rao', 'sneha@example.com', 'FEMALE', 'DEVELOPMENT', 2, NULL),
(5, 'Kiran Gowda', 'kiran@example.com', 'MALE', 'HR', 3, NULL);
