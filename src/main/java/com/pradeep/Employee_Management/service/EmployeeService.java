package com.pradeep.Employee_Management.service;

import java.util.List;

import com.pradeep.Employee_Management.dto.EmployeeDTO;
import com.pradeep.Employee_Management.exception.EmployeeException;

public interface EmployeeService {
	Integer createEmployee(EmployeeDTO dto)throws EmployeeException;

	EmployeeDTO getEmployeeById(Integer id)throws EmployeeException;

	List<EmployeeDTO> getAllEmployees()throws EmployeeException;

	EmployeeDTO updateEmployee(Integer id, EmployeeDTO dto)throws EmployeeException;

	void deleteEmployee(Integer id)throws EmployeeException;
//
//	List<EmployeeDTO> getEmployeesByDepartment(Long departmentId)throws EmployeeException;
//
//	List<EmployeeDTO> getEmployeesByManager(Integer managerId)throws EmployeeException;
}
