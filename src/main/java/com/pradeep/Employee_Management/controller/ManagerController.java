package com.pradeep.Employee_Management.controller;

import java.util.List;

import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

@RestController
@RequestMapping("/manager")
public class ManagerController {
	private Environment environment;
	private ManagerService managerService;

	public ManagerController(Environment environment, ManagerService managerService) {
		super();
		this.environment = environment;
		this.managerService = managerService;
	}

	@PostMapping
	public ResponseEntity<String> createManager(@RequestBody ManagerDTO dto) throws EmployeeException {

		Integer managerId = managerService.createManager(dto);
		String message = environment.getProperty("MANAGER.API.SUCCESS");

		return ResponseEntity.status(HttpStatus.CREATED).body(message + managerId);
	}

	@GetMapping("/{id}")
	public ResponseEntity<ManagerDTO> getManagerById(@PathVariable Integer id) throws EmployeeException {

		ManagerDTO employee = managerService.getManagerById(id);

		return new ResponseEntity<ManagerDTO>(employee, HttpStatus.OK);
	}

	@GetMapping()
	public ResponseEntity<List<ManagerDTO>> getAllManager() throws EmployeeException {

		List<ManagerDTO> employee = managerService.getAllManagers();

		return new ResponseEntity<List<ManagerDTO>>(employee, HttpStatus.OK);
	}

	@PutMapping("/{managerId}")
	public ResponseEntity<String> updateManager(@PathVariable Integer managerId, @RequestBody ManagerDTO dto)
			throws EmployeeException {

		managerService.updateManager(managerId, dto);
		String message = environment.getProperty("MANAGER.API.UPDATE_SUCCESS");

		return ResponseEntity.status(HttpStatus.OK).body(message);
	}

	@DeleteMapping("/{managerId}")
	public ResponseEntity<String> deleteManager(@PathVariable Integer managerId) throws EmployeeException {

		managerService.deleteManager(managerId);
		String message = environment.getProperty("MANAGER.API.DELETE_SUCCESS");

		return ResponseEntity.status(HttpStatus.OK).body(message);
	}

	@PutMapping("/{id}/employee/{employeeId}")
	public ResponseEntity<String> assignEmployee(@PathVariable Integer id, @PathVariable Integer employeeId)
			throws EmployeeException {

		managerService.assignEmployee(id, employeeId);
		String message = environment.getProperty("MANAGER.API.ASSIGN_EMPLOYEE_SUCCESS");
		return ResponseEntity.ok(message);
	}

	@PutMapping("/{id}/projects/{projectId}")
	public ResponseEntity<String> assignProjects(@PathVariable Integer id, @PathVariable Integer projectId)
			throws EmployeeException {

		managerService.assignProjects(id, projectId);
		String message = environment.getProperty("MANAGER.API.ASSIGN_PROJECTS_SUCCESS");
		return ResponseEntity.ok(message);
	}

}
