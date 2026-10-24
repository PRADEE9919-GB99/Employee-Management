USE employee_management;

-- 1. Departments
INSERT INTO department (department_id, department_name) VALUES
(1, 'Quality Assurance'),
(2, 'Software Engineering');

-- 2. Addresses
INSERT INTO address (address_id, city, state, country) VALUES
(1, 'Mysuru', 'Karnataka', 'India'),
(2, 'Bengaluru', 'Karnataka', 'India'),
(3, 'Vijayapura', 'Karnataka', 'India'),
(4, 'Hubballi', 'Karnataka', 'India'),
(5, 'Mangaluru', 'Karnataka', 'India'),
(6, 'Pune', 'Maharashtra', 'India'),
(7, 'Hyderabad', 'Telangana', 'India'),
(8, 'Chennai', 'Tamil Nadu', 'India'),
(9, 'Mumbai', 'Maharashtra', 'India'),
(10, 'Delhi', 'Delhi', 'India');

-- 3. Profiles
INSERT INTO profile
(profile_id, start_date, end_date, designation, employee_phone_no) VALUES
(1, '2025-01-01', NULL, 'QA Manager', '9000000001'),
(2, '2025-01-01', NULL, 'Engineering Manager', '9000000002'),
(3, '2025-02-01', NULL, 'QA Analyst', '9000000003'),
(4, '2025-02-01', NULL, 'Software Engineer', '9000000004'),
(5, '2025-03-01', NULL, 'QA Engineer', '9000000005'),
(6, '2025-03-01', NULL, 'Backend Developer', '9000000006'),
(7, '2025-04-01', NULL, 'Frontend Developer', '9000000007'),
(8, '2025-04-01', NULL, 'Automation Tester', '9000000008'),
(9, '2025-05-01', NULL, 'Software Engineer', '9000000009'),
(10, '2025-05-01', NULL, 'QA Analyst', '9000000010');

-- 4. Employees
-- Keep relationships NULL so assignment APIs can be tested.
INSERT INTO employee
(employee_id, employee_name, email, gender,
 team_id, department_id, manager_id, profile_id, address_id) VALUES
(1, 'Arun Kumar', 'arun@example.com', 'MALE', NULL, NULL, NULL, NULL, NULL),
(2, 'Priya Sharma', 'priya@example.com', 'FEMALE', NULL, NULL, NULL, NULL, NULL),
(3, 'Rahul Patil', 'rahul@example.com', 'MALE', NULL, NULL, NULL, NULL, NULL),
(4, 'Sneha Rao', 'sneha@example.com', 'FEMALE', NULL, NULL, NULL, NULL, NULL),
(5, 'Kiran Gowda', 'kiran@example.com', 'MALE', NULL, NULL, NULL, NULL, NULL),
(6, 'Ananya Reddy', 'ananya@example.com', 'FEMALE', NULL, NULL, NULL, NULL, NULL),
(7, 'Vikram Singh', 'vikram@example.com', 'MALE', NULL, NULL, NULL, NULL, NULL),
(8, 'Meera Nair', 'meera@example.com', 'FEMALE', NULL, NULL, NULL, NULL, NULL),
(9, 'Rohit Das', 'rohit@example.com', 'MALE', NULL, NULL, NULL, NULL, NULL),
(10, 'Divya Shetty', 'divya@example.com', 'FEMALE', NULL, NULL, NULL, NULL, NULL);

-- 5. Managers
-- Employees 1 and 2 are the manager employees.
INSERT INTO manager (manager_id, employee_id) VALUES
(1, 1),
(2, 2);

-- 6. Teams
INSERT INTO teams (teams_id, team_name, manager_id, department_id) VALUES
(1, 'Manual Testing', NULL, NULL),
(2, 'Automation Testing', NULL, NULL),
(3, 'Backend Development', NULL, NULL),
(4, 'Frontend Development', NULL, NULL);

-- 7. Projects
INSERT INTO projects
(project_id, project_name, status, employee_id, manager_id) VALUES
(1, 'CRM Testing', 'ACTIVE', NULL, NULL),
(2, 'Regression Suite', 'ACTIVE', NULL, NULL),
(3, 'API Automation', 'ACTIVE', NULL, NULL),
(4, 'Performance Testing', 'COMPLETED', NULL, NULL),
(5, 'Security Testing', 'ACTIVE', NULL, NULL),
(6, 'Employee Portal', 'ACTIVE', NULL, NULL),
(7, 'Customer Portal', 'COMPLETED', NULL, NULL),
(8, 'Payment Module', 'ACTIVE', NULL, NULL),
(9, 'Reporting Dashboard', 'ACTIVE', NULL, NULL),
(10, 'Mobile Application', 'COMPLETED', NULL, NULL),
(11, 'Database Migration', 'ACTIVE', NULL, NULL),
(12, 'UI Modernization', 'ACTIVE', NULL, NULL),
(13, 'Integration Testing', 'COMPLETED', NULL, NULL),
(14, 'Cloud Deployment', 'ACTIVE', NULL, NULL),
(15, 'Automation Framework', 'ACTIVE', NULL, NULL),
(16, 'Release Validation', 'COMPLETED', NULL, NULL);

USE employee_management;

-- 1. View all departments
SELECT * FROM department;

-- 2. View all teams
SELECT * FROM teams;

-- 3. View all employees
SELECT * FROM employee;

-- 4. View all managers
SELECT * FROM manager;

-- 5. View all projects
SELECT * FROM projects;

-- 6. View all profiles
SELECT * FROM profile;

-- 7. View all addresses
SELECT * FROM address;

SELECT
    e.employee_id,
    e.employee_name,
    e.email,
    d.department_name,
    m.manager_id,
    manager_employee.employee_name AS manager_name
FROM employee e
LEFT JOIN department d
    ON e.department_id = d.department_id
LEFT JOIN manager m
    ON e.manager_id = m.manager_id
LEFT JOIN employee manager_employee
    ON m.employee_id = manager_employee.employee_id;