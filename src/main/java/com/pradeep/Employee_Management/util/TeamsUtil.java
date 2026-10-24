package com.pradeep.Employee_Management.util;

import org.springframework.stereotype.Component;

import com.pradeep.Employee_Management.dto.DepartmentResponseDTO;
import com.pradeep.Employee_Management.dto.ManagerResponseDTO;
import com.pradeep.Employee_Management.dto.TeamsDTO;
import com.pradeep.Employee_Management.entity.Department;
import com.pradeep.Employee_Management.entity.Manager;
import com.pradeep.Employee_Management.entity.Teams;
import com.pradeep.Employee_Management.exception.EmployeeException;

@Component
public class TeamsUtil {

	public static Teams toEntity(TeamsDTO dto) throws EmployeeException {
		if (dto == null) {
			throw new EmployeeException("GENERAL.ITEM_NOT_NULL");
		}

		Teams teams = new Teams();
		teams.setTeamsId(dto.getTeamsId());
		teams.setTeamName(dto.getTeamName());

		return teams;
	}

	public static Teams toEntity(TeamsDTO dto, Department department, Manager manager) throws EmployeeException {

		if (dto == null) {
			throw new EmployeeException("GENERAL.ITEM_NOT_NULL");
		}

		Teams teams = toEntity(dto);
		teams.setDepartment(department);
		teams.setManager(manager);

		return teams;
	}

	public static TeamsDTO toDTO(Teams teams) throws EmployeeException {
		if (teams == null) {
			throw new EmployeeException("GENERAL.ITEM_NOT_NULL");
		}

		TeamsDTO dto = new TeamsDTO();
		dto.setTeamsId(teams.getTeamsId());
		dto.setTeamName(teams.getTeamName());

		if (teams.getDepartment() != null) {
			DepartmentResponseDTO departmentDTO = new DepartmentResponseDTO();
			departmentDTO.setDepartmentId(teams.getDepartment().getDepartmentId());
			departmentDTO.setDepartmentName(teams.getDepartment().getDepartmentName());

			dto.setDepartment(departmentDTO);
		}
		Manager manager = teams.getManager();
		if (teams.getManager() != null) {
			ManagerResponseDTO managerResponse = new ManagerResponseDTO();
			managerResponse.setManagerId(manager.getManagerId());
			managerResponse.setManagerName(manager.getEmployee().getEmployeeName());
			dto.setManagerResponseDTO(managerResponse);
		}

		return dto;
	}
}
