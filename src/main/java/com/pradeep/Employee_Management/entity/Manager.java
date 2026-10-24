
package com.pradeep.Employee_Management.entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class Manager {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer managerId;

	@OneToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "employee_id", unique = true, nullable = false)
	private Employee employee;

	@OneToMany(mappedBy = "manager")
	private List<Teams> teams;

	@OneToMany(mappedBy = "manager")
	private List<Employee> employees;

	@OneToMany(mappedBy = "manager")
	private List<Projects> projectsMentored;

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

	public List<Teams> getTeams() {
		return teams;
	}

	public void setTeams(List<Teams> teams) {
		this.teams = teams;
	}

	public List<Projects> getProjectsMentored() {
		return projectsMentored;
	}

	public void setProjectsMentored(List<Projects> projectsMentored) {
		this.projectsMentored = projectsMentored;
	}

	public List<Teams> getTeam() {
		return teams;
	}

	public void setTeam(List<Teams> team) {
		this.teams = team;
	}

	public List<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}
}
