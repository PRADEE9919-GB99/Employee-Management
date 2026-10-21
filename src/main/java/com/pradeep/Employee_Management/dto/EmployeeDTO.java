package com.pradeep.Employee_Management.dto;

import java.util.List;

import com.pradeep.Employee_Management.enums.Gender;


public class EmployeeDTO {
	private Integer employeeId;
	private String employeeName;
	private String email;

	private Gender gender;
	private TeamsDTO team;
	private DepartmentDTO department;
	private ManagerDTO manager;
	private List<ProjectsDTO> projects;

	public Integer getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	

	public DepartmentDTO getDepartment() {
		return department;
	}

	public void setDepartment(DepartmentDTO department) {
		this.department = department;
	}

	public ManagerDTO getManager() {
		return manager;
	}

	public void setManager(ManagerDTO manager) {
		this.manager = manager;
	}

	public List<ProjectsDTO> getProjects() {
		return projects;
	}

	public void setProjects(List<ProjectsDTO> projects) {
		this.projects = projects;
	}

	public TeamsDTO getTeam() {
		return team;
	}

	public void setTeam(TeamsDTO team) {
		this.team = team;
	}

}
