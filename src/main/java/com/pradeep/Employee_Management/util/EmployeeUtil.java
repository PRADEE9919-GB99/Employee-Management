package com.pradeep.Employee_Management.util;

import org.springframework.stereotype.Component;

import com.pradeep.Employee_Management.dto.DepartmentDTO;
import com.pradeep.Employee_Management.dto.EmployeeDTO;
import com.pradeep.Employee_Management.dto.ManagerDTO;
import com.pradeep.Employee_Management.dto.TeamsDTO;
import com.pradeep.Employee_Management.entity.Department;
import com.pradeep.Employee_Management.entity.Employee;
import com.pradeep.Employee_Management.entity.Manager;
import com.pradeep.Employee_Management.entity.Teams;
import com.pradeep.Employee_Management.exception.EmployeeException;
@Component
public class EmployeeUtil {

	public static Employee toEntity(EmployeeDTO dto) throws EmployeeException {
		return toEntity(dto, null, null, null);
	}

	public static Employee toEntity(EmployeeDTO dto, Department department, Manager manager, Teams team)
			throws EmployeeException {

		if (dto == null) {
			throw new EmployeeException("GENERAL.ITEM_NOT_NULL");
		}

		Employee employee = new Employee();

		employee.setEmployeeId(dto.getEmployeeId());
		employee.setEmployeeName(dto.getEmployeeName());
		employee.setEmail(dto.getEmail());
		employee.setGender(dto.getGender());

		employee.setDepartment(department);
		employee.setManager(manager);
		employee.setTeam(team);

		return employee;
	}

	public static EmployeeDTO toDTO(Employee employee) throws EmployeeException {

		if (employee == null) {
			throw new EmployeeException("GENERAL.ITEM_NOT_NULL");
		}

		EmployeeDTO dto = new EmployeeDTO();

		dto.setEmployeeId(employee.getEmployeeId());
		dto.setEmployeeName(employee.getEmployeeName());
		dto.setEmail(employee.getEmail());
		dto.setGender(employee.getGender());

		// Map Team
		if (employee.getTeam() != null) {
			TeamsDTO teamsDTO = new TeamsDTO();

			teamsDTO.setTeamsId(employee.getTeam().getTeamsId());
			teamsDTO.setTeamName(employee.getTeam().getTeamName());

			dto.setTeam(teamsDTO);
		}

		// Map Department
		if (employee.getDepartment() != null) {
			DepartmentDTO departmentDTO = new DepartmentDTO();

			departmentDTO.setDepartmentId(employee.getDepartment().getDepartmentId());
			departmentDTO.setDepartmentName(employee.getDepartment().getDepartmentName());

			dto.setDepartment(departmentDTO);
		}

		// Map Manager
		if (employee.getManager() != null) {
			ManagerDTO managerDTO = new ManagerDTO();

			managerDTO.setManagerId(employee.getManager().getManagerId());

			dto.setManager(managerDTO);
		}

		// Map Projects
//		if (employee.getProjects() != null) {
//			dto.setProjects(
//					employee.getProjects().stream().map(EmployeeUtil::toProjectSummary).collect(Collectors.toList()));
//		}

		return dto;
	}

//	private static ProjectsDTO toProjectSummary(Projects project) {
//
//		ProjectsDTO dto = new ProjectsDTO();
//
//		dto.setProjectId(project.getProjectId());
//		dto.setProjectName(project.getProjectName());
//		dto.setStatus(project.getStatus());
//
//		return dto;
//	}
}
