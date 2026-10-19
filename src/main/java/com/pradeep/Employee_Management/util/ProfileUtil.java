package com.pradeep.Employee_Management.util;

import org.springframework.stereotype.Component;

import com.pradeep.Employee_Management.dto.EmployeeDTO;
import com.pradeep.Employee_Management.dto.ProfileDTO;
import com.pradeep.Employee_Management.entity.Address;
import com.pradeep.Employee_Management.entity.Employee;
import com.pradeep.Employee_Management.entity.Profile;
@Component
public class ProfileUtil {
	public static Profile toEntity(ProfileDTO dto) {
		return toEntity(dto, null, null);
	}

	public static Profile toEntity(ProfileDTO dto, Address address, Employee employee) {

		if (dto == null)
			return null;

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

	public static ProfileDTO toDTO(Profile profile) {
		if (profile == null)
			return null;

		ProfileDTO dto = new ProfileDTO();
		dto.setProfileId(profile.getProfileId());
		dto.setStartDate(profile.getStartDate());
		dto.setEndDate(profile.getEndDate());
		dto.setDesignation(profile.getDesignation());
		dto.setEmployeePhoneNo(profile.getEmployeePhoneNo());
		dto.setAddress(AddressUtil.toDTO(profile.getAddress()));

		if (profile.getEmployee() != null) {
			Employee employee = profile.getEmployee();

			EmployeeDTO employeeDTO = new EmployeeDTO();
			employeeDTO.setEmployeeId(employee.getEmployeeId());
			employeeDTO.setEmployeeName(employee.getEmployeeName());
			employeeDTO.setEmail(employee.getEmail());
			employeeDTO.setGender(employee.getGender());
			employeeDTO.setTeam(employee.getTeam());

			dto.setEmployee(employeeDTO);
		}

		return dto;
	}
}
