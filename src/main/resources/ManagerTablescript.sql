USE employee_management;
CREATE TABLE manager (
    manager_id INT PRIMARY KEY AUTO_INCREMENT,
    employee_id INT NOT NULL UNIQUE,
    team VARCHAR(50),

    CONSTRAINT fk_manager_employee
        FOREIGN KEY (employee_id)
        REFERENCES employee(employee_id)
);
-- Add this after creating manager because employee and manager
-- reference each other.
ALTER TABLE employee
ADD CONSTRAINT fk_employee_manager
    FOREIGN KEY (manager_id)
    REFERENCES manager(manager_id);
    
-- 4. MANAGER
-- Employee 1 manages the QA team.
-- Employee 3 manages the Development team.
INSERT INTO manager (manager_id, employee_id, team)
VALUES
(1, 1, 'QA'),
(2, 3, 'DEVELOPMENT');
