
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

import com.pradeep.Employee_Management.dto.ManagerDTO;
import com.pradeep.Employee_Management.exception.EmployeeException;
import com.pradeep.Employee_Management.service.ManagerService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;

@RestController
@RequestMapping("/manager")
@Validated
public class ManagerController {

	private final Environment environment;
	private final ManagerService managerService;

	public ManagerController(Environment environment, ManagerService managerService) {
		this.environment = environment;
		this.managerService = managerService;
	}

	@PostMapping
	public ResponseEntity<String> createManager(@Valid @RequestBody ManagerDTO dto) throws EmployeeException {

		Integer managerId = managerService.createManager(dto);
		String message = environment.getProperty("MANAGER.API.SUCCESS");

		return ResponseEntity.status(HttpStatus.CREATED).body(message + managerId);
	}

	@GetMapping("/{id}")
	public ResponseEntity<ManagerDTO> getManagerById(
			@PathVariable @Positive(message = "{manager.id.positive}") Integer id) throws EmployeeException {

		ManagerDTO manager = managerService.getManagerById(id);
		return ResponseEntity.ok(manager);
	}

	@GetMapping
	public ResponseEntity<List<ManagerDTO>> getAllManager() throws EmployeeException {

		List<ManagerDTO> managers = managerService.getAllManagers();
		return ResponseEntity.ok(managers);
	}

	@PutMapping("/{managerId}")
	public ResponseEntity<String> updateManager(
			@PathVariable @Positive(message = "{manager.id.positive}") Integer managerId,
			@Valid @RequestBody ManagerDTO dto) throws EmployeeException {

		managerService.updateManager(managerId, dto);
		String message = environment.getProperty("MANAGER.API.UPDATE_SUCCESS");

		return ResponseEntity.ok(message);
	}

	@DeleteMapping("/{managerId}")
	public ResponseEntity<String> deleteManager(
			@PathVariable @Positive(message = "{manager.id.positive}") Integer managerId) throws EmployeeException {

		managerService.deleteManager(managerId);
		String message = environment.getProperty("MANAGER.API.DELETE_SUCCESS");

		return ResponseEntity.ok(message);
	}

	@PutMapping("/{id}/employee/{employeeId}")
	public ResponseEntity<String> assignEmployee(@PathVariable @Positive(message = "{manager.id.positive}") Integer id,
			@PathVariable @Positive(message = "{employee.id.positive}") Integer employeeId) throws EmployeeException {

		managerService.assignEmployee(id, employeeId);
		String message = environment.getProperty("MANAGER.API.ASSIGN_EMPLOYEE_SUCCESS");

		return ResponseEntity.ok(message);
	}

	@PutMapping("/{id}/projects/{projectId}")
	public ResponseEntity<String> assignProjects(@PathVariable @Positive(message = "{manager.id.positive}") Integer id,
			@PathVariable @Positive(message = "{projects.id.positive}") Integer projectId) throws EmployeeException {

		managerService.assignProjects(id, projectId);
		String message = environment.getProperty("MANAGER.API.ASSIGN_PROJECTS_SUCCESS");

		return ResponseEntity.ok(message);
	}
}
