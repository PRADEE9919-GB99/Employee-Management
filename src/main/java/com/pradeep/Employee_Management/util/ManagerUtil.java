
package com.pradeep.Employee_Management.util;

import java.util.ArrayList;
import java.util.List;
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

	private ManagerUtil() {
	}

	// Convert ManagerDTO to Manager entity
	public static Manager toEntity(ManagerDTO dto) throws EmployeeException {
		return toEntity(dto, null, new ArrayList<>());
	}

	// Convert ManagerDTO to Manager entity with employee and teams
	public static Manager toEntity(ManagerDTO dto, Employee employee, List<Teams> teams) throws EmployeeException {

		if (dto == null) {
			throw new EmployeeException("GENERAL.ITEM_NOT_NULL");
		}

		Manager manager = new Manager();

		manager.setManagerId(dto.getManagerId());
		manager.setEmployee(employee);
		manager.setTeams(teams != null ? teams : new ArrayList<>());

		return manager;
	}

	// Convert Manager entity to ManagerDTO
	public static ManagerDTO toDTO(Manager manager) throws EmployeeException {

		if (manager == null) {
			throw new EmployeeException("GENERAL.ITEM_NOT_NULL");
		}

		ManagerDTO dto = new ManagerDTO();

		dto.setManagerId(manager.getManagerId());

		// Map the employee associated with the manager
		if (manager.getEmployee() != null) {
			dto.setEmployee(EmployeeUtil.toDTO(manager.getEmployee()));
		}

		// Map teams without recursive manager mapping
		if (manager.getTeams() != null) {
			List<TeamsDTO> teamsDTO = manager.getTeams().stream().map(ManagerUtil::toTeamSummary)
					.collect(Collectors.toList());

			dto.setTeams(teamsDTO);
		} else {
			dto.setTeams(new ArrayList<>());
		}

		// Map employees reporting to this manager
		// Requires Employee.manager to exist in the entity
		List<EmployeeDTO> employeesDTO = new ArrayList<>();

		// Employees reporting to this manager

		dto.setEmployees(employeesDTO);

		// Map projects mentored by the manager
		if (manager.getProjectsMentored() != null) {
			List<ProjectsDTO> projectsDTO = manager.getProjectsMentored().stream().map(ManagerUtil::toProjectSummary)
					.collect(Collectors.toList());

			dto.setProjectsMentored(projectsDTO);
		} else {
			dto.setProjectsMentored(new ArrayList<>());
		}

		return dto;
	}

	// Lightweight TeamsDTO to avoid recursive mapping
	private static TeamsDTO toTeamSummary(Teams team) {

		TeamsDTO dto = new TeamsDTO();

		dto.setTeamsId(team.getTeamsId());
		dto.setTeamName(team.getTeamName());

		return dto;
	}

	// Lightweight ProjectsDTO to avoid recursive mapping
	private static ProjectsDTO toProjectSummary(Projects project) {

		ProjectsDTO dto = new ProjectsDTO();

		dto.setProjectId(project.getProjectId());
		dto.setProjectName(project.getProjectName());
		dto.setStatus(project.getStatus());

		return dto;
	}
}
