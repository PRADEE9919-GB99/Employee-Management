package com.pradeep.Employee_Management.service;

import java.util.List;

import com.pradeep.Employee_Management.dto.ProjectsDTO;
import com.pradeep.Employee_Management.exception.EmployeeException;

public interface ProjectsService {
	Integer createProject(ProjectsDTO dto) throws EmployeeException;

	ProjectsDTO getProjectById(Long id) throws EmployeeException;

	List<ProjectsDTO> getAllProjects() throws EmployeeException;

	ProjectsDTO updateProject(Long id, ProjectsDTO dto) throws EmployeeException;

	void deleteProject(Long id) throws EmployeeException;

	List<ProjectsDTO> getProjectsByEmployee(Integer employeeId) throws EmployeeException;

	List<ProjectsDTO> getProjectsByManager(Integer managerId) throws EmployeeException;
}
