package com.pradeep.Employee_Management;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.pradeep.Employee_Management.dto.AddressDTO;
import com.pradeep.Employee_Management.entity.Address;
import com.pradeep.Employee_Management.exception.EmployeeException;
import com.pradeep.Employee_Management.repository.AddressRepository;
import com.pradeep.Employee_Management.service.AddressServiceImpl;

@ExtendWith(MockitoExtension.class)
public class AddressServiceImplTest {

	@Mock
	private AddressRepository addressRepo;

	@InjectMocks
	private AddressServiceImpl addressService;

	private Address address;
	private AddressDTO addressDTO;

	@BeforeEach
	void setUp() {
		address = new Address();
		address.setAddressId(1);
		address.setCity("Mysore");
		address.setState("Karnataka");
		address.setCountry("India");

		addressDTO = new AddressDTO();
		addressDTO.setAddressId(1);
		addressDTO.setCity("Mysore");
		addressDTO.setState("Karnataka");
		addressDTO.setCountry("India");
	}

	// 1. Test createAddress()
	@Test
	void createAddressSuccess() throws EmployeeException {

		when(addressRepo.save(any(Address.class))).thenAnswer(invocation -> {
			Address savedAddress = invocation.getArgument(0);
			savedAddress.setAddressId(1);
			return savedAddress;
		});

		Integer result = addressService.createAddress(addressDTO);

		assertEquals(1, result);
		verify(addressRepo).save(any(Address.class));
	}

	// 2. Test getAddressById() - success
	@Test
	void getAddressByIdSuccess() throws EmployeeException {

		when(addressRepo.findById(1)).thenReturn(Optional.of(address));

		AddressDTO result = addressService.getAddressById(1);

		assertNotNull(result);
		assertEquals(1, result.getAddressId());
		assertEquals("Mysore", result.getCity());
		assertEquals("Karnataka", result.getState());
		assertEquals("India", result.getCountry());

		verify(addressRepo).findById(1);
	}

	// 3. Test getAddressById() - address not found
	@Test
	void getAddressByIdNotFound() {

		when(addressRepo.findById(99)).thenReturn(Optional.empty());

		EmployeeException exception = assertThrows(EmployeeException.class, () -> addressService.getAddressById(99));

		assertEquals("ADDRESS.SERVICE_ADDRESS_NOT_FOUND", exception.getMessage());

		verify(addressRepo).findById(99);
	}

	// 4. Test getAllAddresses() - success
	@Test
	void getAllAddressesSuccess() throws EmployeeException {

		Address secondAddress = new Address();
		secondAddress.setAddressId(2);
		secondAddress.setCity("Bengaluru");
		secondAddress.setState("Karnataka");
		secondAddress.setCountry("India");

		when(addressRepo.findAll()).thenReturn(Arrays.asList(address, secondAddress));

		var result = addressService.getAllAddresses();

		assertEquals(2, result.size());
		assertEquals("Mysore", result.get(0).getCity());
		assertEquals("Bengaluru", result.get(1).getCity());

		verify(addressRepo).findAll();
	}

	// 5. Test getAllAddresses() - empty list
	@Test
	void getAllAddressesEmpty() throws EmployeeException {

		when(addressRepo.findAll()).thenReturn(Collections.emptyList());

		var result = addressService.getAllAddresses();

		assertNotNull(result);
		assertEquals(0, result.size());

		verify(addressRepo).findAll();
	}

	// 6. Test updateAddress() - success
	@Test
	void updateAddressSuccess() throws EmployeeException {

		AddressDTO updatedDTO = new AddressDTO();
		updatedDTO.setCity("Bengaluru");
		updatedDTO.setState("Karnataka");
		updatedDTO.setCountry("India");

		when(addressRepo.findById(1)).thenReturn(Optional.of(address));

		when(addressRepo.save(any(Address.class))).thenAnswer(invocation -> invocation.getArgument(0));

		AddressDTO result = addressService.updateAddress(1, updatedDTO);

		assertNotNull(result);
		assertEquals(1, result.getAddressId());
		assertEquals("Bengaluru", result.getCity());
		assertEquals("Karnataka", result.getState());
		assertEquals("India", result.getCountry());

		verify(addressRepo).findById(1);
		verify(addressRepo).save(any(Address.class));
	}

	// 7. Test updateAddress() - address not found
	@Test
	void updateAddressNotFound() {

		when(addressRepo.findById(99)).thenReturn(Optional.empty());

		assertThrows(EmployeeException.class, () -> addressService.updateAddress(99, addressDTO));

		verify(addressRepo).findById(99);
		verify(addressRepo, never()).save(any(Address.class));
	}

	// 8. Test deleteAddress() - success
	@Test
	void deleteAddressSuccess() throws EmployeeException {

		when(addressRepo.findById(1)).thenReturn(Optional.of(address));
		doNothing().when(addressRepo).delete(address);

		addressService.deleteAddress(1);

		verify(addressRepo).findById(1);
		verify(addressRepo).delete(address);
	}

	// 9. Test deleteAddress() - address not found
	@Test
	void deleteAddressNotFound() {

		when(addressRepo.findById(99)).thenReturn(Optional.empty());

		assertThrows(EmployeeException.class, () -> addressService.deleteAddress(99));

		verify(addressRepo).findById(99);
		verify(addressRepo, never()).delete(any(Address.class));
	}

}
