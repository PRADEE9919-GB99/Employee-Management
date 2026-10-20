package com.pradeep.Employee_Management.util;

import org.springframework.stereotype.Component;

import com.pradeep.Employee_Management.dto.AddressDTO;
import com.pradeep.Employee_Management.entity.Address;
import com.pradeep.Employee_Management.exception.EmployeeException;

@Component
public class AddressUtil {
	public static Address toEntity(AddressDTO dto) throws EmployeeException {
		if (dto == null) {
			throw new EmployeeException("GENERAL.ITEM_NOT_NULL");
		}

		Address address = new Address();
		address.setAddressId(dto.getAddressId());
		address.setCity(dto.getCity());
		address.setState(dto.getState());
		address.setCountry(dto.getCountry());

		return address;
	}

	public static AddressDTO toDTO(Address address) throws EmployeeException {
		if (address == null) {
			throw new EmployeeException("GENERAL.ITEM_NOT_NULL");
		}

		AddressDTO dto = new AddressDTO();
		dto.setAddressId(address.getAddressId());
		dto.setCity(address.getCity());
		dto.setState(address.getState());
		dto.setCountry(address.getCountry());

		return dto;
	}
}
