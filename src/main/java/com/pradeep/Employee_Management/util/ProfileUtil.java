package com.pradeep.Employee_Management.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pradeep.Employee_Management.dto.AddressDTO;
import com.pradeep.Employee_Management.dto.EmployeeDTO;
import com.pradeep.Employee_Management.dto.ProfileDTO;
import com.pradeep.Employee_Management.entity.Address;
import com.pradeep.Employee_Management.entity.Employee;
import com.pradeep.Employee_Management.entity.Profile;
import com.pradeep.Employee_Management.exception.EmployeeException;

@Component
public class ProfileUtil {
	@Autowired
	private AddressUtil addressUtil;

	public static Profile toEntity(ProfileDTO dto) throws EmployeeException {
		return toEntity(dto, null, null);
	}

	public static Profile toEntity(ProfileDTO dto, Address address, Employee employee) throws EmployeeException {

		if (dto == null) {
			throw new EmployeeException("GENERAL.ITEM_NOT_NULL");
		}

		Profile profile = new Profile();

		profile.setProfileId(dto.getProfileId());
		profile.setStartDate(dto.getStartDate());
		profile.setEndDate(dto.getEndDate());
		profile.setDesignation(dto.getDesignation());
		profile.setEmployeePhoneNo(dto.getEmployeePhoneNo());

		profile.setAddress(address);
		profile.setEmployee(employee);

		return profile;
	}

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

		// Map Address
		if (profile.getAddress() != null) {
			AddressDTO addressDTO = AddressUtil.toDTO(profile.getAddress());

			dto.setAddress(addressDTO);
		}

		// Map Employee
		if (profile.getEmployee() != null) {
			EmployeeDTO employeeDTO = EmployeeUtil.toDTO(profile.getEmployee());

			dto.setEmployee(employeeDTO);
		}

		return dto;
	}
	
}
