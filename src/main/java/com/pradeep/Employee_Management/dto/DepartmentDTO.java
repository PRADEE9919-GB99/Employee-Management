package com.pradeep.Employee_Management.dto;

import java.util.List;

import com.pradeep.Employee_Management.entity.Employee;

public class DepartmentDTO {
	private Long departmentId;
	private String DepartmentName;
	private List<Employee> employee;
	public Long getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(Long departmentId) {
		this.departmentId = departmentId;
	}
	public String getDepartmentName() {
		return DepartmentName;
	}
	public void setDepartmentName(String departmentName) {
		DepartmentName = departmentName;
	}
	public List<Employee> getEmployee() {
		return employee;
	}
	public void setEmployee(List<Employee> employee) {
		this.employee = employee;
	}
}
