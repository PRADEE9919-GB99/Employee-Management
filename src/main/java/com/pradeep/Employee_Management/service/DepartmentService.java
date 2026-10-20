package com.pradeep.Employee_Management.service;

import java.util.List;

import com.pradeep.Employee_Management.dto.DepartmentDTO;
import com.pradeep.Employee_Management.exception.EmployeeException;

public interface DepartmentService {
	Long createDepartment(DepartmentDTO dto)throws EmployeeException ;

	DepartmentDTO getDepartmentById(Long id)throws EmployeeException ;

	List<DepartmentDTO> getAllDepartments()throws EmployeeException ;

	DepartmentDTO updateDepartment(Long id, DepartmentDTO dto)throws EmployeeException ;

	void deleteDepartment(Long id)throws EmployeeException ;
}
