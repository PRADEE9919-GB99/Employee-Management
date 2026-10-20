package com.pradeep.Employee_Management.entity;

import java.util.List;

import com.pradeep.Employee_Management.enums.Gender;
import com.pradeep.Employee_Management.enums.Teams;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Employee {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer employeeId;
	private String employeeName;
	@Column(nullable = false, unique = true)
	private String email;
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private Gender gender;
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private Teams team;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "department_id")
	private Department department;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "manager_id")
	private Manager manager;
	@OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
	private List<Projects> projects;

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

	public Teams getTeam() {
		return team;
	}

	public void setTeam(Teams team) {
		this.team = team;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public Manager getManager() {
		return manager;
	}

	public void setManager(Manager manager) {
		this.manager = manager;
	}

	public List<Projects> getProjects() {
		return projects;
	}

	public void setProjects(List<Projects> projects) {
		this.projects = projects;
	}
}
