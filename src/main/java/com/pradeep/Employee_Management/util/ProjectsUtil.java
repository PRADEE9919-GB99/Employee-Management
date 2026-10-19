package com.pradeep.Employee_Management.util;

import org.springframework.stereotype.Component;

import com.pradeep.Employee_Management.dto.EmployeeDTO;
import com.pradeep.Employee_Management.dto.ManagerDTO;
import com.pradeep.Employee_Management.dto.ProjectsDTO;
import com.pradeep.Employee_Management.entity.Employee;
import com.pradeep.Employee_Management.entity.Manager;
import com.pradeep.Employee_Management.entity.Projects;
@Component
public class ProjectsUtil {
	public static Projects toEntity(ProjectsDTO dto) {
		return toEntity(dto, null, null);
	}

	public static Projects toEntity(ProjectsDTO dto, Employee employee, Manager manager) {

		if (dto == null)
			return null;

		Projects project = new Projects();
		project.setProjectId(dto.getProjectId());
		project.setProjectName(dto.getProjectName());
		project.setStatus(dto.getStatus());
		project.setEmployee(employee);
		project.setManager(manager);

		return project;
	}

	public static ProjectsDTO toDTO(Projects project) {
		if (project == null)
			return null;

		ProjectsDTO dto = new ProjectsDTO();
		dto.setProjectId(project.getProjectId());
		dto.setProjectName(project.getProjectName());
		dto.setStatus(project.getStatus());

		if (project.getEmployee() != null) {
			Employee employee = project.getEmployee();

			EmployeeDTO employeeDTO = new EmployeeDTO();
			employeeDTO.setEmployeeId(employee.getEmployeeId());
			employeeDTO.setEmployeeName(employee.getEmployeeName());
			employeeDTO.setEmail(employee.getEmail());
			employeeDTO.setGender(employee.getGender());
			employeeDTO.setTeam(employee.getTeam());

			dto.setEmployee(employeeDTO);
		}

		if (project.getManager() != null) {
			Manager manager = project.getManager();

			ManagerDTO managerDTO = new ManagerDTO();
			managerDTO.setManagerId(manager.getManagerId());
			managerDTO.setTeam(manager.getTeam());

			dto.setManager(managerDTO);
		}

		return dto;
	}
}
