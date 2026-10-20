USE employee_management;

CREATE TABLE projects (
    project_id BIGINT PRIMARY KEY AUTO_INCREMENT,
    project_name VARCHAR(150),
    status VARCHAR(30),
    employee_id INT,
    manager_id INT,

    CONSTRAINT fk_projects_employee
        FOREIGN KEY (employee_id)
        REFERENCES employee(employee_id),

    CONSTRAINT fk_projects_manager
        FOREIGN KEY (manager_id)
        REFERENCES manager(manager_id)
);

-- 6. PROJECTS
INSERT INTO projects
(project_id, project_name, status, employee_id, manager_id)
VALUES
(1, 'Employee Management API', 'IN_PROGRESS', 2, 1),
(2, 'Automation Framework', 'PLANNED', 1, 1),
(3, 'Customer Portal', 'IN_PROGRESS', 4, 2),
(4, 'Internal HR Portal', 'COMPLETED', 5, 2);