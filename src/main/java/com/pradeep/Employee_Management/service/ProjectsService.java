package com.pradeep.Employee_Management.service;

import java.util.List;

import com.pradeep.Employee_Management.dto.ProjectsDTO;

public interface ProjectsService {
	ProjectsDTO createProject(ProjectsDTO dto);

	ProjectsDTO getProjectById(Long id);

	List<ProjectsDTO> getAllProjects();

	ProjectsDTO updateProject(Long id, ProjectsDTO dto);

	void deleteProject(Long id);
	
	List<ProjectsDTO> getProjectsByEmployee(Integer employeeId);

	List<ProjectsDTO> getProjectsByManager(Integer managerId);
}
