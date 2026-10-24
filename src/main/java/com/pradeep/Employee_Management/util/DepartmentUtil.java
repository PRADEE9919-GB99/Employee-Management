
package com.pradeep.Employee_Management.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.pradeep.Employee_Management.dto.DepartmentDTO;
import com.pradeep.Employee_Management.dto.ManagerDTO;
import com.pradeep.Employee_Management.dto.TeamsDTO;
import com.pradeep.Employee_Management.entity.Department;
import com.pradeep.Employee_Management.entity.Manager;
import com.pradeep.Employee_Management.entity.Teams;
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

		return department;
	}

	public static DepartmentDTO toDTO(Department department) throws EmployeeException {

		if (department == null) {
			throw new EmployeeException("GENERAL.ITEM_NOT_NULL");
		}

		DepartmentDTO dto = new DepartmentDTO();
		List<Teams> teams=department.getTeams();
		List<TeamsDTO> teamsDTO=new ArrayList<>();
		for(Teams team:teams) {
			TeamsDTO teamDto=new TeamsDTO();
			teamDto=TeamsUtil.toDTO(team);
			teamsDTO.add(teamDto);
		}
		dto.setDepartmentId(department.getDepartmentId());
		dto.setDepartmentName(department.getDepartmentName());
		dto.setTeams(teamsDTO);
		

		return dto;
	}
}
