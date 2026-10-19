package com.pradeep.Employee_Management.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pradeep.Employee_Management.entity.Profile;

public interface ProfileRepository extends JpaRepository<Profile, Integer> {
	Optional<Profile> findByEmployee(Integer employeeId);
}
