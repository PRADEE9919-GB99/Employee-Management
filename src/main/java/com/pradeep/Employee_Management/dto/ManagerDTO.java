package com.pradeep.Employee_Management.dto;

import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public class ManagerDTO {
	private Integer managerId;
	@NotNull(message = "Manager employee is required")
	@Valid
	private EmployeeDTO employee;
	@Valid
	private List<TeamsDTO> teams;
	@Valid
	private List<EmployeeDTO> employees;
	@Valid
	private List<ProjectsDTO> projectsMentored;

	public Integer getManagerId() {
		return managerId;
	}

	public void setManagerId(Integer managerId) {
		this.managerId = managerId;
	}

	public EmployeeDTO getEmployee() {
		return employee;
	}

	public void setEmployee(EmployeeDTO employee) {
		this.employee = employee;
	}

	public List<EmployeeDTO> getEmployees() {
		return employees;
	}

	public void setEmployees(List<EmployeeDTO> employees) {
		this.employees = employees;
	}

	public List<ProjectsDTO> getProjectsMentored() {
		return projectsMentored;
	}

	public void setProjectsMentored(List<ProjectsDTO> projectsMentored) {
		this.projectsMentored = projectsMentored;
	}

	public void setTeams(List<TeamsDTO> teams) {
		this.teams = teams;
	}

	public List<TeamsDTO> getTeams() {
		return teams;
	}

}
