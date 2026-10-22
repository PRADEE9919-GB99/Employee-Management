package com.pradeep.Employee_Management.controller;

import java.util.List;
import jakarta.validation.Valid;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
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

	private AddressService addressService;
	private Environment environment;

	public AddressController(AddressService addressService, Environment environment) {
		super();
		this.addressService = addressService;
		this.environment = environment;
	}

	@PostMapping
	public ResponseEntity<String> addAddress(@Valid @RequestBody AddressDTO addressDTO) throws EmployeeException {

		Integer addressId = addressService.createAddress(addressDTO);
		String message = environment.getProperty("ADDRESS.API.SUCCESS");

		return ResponseEntity.status(HttpStatus.CREATED).body(message + addressId);
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

	@PutMapping("/{addressId}")
	public ResponseEntity<String> updateAddress(@PathVariable Integer addressId, @RequestBody AddressDTO dto)
			throws EmployeeException {

		addressService.updateAddress(addressId, dto);
		String message = environment.getProperty("ADDRESS.API.UPDATE_SUCCESS");

		return new ResponseEntity<>(message, HttpStatus.OK);
	}

	@DeleteMapping("/{addressId}")
	public ResponseEntity<String> deleteAddress(@PathVariable Integer addressId) throws EmployeeException {

		addressService.deleteAddress(addressId);
		String message = environment.getProperty("ADDRESS.API.DELETE_SUCCESS");
		return new ResponseEntity<>(message, HttpStatus.OK);
	}

}
