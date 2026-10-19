package com.pradeep.Employee_Management.util;

import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.pradeep.Employee_Management.dto.DepartmentDTO;
import com.pradeep.Employee_Management.dto.EmployeeDTO;
import com.pradeep.Employee_Management.dto.ManagerDTO;
import com.pradeep.Employee_Management.dto.ProjectsDTO;
import com.pradeep.Employee_Management.entity.Department;
import com.pradeep.Employee_Management.entity.Employee;
import com.pradeep.Employee_Management.entity.Manager;
import com.pradeep.Employee_Management.entity.Projects;
@Component
public class EmployeeUtil {
	public static Employee toEntity(EmployeeDTO dto) {
		return toEntity(dto, null, null);
	}

	public static Employee toEntity(EmployeeDTO dto, Department department, Manager manager) {

		if (dto == null)
			return null;

		Employee employee = new Employee();
		employee.setEmployeeId(dto.getEmployeeId());
		employee.setEmployeeName(dto.getEmployeeName());
		employee.setEmail(dto.getEmail());
		employee.setGender(dto.getGender());
		employee.setTeam(dto.getTeam());
		employee.setDepartment(department);
		employee.setManager(manager);

		return employee;
	}

	public static EmployeeDTO toDTO(Employee employee) {
		if (employee == null)
			return null;

		EmployeeDTO dto = new EmployeeDTO();
		dto.setEmployeeId(employee.getEmployeeId());
		dto.setEmployeeName(employee.getEmployeeName());
		dto.setEmail(employee.getEmail());
		dto.setGender(employee.getGender());
		dto.setTeam(employee.getTeam());

		if (employee.getDepartment() != null) {
			DepartmentDTO departmentDTO = new DepartmentDTO();
			departmentDTO.setDepartmentId(employee.getDepartment().getDepartmentId());
			departmentDTO.setDepartmentName(employee.getDepartment().getDepartmentName());
			dto.setDepartment(departmentDTO);
		}

		if (employee.getManager() != null) {
			ManagerDTO managerDTO = new ManagerDTO();
			managerDTO.setManagerId(employee.getManager().getManagerId());
			managerDTO.setTeam(employee.getManager().getTeam());
			dto.setManager(managerDTO);
		}

		if (employee.getProjects() != null) {
			dto.setProjects(
					employee.getProjects().stream().map(EmployeeUtil::toProjectSummary).collect(Collectors.toList()));
		}

		return dto;
	}

	private static ProjectsDTO toProjectSummary(Projects project) {
		ProjectsDTO dto = new ProjectsDTO();
		dto.setProjectId(project.getProjectId());
		dto.setProjectName(project.getProjectName());
		dto.setStatus(project.getStatus());
		return dto;
	}
}
