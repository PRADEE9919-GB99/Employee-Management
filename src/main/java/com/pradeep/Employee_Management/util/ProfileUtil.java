package com.pradeep.Employee_Management.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pradeep.Employee_Management.dto.EmployeeDTO;
import com.pradeep.Employee_Management.dto.ProfileDTO;
import com.pradeep.Employee_Management.entity.Employee;
import com.pradeep.Employee_Management.entity.Profile;
@Component
public class ProfileUtil {
	@Autowired
	private AddressUtil addressUtil;
	public ProfileDTO toDTO(Profile profile) {
	    if (profile == null)
	        return null;

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
}
