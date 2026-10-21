package com.pradeep.Employee_Management.dto;

public class TeamsDTO {
	private Integer teamsId;
	private String teamName;
	private ManagerDTO manager;
	private DepartmentDTO department;
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
	public ManagerDTO getManager() {
		return manager;
	}
	public void setManager(ManagerDTO manager) {
		this.manager = manager;
	}
	public DepartmentDTO getDepartment() {
		return department;
	}
	public void setDepartment(DepartmentDTO department) {
		this.department = department;
	}
	
}
