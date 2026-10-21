package com.pradeep.Employee_Management.dto;

import java.util.List;

public class ManagerDTO {
	private Integer managerId;
	private EmployeeDTO employee;
	private TeamsDTO team;
	private List<EmployeeDTO> employees;
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

	public void setTeam(TeamsDTO team) {
		this.team = team;
	}

	public TeamsDTO getTeam() {
		return team;
	}

}
