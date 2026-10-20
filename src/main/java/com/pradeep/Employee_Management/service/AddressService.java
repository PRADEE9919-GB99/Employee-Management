package com.pradeep.Employee_Management.service;

import java.util.List;

import com.pradeep.Employee_Management.dto.AddressDTO;
import com.pradeep.Employee_Management.exception.EmployeeException;

public interface AddressService {
	Integer createAddress(AddressDTO dto)throws EmployeeException ;

	AddressDTO getAddressById(Integer id)throws EmployeeException ;

	List<AddressDTO> getAllAddresses()throws EmployeeException ;

	AddressDTO updateAddress(Integer id, AddressDTO dto)throws EmployeeException ;

	void deleteAddress(Integer id)throws EmployeeException ;
}
