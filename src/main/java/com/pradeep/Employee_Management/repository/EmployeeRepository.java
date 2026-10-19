package com.pradeep.Employee_Management.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pradeep.Employee_Management.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer>  {
	List<Employee> findByDepartment(Long departmentId);
	List<Employee> findByManager(Integer managerId);
}
