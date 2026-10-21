package com.pradeep.Employee_Management.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pradeep.Employee_Management.dto.ProfileDTO;
import com.pradeep.Employee_Management.entity.Address;
import com.pradeep.Employee_Management.entity.Employee;
import com.pradeep.Employee_Management.entity.Profile;
import com.pradeep.Employee_Management.exception.EmployeeException;
import com.pradeep.Employee_Management.repository.AddressRepository;
import com.pradeep.Employee_Management.repository.EmployeeRepository;
import com.pradeep.Employee_Management.repository.ProfileRepository;
import com.pradeep.Employee_Management.util.ProfileUtil;

@Service
@Transactional
public class ProfileServiceImpl implements ProfileService {
	private ProfileRepository profileRepo;
	private EmployeeRepository employeeRepo;
	private AddressRepository addressRepo;

	public ProfileServiceImpl(ProfileRepository profileRepo, EmployeeRepository employeeRepo,
			AddressRepository addressRepo) {
		super();
		this.profileRepo = profileRepo;
		this.employeeRepo = employeeRepo;
		this.addressRepo = addressRepo;
	}

	@Override
	public Integer createProfile(ProfileDTO dto) throws EmployeeException {
		// TODO Auto-generated method stub
		Integer employeeId = dto.getEmployee().getEmployeeId();

		Employee employee = employeeRepo.findById(employeeId)
				.orElseThrow(() -> new EmployeeException("EMPLOYEE.NOT_FOUND"));

		if (profileRepo.existsByEmployee_EmployeeId(employeeId)) {
			throw new EmployeeException("PROFILE.ALREADY_EXISTS");
		}

		Address address = null;

		if (dto.getAddress() != null) {

			if (dto.getAddress().getAddressId() == null) {
				throw new EmployeeException("GENERAL.ITEM_NOT_NULL");
			}

			Integer addressId = dto.getAddress().getAddressId();

			address = addressRepo.findById(addressId).orElseThrow(() -> new EmployeeException("ADDRESS.NOT_FOUND"));

			if (profileRepo.existsByAddress_AddressId(addressId)) {
				throw new EmployeeException("ADDRESS.ALREADY_ASSIGNED");
			}
		}

		Profile profile = ProfileUtil.toEntity(dto, address, employee);

		return profileRepo.save(profile).getProfileId();
	}

	@Override
	public ProfileDTO getProfileById(Integer id) throws EmployeeException {
		Profile profile = profileRepo.findById(id).orElseThrow(() -> new EmployeeException("PROFILE.NOT_FOUND"));

		return ProfileUtil.toDTO(profile);
	}

	@Override
	public List<ProfileDTO> getAllProfiles() throws EmployeeException {
		// TODO Auto-generated method stub
		List<ProfileDTO> profileDTOs = new ArrayList<>();

		for (Profile profile : profileRepo.findAll()) {
			profileDTOs.add(ProfileUtil.toDTO(profile));
		}

		return profileDTOs;
	}

	@Override
	public ProfileDTO updateProfile(Integer id, ProfileDTO dto) throws EmployeeException {
		// TODO Auto-generated method stub
		Profile existingProfile = profileRepo.findById(id)
				.orElseThrow(() -> new EmployeeException("PROFILE.NOT_FOUND"));

		if (dto.getEmployee() != null) {

			if (dto.getEmployee().getEmployeeId() == null) {
				throw new EmployeeException("GENERAL.ITEM_NOT_NULL");
			}

			Integer employeeId = dto.getEmployee().getEmployeeId();

			Employee employee = employeeRepo.findById(employeeId)
					.orElseThrow(() -> new EmployeeException("EMPLOYEE.NOT_FOUND"));

			if (profileRepo.existsByEmployee_EmployeeIdAndProfileIdNot(employeeId, id)) {
				throw new EmployeeException("EMPLOYEE.PROFILE_ALREADY_EXISTS");
			}

			existingProfile.setEmployee(employee);
		}

		if (dto.getAddress() != null) {

			if (dto.getAddress().getAddressId() == null) {
				throw new EmployeeException("GENERAL.ITEM_NOT_NULL");
			}

			Integer addressId = dto.getAddress().getAddressId();

			Address address = addressRepo.findById(addressId)
					.orElseThrow(() -> new EmployeeException("ADDRESS.NOT_FOUND"));

			if (profileRepo.existsByAddress_AddressIdAndProfileIdNot(addressId, id)) {
				throw new EmployeeException("ADDRESS.ALREADY_ASSIGNED");
			}

			existingProfile.setAddress(address);
		}

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
		// TODO Auto-generated method stub
		Profile profile = profileRepo.findById(id).orElseThrow(() -> new EmployeeException("PROFILE.NOT_FOUND"));

		profile.setAddress(null);
		profile.setEmployee(null);
		profileRepo.deleteById(id);
	}

	@Override
	public ProfileDTO getProfileByEmployeeId(Integer employeeId) throws EmployeeException {
		// TODO Auto-generated method stub
		Profile profile = profileRepo.findByEmployee_EmployeeId(employeeId)
				.orElseThrow(() -> new EmployeeException("PROFILE.NOT_FOUND"));

		return ProfileUtil.toDTO(profile);

	}

}
