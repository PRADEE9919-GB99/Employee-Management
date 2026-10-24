
package com.pradeep.Employee_Management.controller;

import java.util.List;

import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pradeep.Employee_Management.dto.ProjectsDTO;
import com.pradeep.Employee_Management.exception.EmployeeException;
import com.pradeep.Employee_Management.service.ProjectsService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;

@RestController
@RequestMapping("/project")
@Validated
public class ProjectsController {

	private Environment environment;
	private ProjectsService projectService;

	public ProjectsController(Environment environment, ProjectsService projectService) {
		super();
		this.environment = environment;
		this.projectService = projectService;
	}

	@PostMapping
	public ResponseEntity<String> createProject(@Valid @RequestBody ProjectsDTO dto) throws EmployeeException {

		Integer projectId = projectService.createProject(dto);
		String message = environment.getProperty("PROJECTS.API.SUCCESS");

		return new ResponseEntity<>(message + projectId, HttpStatus.CREATED);
	}

	@GetMapping("/{projectid}")
	public ResponseEntity<ProjectsDTO> getProjectById(
			@PathVariable @Positive(message = "{projects.id.positive}") Long projectid) throws EmployeeException {

		ProjectsDTO dto = projectService.getProjectById(projectid);

		return ResponseEntity.ok(dto);
	}

	@GetMapping
	public ResponseEntity<List<ProjectsDTO>> getAllProjects() throws EmployeeException {

		List<ProjectsDTO> projects = projectService.getAllProjects();

		return ResponseEntity.ok(projects);
	}

	@PutMapping("/{projectid}")
	public ResponseEntity<String> updateProject(
			@PathVariable @Positive(message = "{projects.id.positive}") Long projectid,
			@Valid @RequestBody ProjectsDTO dto) throws EmployeeException {

		projectService.updateProject(projectid, dto);

		String message = environment.getProperty("PROJECTS.API.UPDATE_SUCCESS");

		return ResponseEntity.ok(message);
	}

	@DeleteMapping("/{projectid}")
	public ResponseEntity<String> deleteProject(
			@PathVariable @Positive(message = "{projects.id.positive}") Long projectid) throws EmployeeException {

		projectService.deleteProject(projectid);

		String message = environment.getProperty("PROJECTS.API.DELETE_SUCCESS");

		return ResponseEntity.ok(message);
	}

	@GetMapping("/employee/{employeeId}")
	public ResponseEntity<List<ProjectsDTO>> getProjectsByEmployee(
			@PathVariable @Positive(message = "{employee.id.positive}") Integer employeeId) throws EmployeeException {

		List<ProjectsDTO> projects = projectService.getProjectsByEmployee(employeeId);

		return ResponseEntity.ok(projects);
	}

	@GetMapping("/manager/{managerId}")
	public ResponseEntity<List<ProjectsDTO>> getProjectsByManager(
			@PathVariable @Positive(message = "{manager.id.positive}") Integer managerId) throws EmployeeException {

		List<ProjectsDTO> projects = projectService.getProjectsByManager(managerId);

		return ResponseEntity.ok(projects);
	}

	@PutMapping("/{id}/employee/{employeeId}")
	public ResponseEntity<String> assignProject(@PathVariable @Positive(message = "{projects.id.positive}") Long id,
			@PathVariable @Positive(message = "{employee.id.positive}") Integer employeeId) throws EmployeeException {

		projectService.assignProject(id, employeeId);

		String message = environment.getProperty("PROJECTS.API.EMPLOYEE_ASSIGN_SUCCESS");

		return ResponseEntity.ok(message);
	}

	@PutMapping("/{id}/manager/{managerId}")
	public ResponseEntity<String> assignManager(@PathVariable @Positive(message = "{projects.id.positive}") Long id,
			@PathVariable @Positive(message = "{manager.id.positive}") Integer managerId) throws EmployeeException {

		projectService.assignManager(id, managerId);

		String message = environment.getProperty("PROJECTS.API.MANAGER_ASSIGN_SUCCESS");

		return ResponseEntity.ok(message);
	}
}
