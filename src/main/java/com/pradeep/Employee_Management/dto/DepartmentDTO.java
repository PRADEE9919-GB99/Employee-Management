package com.pradeep.Employee_Management.dto;

import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class DepartmentDTO {
	private Long departmentId;
	@NotBlank(message = "{department.departmentName.notBlank}")
	@Size(min = 2, max = 100, message = "{department.departmentName.size}")
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
