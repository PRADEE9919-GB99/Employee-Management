package com.pradeep.Employee_Management.util;

import org.springframework.stereotype.Component;

import com.pradeep.Employee_Management.dto.AddressDTO;
import com.pradeep.Employee_Management.entity.Address;

@Component
public class AddressUtil {
	public static Address toEntity(AddressDTO dto) {
		if (dto == null)
			return null;

		Address address = new Address();
		address.setAddressId(dto.getAddressId());
		address.setCity(dto.getCity());
		address.setState(dto.getState());
		address.setCountry(dto.getCountry());

		return address;
	}

	public static AddressDTO toDTO(Address address) {
		if (address == null)
			return null;

		AddressDTO dto = new AddressDTO();
		dto.setAddressId(address.getAddressId());
		dto.setCity(address.getCity());
		dto.setState(address.getState());
		dto.setCountry(address.getCountry());

		return dto;
	}
}
