package com.pradeep.Employee_Management.dto;

import java.util.List;

public class DepartmentDTO {
	private Long departmentId;
	private String departmentName;
	private List<TeamsDTO> teams;

	public Long getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Long departmentId) {
		this.departmentId = departmentId;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public List<TeamsDTO> getTeams() {
		return teams;
	}

	public void setTeams(List<TeamsDTO> teams) {
		this.teams = teams;
	}

}
