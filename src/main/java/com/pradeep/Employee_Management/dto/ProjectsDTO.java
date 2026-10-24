package com.pradeep.Employee_Management.dto;

import com.pradeep.Employee_Management.enums.Status;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class ProjectsDTO {
	private Long projectId;
	@NotBlank(message = "{projects.projectName.notBlank}")
	@Size(min = 2, max = 150, message = "{projects.projectName.size}")
	private String projectName;
	@NotNull(message = "{projects.status.notNull}")
	private Status status;
	@Valid
	private EmployeeDTO employee;
	@Valid
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
