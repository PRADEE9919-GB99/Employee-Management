USE employee_management;

CREATE TABLE department (
    department_id BIGINT PRIMARY KEY AUTO_INCREMENT,
    department_name VARCHAR(100)
);

-- 2. DEPARTMENT
INSERT INTO department (department_id, department_name)
VALUES
(1, 'Quality Assurance'),
(2, 'Development'),
(3, 'Human Resources');