package com.pradeep.Employee_Management.dto;

import java.util.List;

public class DepartmentDTO {
	private Long departmentId;
	private String departmentName;
	private List<EmployeeDTO> employee;
	public Long getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(Long departmentId) {
		this.departmentId = departmentId;
	}
	
	public List<EmployeeDTO> getEmployee() {
		return employee;
	}
	public void setEmployee(List<EmployeeDTO> employee) {
		this.employee = employee;
	}
	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	
}
