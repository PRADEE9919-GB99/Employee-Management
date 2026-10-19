package com.pradeep.Employee_Management.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pradeep.Employee_Management.entity.Projects;

public interface ProjectsRepository extends JpaRepository<Projects, Long> {
	List<Projects> findByEmployee(Integer employeeId);
	List<Projects> findByManager(Integer managerId);
}
