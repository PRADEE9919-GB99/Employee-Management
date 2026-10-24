package com.pradeep.Employee_Management.dto;

import com.pradeep.Employee_Management.enums.Status;

public class ProjectResponseDTO {
	private Long projectId;
	private String projectName;
	private Status status;

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
}
