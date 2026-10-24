package com.pradeep.Employee_Management;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
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

import com.pradeep.Employee_Management.dto.ProfileDTO;
import com.pradeep.Employee_Management.entity.Employee;
import com.pradeep.Employee_Management.entity.Profile;
import com.pradeep.Employee_Management.exception.EmployeeException;
import com.pradeep.Employee_Management.repository.EmployeeRepository;
import com.pradeep.Employee_Management.repository.ProfileRepository;
import com.pradeep.Employee_Management.service.ProfileServiceImpl;

@ExtendWith(MockitoExtension.class)
class ProfileServiceImplTest {

	@Mock
	private ProfileRepository profileRepo;

	@Mock
	private EmployeeRepository employeeRepo;

	@InjectMocks
	private ProfileServiceImpl profileService;

	private Profile profile;
	private Employee employee;
	private ProfileDTO profileDTO;

	@BeforeEach
	void setUp() {
		profile = new Profile();
		profile.setProfileId(1);
		profile.setDesignation("Software Engineer");
		profile.setEmployeePhoneNo("9876543210");

		employee = new Employee();
		employee.setEmployeeId(10);
		employee.setProfile(profile);

		profileDTO = new ProfileDTO();
		profileDTO.setStartDate(profile.getStartDate());
		profileDTO.setEndDate(profile.getEndDate());
		profileDTO.setDesignation("Software Engineer");
		profileDTO.setEmployeePhoneNo("9876543210");
	}

	// CREATE PROFILE

	@Test
	void createProfile_Success() throws EmployeeException {
		when(profileRepo.save(any(Profile.class))).thenAnswer(invocation -> {
			Profile saved = invocation.getArgument(0);
			saved.setProfileId(1);
			return saved;
		});

		Integer result = profileService.createProfile(profileDTO);

		assertEquals(1, result);
		verify(profileRepo).save(any(Profile.class));
	}

	@Test
	void createProfile_NullDTO_ThrowsException() {
		assertThrows(EmployeeException.class, () -> profileService.createProfile(null));

		verify(profileRepo, never()).save(any(Profile.class));
	}

	// GET PROFILE BY ID

	@Test
	void getProfileById_Success() throws EmployeeException {
		when(profileRepo.findById(1)).thenReturn(Optional.of(profile));

		ProfileDTO result = profileService.getProfileById(1);

		assertNotNull(result);
		assertEquals("Software Engineer", result.getDesignation());
		assertEquals("9876543210", result.getEmployeePhoneNo());
		verify(profileRepo).findById(1);
	}

	@Test
	void getProfileById_NotFound_ThrowsException() {
		when(profileRepo.findById(99)).thenReturn(Optional.empty());

		assertThrows(EmployeeException.class, () -> profileService.getProfileById(99));

		verify(profileRepo).findById(99);
	}

	// GET ALL PROFILES

	@Test
	void getAllProfiles_Success() throws EmployeeException {
		Profile secondProfile = new Profile();
		secondProfile.setProfileId(2);
		secondProfile.setDesignation("QA Analyst");
		secondProfile.setEmployeePhoneNo("9123456780");

		when(profileRepo.findAll()).thenReturn(Arrays.asList(profile, secondProfile));

		var result = profileService.getAllProfiles();

		assertEquals(2, result.size());
		assertEquals("Software Engineer", result.get(0).getDesignation());
		assertEquals("QA Analyst", result.get(1).getDesignation());
		verify(profileRepo).findAll();
	}

	@Test
	void getAllProfiles_EmptyList() throws EmployeeException {
		when(profileRepo.findAll()).thenReturn(Collections.emptyList());

		var result = profileService.getAllProfiles();

		assertNotNull(result);
		assertTrue(result.isEmpty());
	}

	// UPDATE PROFILE

	@Test
	void updateProfile_Success() throws EmployeeException {
		ProfileDTO updateDTO = new ProfileDTO();
		updateDTO.setDesignation("Senior QA Analyst");
		updateDTO.setEmployeePhoneNo("9000011111");

		when(profileRepo.findById(1)).thenReturn(Optional.of(profile));
		when(profileRepo.save(any(Profile.class))).thenAnswer(invocation -> invocation.getArgument(0));

		ProfileDTO result = profileService.updateProfile(1, updateDTO);

		assertEquals("Senior QA Analyst", result.getDesignation());
		assertEquals("9000011111", result.getEmployeePhoneNo());
		verify(profileRepo).save(profile);
	}

