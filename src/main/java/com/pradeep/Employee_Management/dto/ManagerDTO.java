package com.pradeep.Employee_Management.dto;

import java.util.List;

import com.pradeep.Employee_Management.entity.Employee;
import com.pradeep.Employee_Management.entity.Projects;
import com.pradeep.Employee_Management.entity.Teams;

public class ManagerDTO {
	private Integer managerId;
	private Employee employee;
	private Teams team;
	private List<Employee> employees;
	private Projects projectsMentored;
	public Integer getManagerId() {
		return managerId;
	}
	public void setManagerId(Integer managerId) {
		this.managerId = managerId;
	}
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	public Teams getTeam() {
		return team;
	}
	public void setTeam(Teams team) {
		this.team = team;
	}
	public List<Employee> getEmployees() {
		return employees;
	}
	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}
	public Projects getProjectsMentored() {
		return projectsMentored;
	}
	public void setProjectsMentored(Projects projectsMentored) {
		this.projectsMentored = projectsMentored;
	}
}
