USE employee_management;

CREATE TABLE employee (
    employee_id INT AUTO_INCREMENT PRIMARY KEY,
    employee_name VARCHAR(255),
    email VARCHAR(255) NOT NULL UNIQUE,
    gender VARCHAR(255) NOT NULL,
    team_id INT,
    manager_id INT,
    profile_id INT UNIQUE,
    address_id INT UNIQUE,

    CONSTRAINT fk_employee_team
        FOREIGN KEY (team_id)
        REFERENCES teams(teams_id),

    CONSTRAINT fk_employee_manager
        FOREIGN KEY (manager_id)
        REFERENCES manager(manager_id),

    CONSTRAINT fk_employee_profile
        FOREIGN KEY (profile_id)
        REFERENCES profile(profile_id),

    CONSTRAINT fk_employee_address
        FOREIGN KEY (address_id)
        REFERENCES address(address_id)
);