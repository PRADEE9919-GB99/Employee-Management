package com.pradeep.Employee_Management.dto;

import java.util.List;

public class TeamsDTO {
	private Integer teamsId;
	private String teamName;
	private ManagerResponseDTO manager;
	private DepartmentResponseDTO department;
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
