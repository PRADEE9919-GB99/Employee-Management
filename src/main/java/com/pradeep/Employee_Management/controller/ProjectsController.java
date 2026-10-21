package com.pradeep.Employee_Management.controller;

import java.util.List;

import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pradeep.Employee_Management.dto.ProjectsDTO;
import com.pradeep.Employee_Management.exception.EmployeeException;
import com.pradeep.Employee_Management.service.ProjectsService;

@RestController
@RequestMapping("/project")
public class ProjectsController {
	private Environment environment;
	private ProjectsService projectService;

	public ProjectsController(Environment environment, ProjectsService projectService) {
		super();
		this.environment = environment;
		this.projectService = projectService;
	}

	@PostMapping
	public ResponseEntity<String> createProject(@RequestBody ProjectsDTO dto) throws EmployeeException {

		Integer projectId = projectService.createProject(dto);
		String message = environment.getProperty("PROJECTS.API.SUCCESS");
		return new ResponseEntity<>(message + projectId, HttpStatus.CREATED);
	}

	@GetMapping("/{id}")
	public ResponseEntity<ProjectsDTO> getProjectById(@PathVariable Long id) throws EmployeeException {

		ProjectsDTO dto = projectService.getProjectById(id);

		return ResponseEntity.ok(dto);
	}

	@GetMapping
	public ResponseEntity<List<ProjectsDTO>> getAllProjects() throws EmployeeException {

		List<ProjectsDTO> projects = projectService.getAllProjects();

		return ResponseEntity.ok(projects);
	}
}
