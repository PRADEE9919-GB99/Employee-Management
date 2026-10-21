package com.pradeep.Employee_Management.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

@Entity
public class Teams {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer teamsId;
	private String teamName;
	@OneToOne
	@JoinColumn(name = "manager_id", unique = true)
	private Manager manager;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "department_id", nullable = false)
	private Department department;

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

	public Manager getManager() {
		return manager;
	}

	public void setManager(Manager manager) {
		this.manager = manager;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

}
