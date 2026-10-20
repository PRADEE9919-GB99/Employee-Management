package com.pradeep.Employee_Management.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pradeep.Employee_Management.dto.AddressDTO;
import com.pradeep.Employee_Management.entity.Address;
import com.pradeep.Employee_Management.exception.EmployeeException;
import com.pradeep.Employee_Management.repository.AddressRepository;
import com.pradeep.Employee_Management.util.AddressUtil;

@Service
public class AddressServiceImpl implements AddressService {
	
	private AddressUtil addressUtil;
	private AddressRepository addressRepo;

	public AddressServiceImpl(AddressUtil addressUtil, AddressRepository addressRepo) {
		super();
		this.addressUtil = addressUtil;
		this.addressRepo = addressRepo;
	}

	@Override
	public Integer createAddress(AddressDTO dto) throws EmployeeException {
		// TODO Auto-generated method stub
		Address address = addressUtil.toEntity(dto);
		addressRepo.save(address);
		return address.getAddressId();
	}

	@Override
	public AddressDTO getAddressById(Integer id) throws EmployeeException {

		Address address = addressRepo.findById(id)
				.orElseThrow(() -> new EmployeeException("ADDRESS.SERVICE_ADDRESS_NOT_FOUND"));

		return addressUtil.toDTO(address);
	}

	@Override
	public List<AddressDTO> getAllAddresses() throws EmployeeException {
		// TODO Auto-generated method stub
		List<Address> addresses = addressRepo.findAll();
		List<AddressDTO> adto = new ArrayList<>();
		for (Address address : addresses) {
			AddressDTO addressDTO = addressUtil.toDTO(address);
			adto.add(addressDTO);
		}

		return adto;

	}

	@Override
	public AddressDTO updateAddress(Integer id, AddressDTO dto) throws EmployeeException {
		// TODO Auto-generated method stub
		Address address = addressRepo.findById(id)
				.orElseThrow(() -> new EmployeeException("ADDRESS.SERVICE_ADDRESS_NOT_FOUND"));

		Address updatedAddress = addressUtil.toEntity(dto);
		updatedAddress.setAddressId(address.getAddressId());

		addressRepo.save(updatedAddress);

		return addressUtil.toDTO(updatedAddress);
	}

	@Override
	public void deleteAddress(Integer id) throws EmployeeException {
		// TODO Auto-generated method stub
		Address address = addressRepo.findById(id)
				.orElseThrow(() -> new EmployeeException("ADDRESS.SERVICE_ADDRESS_NOT_FOUND"));

		addressRepo.delete(address);
	}

}
