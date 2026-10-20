package com.pradeep.Employee_Management.service;

import java.util.List;

import com.pradeep.Employee_Management.dto.ProfileDTO;
import com.pradeep.Employee_Management.exception.EmployeeException;

public interface ProfileService {
	Integer createProfile(ProfileDTO dto)throws EmployeeException;

	ProfileDTO getProfileById(Integer id)throws EmployeeException;

	List<ProfileDTO> getAllProfiles()throws EmployeeException;

	ProfileDTO updateProfile(Integer id, ProfileDTO dto)throws EmployeeException;

	void deleteProfile(Integer id)throws EmployeeException;

	ProfileDTO getProfileByEmployeeId(Integer employeeId)throws EmployeeException;
}
