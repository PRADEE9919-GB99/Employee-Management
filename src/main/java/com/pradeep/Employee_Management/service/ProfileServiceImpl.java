
package com.pradeep.Employee_Management.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pradeep.Employee_Management.dto.ProfileDTO;
import com.pradeep.Employee_Management.entity.Employee;
import com.pradeep.Employee_Management.entity.Profile;
import com.pradeep.Employee_Management.exception.EmployeeException;
import com.pradeep.Employee_Management.repository.EmployeeRepository;
import com.pradeep.Employee_Management.repository.ProfileRepository;
import com.pradeep.Employee_Management.util.ProfileUtil;

@Service
@Transactional
public class ProfileServiceImpl implements ProfileService {

	private final ProfileRepository profileRepo;
	private final EmployeeRepository employeeRepo;

	public ProfileServiceImpl(ProfileRepository profileRepo, EmployeeRepository employeeRepo) {
		this.profileRepo = profileRepo;
		this.employeeRepo = employeeRepo;
	}

	@Override
	public Integer createProfile(ProfileDTO dto) throws EmployeeException {

		if (dto == null) {
			throw new EmployeeException("GENERAL.ITEM_NOT_NULL");
		}

		Profile profile = ProfileUtil.toEntity(dto);

		return profileRepo.save(profile).getProfileId();
	}

	@Override
	@Transactional(readOnly = true)
	public ProfileDTO getProfileById(Integer id) throws EmployeeException {

		Profile profile = profileRepo.findById(id).orElseThrow(() -> new EmployeeException("PROFILE.SERVICE_PROFILE_NOT_FOUND"));

		return ProfileUtil.toDTO(profile);
	}

	@Override
	@Transactional(readOnly = true)
	public List<ProfileDTO> getAllProfiles() throws EmployeeException {

		List<ProfileDTO> profileDTOs = new ArrayList<>();

		for (Profile profile : profileRepo.findAll()) {
			profileDTOs.add(ProfileUtil.toDTO(profile));
		}

		return profileDTOs;
	}

	@Override
	public ProfileDTO updateProfile(Integer id, ProfileDTO dto) throws EmployeeException {

		if (dto == null) {
			throw new EmployeeException("GENERAL.ITEM_NOT_NULL");
		}

		Profile existingProfile = profileRepo.findById(id)
				.orElseThrow(() -> new EmployeeException("PROFILE.SERVICE_PROFILE_NOT_FOUND"));

		if (dto.getStartDate() != null) {
			existingProfile.setStartDate(dto.getStartDate());
		}

		if (dto.getEndDate() != null) {
			existingProfile.setEndDate(dto.getEndDate());
		}

		if (dto.getDesignation() != null) {
			existingProfile.setDesignation(dto.getDesignation());
		}

		if (dto.getEmployeePhoneNo() != null) {
			existingProfile.setEmployeePhoneNo(dto.getEmployeePhoneNo());
		}

		Profile updatedProfile = profileRepo.save(existingProfile);

		return ProfileUtil.toDTO(updatedProfile);
	}

	@Override
	public void deleteProfile(Integer id) throws EmployeeException {

		Profile profile = profileRepo.findById(id).orElseThrow(() -> new EmployeeException("PROFILE.SERVICE_PROFILE_NOT_FOUND"));

		// Employee owns the profile relationship.
		// Unlink the profile before deleting it.
		for (Employee employee : employeeRepo.findAll()) {
			if (employee.getProfile() != null && id.equals(employee.getProfile().getProfileId())) {

				employee.setProfile(null);
			}
		}

		profileRepo.delete(profile);
	}

	@Override
	@Transactional(readOnly = true)
	public ProfileDTO getProfileByEmployeeId(Integer employeeId) throws EmployeeException {

		Employee employee = employeeRepo.findById(employeeId)
				.orElseThrow(() -> new EmployeeException("EMPLOYEE.SERVICE_EMPLOYEE_NOT_FOUND"));

		Profile profile = employee.getProfile();

		if (profile == null) {
			throw new EmployeeException("PROFILE.SERVICE_PROFILE_NOT_FOUND");
		}

		return ProfileUtil.toDTO(profile);
	}
}
