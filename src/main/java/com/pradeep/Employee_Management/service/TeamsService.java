package com.pradeep.Employee_Management.service;

import com.pradeep.Employee_Management.dto.TeamsDTO;
import com.pradeep.Employee_Management.exception.EmployeeException;

public interface TeamsService {
	public Integer createTeams(TeamsDTO dto) throws EmployeeException;
}
