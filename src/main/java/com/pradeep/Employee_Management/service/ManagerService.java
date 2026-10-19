package com.pradeep.Employee_Management.service;

import java.util.List;

import com.pradeep.Employee_Management.dto.EmployeeDTO;
import com.pradeep.Employee_Management.dto.ManagerDTO;

public interface ManagerService {
	ManagerDTO createManager(ManagerDTO dto);

	ManagerDTO getManagerById(Integer id);

	List<ManagerDTO> getAllManagers();

	ManagerDTO updateManager(Integer id, ManagerDTO dto);

	void deleteManager(Integer id);
//	List<EmployeeDTO> getEmployeesUnderManager(Integer managerId);
}
