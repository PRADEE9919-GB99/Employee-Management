SELECT * FROM address;
SELECT * FROM department;
SELECT * FROM employee;
SELECT * FROM manager;
SELECT * FROM profile;
SELECT * FROM projects;

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