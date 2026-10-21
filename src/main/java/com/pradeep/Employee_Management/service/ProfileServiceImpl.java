package com.pradeep.Employee_Management.service;

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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ProfileDTO> getAllProfiles() throws EmployeeException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ProfileDTO updateProfile(Integer id, ProfileDTO dto) throws EmployeeException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteProfile(Integer id) throws EmployeeException {
		// TODO Auto-generated method stub

	}

	@Override
	public ProfileDTO getProfileByEmployeeId(Integer employeeId) throws EmployeeException {
		// TODO Auto-generated method stub
		return null;
	}

}
