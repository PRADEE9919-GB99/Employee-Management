package com.pradeep.Employee_Management.util;

import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.pradeep.Employee_Management.dto.EmployeeDTO;
import com.pradeep.Employee_Management.dto.ManagerDTO;
import com.pradeep.Employee_Management.dto.ProjectsDTO;
import com.pradeep.Employee_Management.entity.Employee;
import com.pradeep.Employee_Management.entity.Manager;
import com.pradeep.Employee_Management.entity.Projects;
@Component
public class ManagerUtil {
	public static Manager toEntity(ManagerDTO dto) {
		return toEntity(dto, null);
	}

	public static Manager toEntity(ManagerDTO dto, Employee managerEmployee) {

		if (dto == null)
			return null;

		Manager manager = new Manager();
		manager.setManagerId(dto.getManagerId());
		manager.setEmployee(managerEmployee);
		manager.setTeam(dto.getTeam());

		return manager;
	}

	public static ManagerDTO toDTO(Manager manager) {
		if (manager == null)
			return null;

		ManagerDTO dto = new ManagerDTO();
		dto.setManagerId(manager.getManagerId());
		dto.setTeam(manager.getTeam());

		if (manager.getEmployee() != null) {
			dto.setEmployee(toEmployeeSummary(manager.getEmployee()));
		}

		if (manager.getEmployees() != null) {
			dto.setEmployees(
					manager.getEmployees().stream().map(ManagerUtil::toEmployeeSummary).collect(Collectors.toList()));
		}

		if (manager.getProjectsMentored() != null) {
			dto.setProjectsMentored(manager.getProjectsMentored().stream().map(ManagerUtil::toProjectSummary)
					.collect(Collectors.toList()));
		}

		return dto;
	}

	private static EmployeeDTO toEmployeeSummary(Employee employee) {
		EmployeeDTO dto = new EmployeeDTO();
		dto.setEmployeeId(employee.getEmployeeId());
		dto.setEmployeeName(employee.getEmployeeName());
		dto.setEmail(employee.getEmail());
		dto.setGender(employee.getGender());
		dto.setTeam(employee.getTeam());
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
