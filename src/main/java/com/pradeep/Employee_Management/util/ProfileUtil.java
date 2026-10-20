package com.pradeep.Employee_Management.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pradeep.Employee_Management.dto.EmployeeDTO;
import com.pradeep.Employee_Management.dto.ProfileDTO;
import com.pradeep.Employee_Management.entity.Employee;
import com.pradeep.Employee_Management.entity.Profile;
import com.pradeep.Employee_Management.exception.EmployeeException;

@Component
public class ProfileUtil {
	@Autowired
	private AddressUtil addressUtil;

	public ProfileDTO toDTO(Profile profile) throws EmployeeException {
		if (profile == null) {
			throw new EmployeeException("GENERAL.ITEM_NOT_NULL");
		}
		ProfileDTO dto = new ProfileDTO();
		dto.setProfileId(profile.getProfileId());
		dto.setStartDate(profile.getStartDate());
		dto.setEndDate(profile.getEndDate());
		dto.setDesignation(profile.getDesignation());
		dto.setEmployeePhoneNo(profile.getEmployeePhoneNo());
		dto.setAddress(addressUtil.toDTO(profile.getAddress()));

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

	public Profile toEntity(ProfileDTO dto) throws EmployeeException {

		if (dto == null) {
			throw new EmployeeException("GENERAL.ITEM_NOT_NULL");
		}

		Profile profile = new Profile();

		profile.setProfileId(dto.getProfileId());
		profile.setStartDate(dto.getStartDate());
		profile.setEndDate(dto.getEndDate());
		profile.setDesignation(dto.getDesignation());
		profile.setEmployeePhoneNo(dto.getEmployeePhoneNo());
		profile.setAddress(addressUtil.toEntity(dto.getAddress()));

		if (dto.getEmployee() != null) {
			EmployeeDTO employeeDTO = dto.getEmployee();

			Employee employee = new Employee();
			employee.setEmployeeId(employeeDTO.getEmployeeId());
			employee.setEmployeeName(employeeDTO.getEmployeeName());
			employee.setEmail(employeeDTO.getEmail());
			employee.setGender(employeeDTO.getGender());
			employee.setTeam(employeeDTO.getTeam());

			profile.setEmployee(employee);
		}

		return profile;
	}
}
