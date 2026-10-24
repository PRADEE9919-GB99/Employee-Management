
package com.pradeep.Employee_Management.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.pradeep.Employee_Management.dto.AddressDTO;
import com.pradeep.Employee_Management.dto.DepartmentResponseDTO;
import com.pradeep.Employee_Management.dto.EmployeeDTO;
import com.pradeep.Employee_Management.dto.ManagerResponseDTO;
import com.pradeep.Employee_Management.dto.ProfileDTO;
import com.pradeep.Employee_Management.dto.ProjectResponseDTO;
import com.pradeep.Employee_Management.dto.TeamsResponseDTO;
import com.pradeep.Employee_Management.entity.Address;
import com.pradeep.Employee_Management.entity.Department;
import com.pradeep.Employee_Management.entity.Employee;
import com.pradeep.Employee_Management.entity.Manager;
import com.pradeep.Employee_Management.entity.Profile;
import com.pradeep.Employee_Management.entity.Projects;
import com.pradeep.Employee_Management.entity.Teams;

@Component
public class EmployeeUtil {

	public static EmployeeDTO toDTO(Employee employee) {

		if (employee == null) {
			return null;
		}

		EmployeeDTO dto = new EmployeeDTO();

		dto.setEmployeeId(employee.getEmployeeId());
		dto.setEmployeeName(employee.getEmployeeName());
		dto.setEmail(employee.getEmail());
		dto.setGender(employee.getGender());

		// Team
		Teams team = employee.getTeam();

		TeamsResponseDTO teams = new TeamsResponseDTO();
		teams.setTeamsId(team.getTeamsId());
		teams.setTeamName(team.getTeamName());
		teams.setManagerName(team.getManager().getEmployee().getEmployeeName());
		dto.setTeam(teams);

		// Department is obtained through Team
		Department department = team.getDepartment();

		DepartmentResponseDTO departmentDTO = new DepartmentResponseDTO();
		departmentDTO.setDepartmentId(department.getDepartmentId());
		departmentDTO.setDepartmentName(department.getDepartmentName());

		dto.setDepartment(departmentDTO);

		// Manager is obtained through Team
		Manager manager = team.getManager();
//
//			if (manager != null) {
//				ManagerDTO managerDTO = new ManagerDTO();
//				managerDTO.setManagerId(manager.getManagerId());
//
//				dto.setManager(managerDTO);
//			}
		ManagerResponseDTO managerResponse = new ManagerResponseDTO();
		managerResponse.setManagerId(manager.getManagerId());
		managerResponse.setManagerName(manager.getEmployee().getEmployeeName());
		dto.setManager(managerResponse);

		// Address
		Address address = employee.getAddress();

		if (address != null) {
			AddressDTO addressDTO = new AddressDTO();
			addressDTO.setAddressId(address.getAddressId());
			addressDTO.setCity(address.getCity());
			addressDTO.setState(address.getState());
			addressDTO.setCountry(address.getCountry());

			dto.setAddress(addressDTO);
		}

		// Profile
		Profile profile = employee.getProfile();

		if (profile != null) {
			ProfileDTO profileDTO = new ProfileDTO();
			profileDTO.setProfileId(profile.getProfileId());
			profileDTO.setStartDate(profile.getStartDate());
			profileDTO.setEndDate(profile.getEndDate());
			profileDTO.setDesignation(profile.getDesignation());
			profileDTO.setEmployeePhoneNo(profile.getEmployeePhoneNo());

			dto.setProfile(profileDTO);
		}

		// Projects
		if (employee.getProjects() != null) {
			List<ProjectResponseDTO> projectDTOs = new ArrayList<>();

			for (Projects project : employee.getProjects()) {
				if (project == null) {
					continue;
				}

				ProjectResponseDTO projectDTO = new ProjectResponseDTO();
				projectDTO.setProjectId(project.getProjectId());
				projectDTO.setProjectName(project.getProjectName());
				projectDTO.setStatus(project.getStatus());

				projectDTOs.add(projectDTO);
			}

			dto.setProjects(projectDTOs);
		}

		return dto;
	}

	public static Employee toEntity(EmployeeDTO dto) {

		if (dto == null) {
			return null;
		}

		Employee employee = new Employee();

		employee.setEmployeeId(dto.getEmployeeId());
		employee.setEmployeeName(dto.getEmployeeName());
		employee.setEmail(dto.getEmail());
		employee.setGender(dto.getGender());

//		// Team reference
//		if (dto.getTeam() != null && dto.getTeam().getTeamsId() != null) {
//
//			Teams team = new Teams();
//			team.setTeamsId(dto.getTeam().getTeamsId());
//
//			employee.setTeam(team);
//		}

		// Address reference
		if (dto.getAddress() != null && dto.getAddress().getAddressId() != null) {

			Address address = new Address();
			address.setAddressId(dto.getAddress().getAddressId());

			employee.setAddress(address);
		}

		// Profile reference
		if (dto.getProfile() != null && dto.getProfile().getProfileId() != null) {

			Profile profile = new Profile();
			profile.setProfileId(dto.getProfile().getProfileId());

			employee.setProfile(profile);
		}

		// Project references
		if (dto.getProjects() != null) {
			List<Projects> projects = new ArrayList<>();

			for (ProjectResponseDTO projectDTO : dto.getProjects()) {
				if (projectDTO == null || projectDTO.getProjectId() == null) {
					continue;
				}

				Projects project = new Projects();
				project.setProjectId(projectDTO.getProjectId());

				projects.add(project);
			}

			employee.setProjects(projects);
		}

		return employee;
	}
}
