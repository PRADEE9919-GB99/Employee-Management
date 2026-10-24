USE employee_management;
USE employee_management;

CREATE TABLE department (
    department_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    department_name VARCHAR(255)
);

--INSERT INTO department (department_name)
VALUES
('Engineering'),
('Human Resources'),
('Finance'),
('Marketing'),
('Quality Assurance');