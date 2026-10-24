package com.pradeep.Employee_Management.service;

import java.util.List;

import com.pradeep.Employee_Management.dto.ManagerDTO;
import com.pradeep.Employee_Management.exception.EmployeeException;

public interface ManagerService {
	Integer createManager(ManagerDTO dto) throws EmployeeException;

	ManagerDTO getManagerById(Integer id) throws EmployeeException;

	List<ManagerDTO> getAllManagers() throws EmployeeException;

	ManagerDTO updateManager(Integer id, ManagerDTO dto) throws EmployeeException;

	void deleteManager(Integer id) throws EmployeeException;
//	List<EmployeeDTO> getEmployeesUnderManager(Integer managerId);
//
	void assignEmployee(Integer id, Integer employeeId) throws EmployeeException;
//
	void assignProjects(Integer id, Integer projectId) throws EmployeeException;

	

}
