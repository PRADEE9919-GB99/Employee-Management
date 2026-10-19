package com.pradeep.Employee_Management.service;

import java.util.List;

import com.pradeep.Employee_Management.dto.EmployeeDTO;

public interface EmployeeService {
	EmployeeDTO createEmployee(EmployeeDTO dto);

	EmployeeDTO getEmployeeById(Integer id);

	List<EmployeeDTO> getAllEmployees();

	EmployeeDTO updateEmployee(Integer id, EmployeeDTO dto);

	void deleteEmployee(Integer id);
//
//	List<EmployeeDTO> getEmployeesByDepartment(Long departmentId);
//
//	List<EmployeeDTO> getEmployeesByManager(Integer managerId);
}
