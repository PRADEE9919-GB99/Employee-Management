package com.pradeep.Employee_Management.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
@Entity
public class Profile {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer profileId;
	private LocalDate startDate;
	private LocalDate endDate;
	private String designation;
	private String employeePhoneNo;

	@OneToOne
	@JoinColumn(name = "address_id", unique = true)
	private Address address;
	@OneToOne(optional = false)
	@JoinColumn(name = "employee_id", unique = true)
	private Employee employee;

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

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
}
