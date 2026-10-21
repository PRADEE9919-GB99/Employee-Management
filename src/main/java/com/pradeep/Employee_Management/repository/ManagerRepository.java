package com.pradeep.Employee_Management.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pradeep.Employee_Management.entity.Manager;

public interface ManagerRepository extends JpaRepository<Manager, Integer> {

	boolean existsByEmployee_EmployeeId(Integer employeeId);

	boolean existsByEmployee_EmployeeIdAndManagerIdNot(Integer employeeId, Integer managerId);
}
