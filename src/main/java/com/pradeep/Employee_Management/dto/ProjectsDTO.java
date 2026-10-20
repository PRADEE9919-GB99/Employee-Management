package com.pradeep.Employee_Management.dto;

import com.pradeep.Employee_Management.enums.Status;

public class ProjectsDTO {
	private Long projectId;
	private String projectName;
	private Status status;
	private EmployeeDTO employee;
	private ManagerDTO manager;

	public Long getProjectId() {
		return projectId;
	}

	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public void setEmployee(EmployeeDTO employee) {
		this.employee = employee;
	}

	public EmployeeDTO getEmployee() {
		return employee;
	}

	public ManagerDTO getManager() {
		return manager;
	}

	public void setManager(ManagerDTO manager) {
		this.manager = manager;
	}
}
