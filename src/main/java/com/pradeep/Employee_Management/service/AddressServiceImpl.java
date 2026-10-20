package com.pradeep.Employee_Management.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pradeep.Employee_Management.dto.AddressDTO;
import com.pradeep.Employee_Management.entity.Address;
import com.pradeep.Employee_Management.repository.AddressRepository;
import com.pradeep.Employee_Management.util.AddressUtil;

@Service
public class AddressServiceImpl implements AddressService {
	@Autowired
	private AddressUtil addressUtil;
	@Autowired
	private AddressRepository addressRepo;

	@Override
	public Integer createAddress(AddressDTO dto) {
		// TODO Auto-generated method stub
		Address address = addressUtil.toEntity(dto);
		addressRepo.save(address);
		return address.getAddressId();
	}

	@Override
	public AddressDTO getAddressById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AddressDTO> getAllAddresses() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AddressDTO updateAddress(Integer id, AddressDTO dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteAddress(Integer id) {
		// TODO Auto-generated method stub

	}

}
