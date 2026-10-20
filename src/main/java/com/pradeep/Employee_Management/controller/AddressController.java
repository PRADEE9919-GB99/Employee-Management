package com.pradeep.Employee_Management.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pradeep.Employee_Management.dto.AddressDTO;
import com.pradeep.Employee_Management.exception.EmployeeException;
import com.pradeep.Employee_Management.service.AddressService;

@RestController
@RequestMapping("/address")
public class AddressController {

	@Autowired
	private AddressService addressService;

	@Autowired
	private Environment environment;

	@PostMapping
	public ResponseEntity<Integer> addAddress(@RequestBody AddressDTO addressDTO) throws EmployeeException {

		Integer addressId = addressService.createAddress(addressDTO);

		return ResponseEntity.status(HttpStatus.CREATED).body(addressId);
	}

	@GetMapping("/{addressId}")
	public ResponseEntity<AddressDTO> getAddressById(@PathVariable Integer addressId) throws EmployeeException {

		AddressDTO address = addressService.getAddressById(addressId);

		return new ResponseEntity<>(address, HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<List<AddressDTO>> getAllAddresses() throws EmployeeException {

		List<AddressDTO> addresses = addressService.getAllAddresses();

		return new ResponseEntity<>(addresses, HttpStatus.OK);
	}

	@PutMapping("/update/{addressId}")
	public ResponseEntity<AddressDTO> updateAddress(@PathVariable Integer addressId, @RequestBody AddressDTO dto)
			throws EmployeeException {

		AddressDTO address = addressService.updateAddress(addressId, dto);

		return new ResponseEntity<>(address, HttpStatus.OK);
	}

}
