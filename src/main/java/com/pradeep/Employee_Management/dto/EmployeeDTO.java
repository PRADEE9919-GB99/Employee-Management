package com.pradeep.Employee_Management.dto;

import java.util.List;

import com.pradeep.Employee_Management.enums.Gender;

public class EmployeeDTO {
	private Integer employeeId;
	private String employeeName;
	private String email;

	private Gender gender;
	private TeamsResponseDTO team;
	private ProfileDTO profile;
	private AddressDTO address;
	private List<ProjectResponseDTO> projects;
	private ManagerResponseDTO manager;
	private DepartmentResponseDTO department;

	public Integer getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public ProfileDTO getProfile() {
		return profile;
	}

	public void setProfile(ProfileDTO profile) {
		this.profile = profile;
	}

	public AddressDTO getAddress() {
		return address;
	}

	public void setAddress(AddressDTO address) {
		this.address = address;
	}

	public ManagerResponseDTO getManager() {
		return manager;
	}

	public void setManager(ManagerResponseDTO manager) {
		this.manager = manager;
	}

	public void setTeam(TeamsResponseDTO team) {
		this.team = team;
	}

	public TeamsResponseDTO getTeam() {
		return team;
	}

	public void setDepartment(DepartmentResponseDTO department) {
		this.department = department;
	}

	public DepartmentResponseDTO getDepartment() {
		return department;
	}

	public void setProjects(List<ProjectResponseDTO> projects) {
		this.projects = projects;
	}

	public List<ProjectResponseDTO> getProjects() {
		return projects;
	}

}