	@Test
	void updateProfile_NullDTO_ThrowsException() {
		assertThrows(EmployeeException.class, () -> profileService.updateProfile(1, null));

		verify(profileRepo, never()).save(any(Profile.class));
	}

	@Test
	void updateProfile_ProfileNotFound_ThrowsException() {
		when(profileRepo.findById(99)).thenReturn(Optional.empty());

		assertThrows(EmployeeException.class, () -> profileService.updateProfile(99, profileDTO));

		verify(profileRepo, never()).save(any(Profile.class));
	}

	@Test
	void updateProfile_OnlyNonNullFieldsAreUpdated() throws EmployeeException {
		profile.setDesignation("Old Designation");
		profile.setEmployeePhoneNo("9000000000");

		ProfileDTO updateDTO = new ProfileDTO();
		updateDTO.setDesignation("New Designation");

		when(profileRepo.findById(1)).thenReturn(Optional.of(profile));
		when(profileRepo.save(any(Profile.class))).thenAnswer(invocation -> invocation.getArgument(0));

		ProfileDTO result = profileService.updateProfile(1, updateDTO);

		assertEquals("New Designation", result.getDesignation());
		assertEquals("9000000000", result.getEmployeePhoneNo());
		verify(profileRepo).save(profile);
	}

	// DELETE PROFILE

	@Test
	void deleteProfile_Success_UnlinksEmployeeProfile() throws EmployeeException {
		Employee anotherEmployee = new Employee();
		anotherEmployee.setEmployeeId(20);

		Employee employeeWithDifferentProfile = new Employee();
		employeeWithDifferentProfile.setEmployeeId(30);

		Profile anotherProfile = new Profile();
		anotherProfile.setProfileId(2);
		employeeWithDifferentProfile.setProfile(anotherProfile);

		when(profileRepo.findById(1)).thenReturn(Optional.of(profile));
		when(employeeRepo.findAll()).thenReturn(Arrays.asList(employee, anotherEmployee, employeeWithDifferentProfile));

		profileService.deleteProfile(1);

		assertNull(employee.getProfile());
		assertNull(anotherEmployee.getProfile());
		assertSame(anotherProfile, employeeWithDifferentProfile.getProfile());

		verify(profileRepo).delete(profile);
	}

	@Test
	void deleteProfile_NotFound_ThrowsException() {
		when(profileRepo.findById(99)).thenReturn(Optional.empty());

		assertThrows(EmployeeException.class, () -> profileService.deleteProfile(99));

		verify(employeeRepo, never()).findAll();
		verify(profileRepo, never()).delete(any(Profile.class));
	}

	// GET PROFILE BY EMPLOYEE ID

	@Test
	void getProfileByEmployeeId_Success() throws EmployeeException {
		when(employeeRepo.findById(10)).thenReturn(Optional.of(employee));

		ProfileDTO result = profileService.getProfileByEmployeeId(10);

		assertNotNull(result);
		assertEquals("Software Engineer", result.getDesignation());
		assertEquals("9876543210", result.getEmployeePhoneNo());
		verify(employeeRepo).findById(10);
	}

	@Test
	void getProfileByEmployeeId_EmployeeNotFound_ThrowsException() {
		when(employeeRepo.findById(99)).thenReturn(Optional.empty());

		assertThrows(EmployeeException.class, () -> profileService.getProfileByEmployeeId(99));
	}

	@Test
	void getProfileByEmployeeId_ProfileNotAssigned_ThrowsException() {
		Employee employeeWithoutProfile = new Employee();
		employeeWithoutProfile.setEmployeeId(20);
		employeeWithoutProfile.setProfile(null);

		when(employeeRepo.findById(20)).thenReturn(Optional.of(employeeWithoutProfile));

		assertThrows(EmployeeException.class, () -> profileService.getProfileByEmployeeId(20));
	}
}
