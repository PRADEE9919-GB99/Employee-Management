package com.pradeep.Employee_Management.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pradeep.Employee_Management.entity.Manager;

public interface ManagerRepository extends JpaRepository<Manager, Integer> {

//	boolean existsByEmployeeId(Integer employeeId);

//	boolean existsByEmployeeIdAndManagerIdNot(Integer employeeId, Integer managerId);

}
