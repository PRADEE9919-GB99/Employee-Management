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

import com.pradeep.Employee_Management.dto.EmployeeDTO;
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
}
