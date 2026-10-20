package com.pradeep.Employee_Management.service;

import java.util.List;

import com.pradeep.Employee_Management.dto.AddressDTO;

public interface AddressService {
	Integer createAddress(AddressDTO dto);

	AddressDTO getAddressById(Integer id);

	List<AddressDTO> getAllAddresses();

	AddressDTO updateAddress(Integer id, AddressDTO dto);

	void deleteAddress(Integer id);
}
