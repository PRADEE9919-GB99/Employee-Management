package com.pradeep.Employee_Management.dto;

import com.pradeep.Employee_Management.entity.Employee;
import com.pradeep.Employee_Management.entity.Status;

public class ProjectsSDTO {
	private Long projectId;
	private String projectName;
	private Status status;
	private Employee employee;
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
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
}
