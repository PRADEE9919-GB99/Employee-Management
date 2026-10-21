package com.pradeep.Employee_Management.util;

import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.pradeep.Employee_Management.dto.EmployeeDTO;
import com.pradeep.Employee_Management.dto.ManagerDTO;
import com.pradeep.Employee_Management.dto.ProjectsDTO;
import com.pradeep.Employee_Management.dto.TeamsDTO;
import com.pradeep.Employee_Management.entity.Employee;
import com.pradeep.Employee_Management.entity.Manager;
import com.pradeep.Employee_Management.entity.Projects;
import com.pradeep.Employee_Management.entity.Teams;
import com.pradeep.Employee_Management.exception.EmployeeException;

@Component
public class ManagerUtil {

	public static Manager toEntity(ManagerDTO dto) throws EmployeeException {

		return toEntity(dto, null, null);
	}

	public static Manager toEntity(ManagerDTO dto, Employee employee, Teams team) throws EmployeeException {

		if (dto == null) {
			throw new EmployeeException("GENERAL.ITEM_NOT_NULL");
		}

		Manager manager = new Manager();

		manager.setManagerId(dto.getManagerId());
		manager.setEmployee(employee);
		manager.setTeam(team);

		return manager;
	}

	public static ManagerDTO toDTO(Manager manager) throws EmployeeException {

		if (manager == null) {
			throw new EmployeeException("GENERAL.ITEM_NOT_NULL");
		}

		ManagerDTO dto = new ManagerDTO();

		dto.setManagerId(manager.getManagerId());

		// Map the employee associated with the manager
		if (manager.getEmployee() != null) {
			EmployeeDTO employeeDTO = EmployeeUtil.toDTO(manager.getEmployee());

			dto.setEmployee(employeeDTO);
		}

		// Map team summary without recursive manager mapping
		if (manager.getTeam() != null) {
			TeamsDTO teamsDTO = new TeamsDTO();

			teamsDTO.setTeamsId(manager.getTeam().getTeamsId());
			teamsDTO.setTeamName(manager.getTeam().getTeamName());

			dto.setTeam(teamsDTO);
		}

		// Map employees managed by this manager
		if (manager.getEmployees() != null) {
			dto.setEmployees(manager.getEmployees().stream().map(employee -> {
				try {
					return EmployeeUtil.toDTO(employee);
				} catch (EmployeeException e) {
					throw new IllegalStateException(e);
				}
			}).collect(Collectors.toList()));
		}

		// Map mentored project summaries
		if (manager.getProjectsMentored() != null) {
			dto.setProjectsMentored(manager.getProjectsMentored().stream().map(ManagerUtil::toProjectSummary)
					.collect(Collectors.toList()));
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
