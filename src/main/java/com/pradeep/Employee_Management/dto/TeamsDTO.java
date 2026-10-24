package com.pradeep.Employee_Management.dto;

import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class TeamsDTO {
	private Integer teamsId;
	@NotBlank(message = "{teams.teamName.notBlank}")
	@Size(min = 2, max = 100, message = "{teams.teamName.size}")
	private String teamName;
	@Valid
	private ManagerResponseDTO manager;
	@Valid
	private DepartmentResponseDTO department;
    @Valid
	private List<EmployeeDTO> employees;

	public Integer getTeamsId() {
		return teamsId;
	}

	public void setTeamsId(Integer teamsId) {
		this.teamsId = teamsId;
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public ManagerResponseDTO getManagerResponseDTO() {
		return manager;
	}

	public void setManagerResponseDTO(ManagerResponseDTO managerResponseDTO) {
		this.manager = managerResponseDTO;
	}

	public List<EmployeeDTO> getEmployees() {
		return employees;
	}

	public void setEmployees(List<EmployeeDTO> employees) {
		this.employees = employees;
	}

	public DepartmentResponseDTO getDepartment() {
		return department;
	}

	public void setDepartment(DepartmentResponseDTO department) {
		this.department = department;
	}

}
