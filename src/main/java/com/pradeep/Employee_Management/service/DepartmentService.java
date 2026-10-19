package com.pradeep.Employee_Management.service;

import java.util.List;

import com.pradeep.Employee_Management.dto.DepartmentDTO;

public interface DepartmentService {
	DepartmentDTO createDepartment(DepartmentDTO dto);

	DepartmentDTO getDepartmentById(Long id);

	List<DepartmentDTO> getAllDepartments();

	DepartmentDTO updateDepartment(Long id, DepartmentDTO dto);

	void deleteDepartment(Long id);
}
