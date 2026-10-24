package com.pradeep.Employee_Management.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pradeep.Employee_Management.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
//	List<Employee> findByDepartment(Long departmentId);

	List<Employee> findByManager(Integer managerId);

	boolean existsByEmail(String email);

	boolean existsByEmailAndEmployeeIdNot(String email, Integer employeeId);

//	List<Employee> findByDepartment_DepartmentId(Long departmentId);

	List<Employee> findByManager_ManagerId(Integer managerId);

}
