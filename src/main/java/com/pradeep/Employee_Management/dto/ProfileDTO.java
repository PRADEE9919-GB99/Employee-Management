package com.pradeep.Employee_Management.dto;

import java.time.LocalDate;

public class ProfileDTO {
	private Integer profileId;
	private LocalDate startDate;
	private LocalDate endDate;
	private String designation;
	private String employeePhoneNo;
	private AddressDTO address;
	private EmployeeDTO employee;
	public Integer getProfileId() {
		return profileId;
	}
	public void setProfileId(Integer profileId) {
		this.profileId = profileId;
	}
	public LocalDate getStartDate() {
		return startDate;
	}
	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}
	public LocalDate getEndDate() {
		return endDate;
	}
	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public String getEmployeePhoneNo() {
		return employeePhoneNo;
	}
	public void setEmployeePhoneNo(String employeePhoneNo) {
		this.employeePhoneNo = employeePhoneNo;
	}
	public AddressDTO getAddress() {
		return address;
	}
	public void setAddress(AddressDTO address) {
		this.address = address;
	}
	public EmployeeDTO getEmployee() {
		return employee;
	}
	public void setEmployee(EmployeeDTO employee) {
		this.employee = employee;
	}
	
}
