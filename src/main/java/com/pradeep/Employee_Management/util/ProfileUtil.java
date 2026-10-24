
package com.pradeep.Employee_Management.util;

import org.springframework.stereotype.Component;

import com.pradeep.Employee_Management.dto.ProfileDTO;
import com.pradeep.Employee_Management.entity.Profile;
import com.pradeep.Employee_Management.exception.EmployeeException;

@Component
public class ProfileUtil {

	// Convert ProfileDTO to Profile entity
	public static Profile toEntity(ProfileDTO dto) throws EmployeeException {

		if (dto == null) {
			throw new EmployeeException("GENERAL.ITEM_NOT_NULL");
		}

		Profile profile = new Profile();

		profile.setProfileId(dto.getProfileId());
		profile.setStartDate(dto.getStartDate());
		profile.setEndDate(dto.getEndDate());
		profile.setDesignation(dto.getDesignation());
		profile.setEmployeePhoneNo(dto.getEmployeePhoneNo());

		return profile;
	}

	// Convert Profile entity to ProfileDTO
	public static ProfileDTO toDTO(Profile profile) throws EmployeeException {

		if (profile == null) {
			throw new EmployeeException("GENERAL.ITEM_NOT_NULL");
		}

		ProfileDTO dto = new ProfileDTO();

		dto.setProfileId(profile.getProfileId());
		dto.setStartDate(profile.getStartDate());
		dto.setEndDate(profile.getEndDate());
		dto.setDesignation(profile.getDesignation());
		dto.setEmployeePhoneNo(profile.getEmployeePhoneNo());

		return dto;
	}
}
