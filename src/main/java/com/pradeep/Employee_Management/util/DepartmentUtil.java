package com.pradeep.Employee_Management.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.pradeep.Employee_Management.dto.DepartmentDTO;
import com.pradeep.Employee_Management.dto.EmployeeDTO;
import com.pradeep.Employee_Management.entity.Department;
import com.pradeep.Employee_Management.entity.Employee;
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

		List<Employee> employees = new ArrayList<>();

		if (dto.getEmployee() != null) {
			for (EmployeeDTO employeeDTO : dto.getEmployee()) {

				Employee employee = EmployeeUtil.toEntity(employeeDTO);

				employee.setDepartment(department);
				employees.add(employee);
			}
		}

		department.setEmployee(employees);

		return department;
	}

	public static DepartmentDTO toDTO(Department department) throws EmployeeException {
		if (department == null) {
			throw new EmployeeException("GENERAL.ITEM_NOT_NULL");
		}
		DepartmentDTO dto = new DepartmentDTO();
		dto.setDepartmentId(department.getDepartmentId());
		dto.setDepartmentName(department.getDepartmentName());
		List<Employee> employees = new ArrayList<>();

		List<EmployeeDTO> employeeDTOs = new ArrayList<>();

        if (department.getEmployee() != null) {
            for (Employee employee : department.getEmployee()) {
                employeeDTOs.add(EmployeeUtil.toDTO(employee));
            }
        }

        dto.setEmployee(employeeDTOs);

        return dto;
	}
}
