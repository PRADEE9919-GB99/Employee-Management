package com.pradeep.Employee_Management.util;

import org.springframework.stereotype.Component;

import com.pradeep.Employee_Management.dto.DepartmentDTO;
import com.pradeep.Employee_Management.entity.Department;
@Component
public class DepartmentUtil {
	
	public static Department toEntity(DepartmentDTO dto) {
		if (dto == null)
			return null;

		Department department = new Department();
		department.setDepartmentId(dto.getDepartmentId());
		department.setDepartmentName(dto.getDepartmentName());

		return department;
	}

	public static DepartmentDTO toDTO(Department department) {
		if (department == null)
			return null;

		DepartmentDTO dto = new DepartmentDTO();
		dto.setDepartmentId(department.getDepartmentId());
		dto.setDepartmentName(department.getDepartmentName());

		return dto;
	}
}
