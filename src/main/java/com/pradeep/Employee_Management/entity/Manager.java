package com.pradeep.Employee_Management.entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
	@OneToOne(optional = false)
	@JoinColumn(name = "employee_id", unique = true)
	private Employee employee;

	@Enumerated(EnumType.STRING)
	private Teams team;
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

	public List<Projects> getProjectsMentored() {
		return projectsMentored;
	}

	public void setProjectsMentored(List<Projects> projectsMentored) {
		this.projectsMentored = projectsMentored;
	}

}
