package com.pradeep.Employee_Management.service;

import java.util.List;

import com.pradeep.Employee_Management.dto.ProfileDTO;

public interface ProfileService {
	ProfileDTO createProfile(ProfileDTO dto);

	ProfileDTO getProfileById(Integer id);

	List<ProfileDTO> getAllProfiles();

	ProfileDTO updateProfile(Integer id, ProfileDTO dto);

	void deleteProfile(Integer id);

	ProfileDTO getProfileByEmployeeId(Integer employeeId);
}
