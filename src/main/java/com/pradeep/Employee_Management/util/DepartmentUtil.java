package com.pradeep.Employee_Management.util;

import org.springframework.stereotype.Component;

import com.pradeep.Employee_Management.dto.DepartmentDTO;
import com.pradeep.Employee_Management.entity.Department;
import com.pradeep.Employee_Management.exception.EmployeeException;
@Component
public class DepartmentUtil {
	
	public static Department toEntity(DepartmentDTO dto) throws EmployeeException {
		if (dto == null) {
			throw new EmployeeException("GENERAL.ITEM_NOT_NULL");
		}
		Department department = new Department();
		department.setDepartmentId(dto.getDepartmentId());
		department.setDepartmentName(dto.getDepartmentName());

		return department;
	}

	public static DepartmentDTO toDTO(Department department) throws EmployeeException {
		if (department == null) {
			throw new EmployeeException("GENERAL.ITEM_NOT_NULL");
		}
		DepartmentDTO dto = new DepartmentDTO();
		dto.setDepartmentId(department.getDepartmentId());
		dto.setDepartmentName(department.getDepartmentName());

		return dto;
	}
}
