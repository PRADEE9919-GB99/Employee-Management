package com.pradeep.Employee_Management.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pradeep.Employee_Management.entity.Profile;

public interface ProfileRepository extends JpaRepository<Profile, Integer> {
//	Optional<Profile> findByEmployee(Integer employeeId);

//	boolean existsByEmployee_EmployeeId(Integer employeeId);

//	boolean existsByAddress_AddressId(Integer addressId);

//	boolean existsByEmployee_EmployeeIdAndProfileIdNot(Integer employeeId, Integer profileId);

//	boolean existsByAddress_AddressIdAndProfileIdNot(Integer addressId, Integer profileId);

//	Optional<Profile> findByEmployee_EmployeeId(Integer employeeId);
}
