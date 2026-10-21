package com.pradeep.Employee_Management.util;

import org.springframework.stereotype.Component;

import com.pradeep.Employee_Management.dto.EmployeeDTO;
import com.pradeep.Employee_Management.dto.ManagerDTO;
import com.pradeep.Employee_Management.dto.ProjectsDTO;
import com.pradeep.Employee_Management.entity.Employee;
import com.pradeep.Employee_Management.entity.Manager;
import com.pradeep.Employee_Management.entity.Projects;
import com.pradeep.Employee_Management.exception.EmployeeException;

@Component
public class ProjectsUtil {
	public static Projects toEntity(ProjectsDTO dto) throws EmployeeException {
		return toEntity(dto, null, null);
	}

	public static Projects toEntity(ProjectsDTO dto, Employee employee, Manager manager) throws EmployeeException {

		if (dto == null) {
			throw new EmployeeException("GENERAL.ITEM_NOT_NULL");
		}

		Projects project = new Projects();

		project.setProjectId(dto.getProjectId());
		project.setProjectName(dto.getProjectName());
		project.setStatus(dto.getStatus());

		project.setEmployee(employee);
		project.setManager(manager);

		return project;
	}

	public static ProjectsDTO toDTO(Projects project) throws EmployeeException {

		if (project == null) {
			throw new EmployeeException("GENERAL.ITEM_NOT_NULL");
		}

		ProjectsDTO dto = new ProjectsDTO();

		dto.setProjectId(project.getProjectId());
		dto.setProjectName(project.getProjectName());
		dto.setStatus(project.getStatus());

		// Map Employee basic details
		if (project.getEmployee() != null) {
			EmployeeDTO employeeDTO = new EmployeeDTO();

			employeeDTO.setEmployeeId(project.getEmployee().getEmployeeId());
			employeeDTO.setEmployeeName(project.getEmployee().getEmployeeName());
			employeeDTO.setEmail(project.getEmployee().getEmail());
			employeeDTO.setGender(project.getEmployee().getGender());

			dto.setEmployee(employeeDTO);
		}

		// Map Manager basic details
		if (project.getManager() != null) {
			ManagerDTO managerDTO = new ManagerDTO();

			managerDTO.setManagerId(project.getManager().getManagerId());

			dto.setManager(managerDTO);
		}

		return dto;
	}
}
