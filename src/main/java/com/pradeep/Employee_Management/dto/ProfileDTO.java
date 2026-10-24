package com.pradeep.Employee_Management.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class ProfileDTO {
	private Integer profileId;
	@NotNull(message = "{profile.startDate.notNull}")
	@PastOrPresent(message = "Start date cannot be in the future")
	private LocalDate startDate;
	private LocalDate endDate;
	@NotBlank(message = "{profile.designation.notBlank}")
	@Size(min = 2, max = 100, message = "{profile.designation.size}")
	private String designation;
	@NotBlank(message = "{profile.phone.notBlank}")
	@Pattern(regexp = "^^[6-9]\\\\d{9}$", message = "{profile.phone.invalid}")
	private String employeePhoneNo;

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

}
