package com.pradeep.Employee_Management.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pradeep.Employee_Management.dto.EmployeeDTO;
import com.pradeep.Employee_Management.exception.EmployeeException;
import com.pradeep.Employee_Management.service.EmployeeService;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

	private EmployeeService employeeService;
	private Environment environment;

	public EmployeeController(EmployeeService employeeService, Environment environment) {
		super();
		this.employeeService = employeeService;
		this.environment = environment;
	}

	@PostMapping
	public ResponseEntity<String> createEmployee(@RequestBody EmployeeDTO dto) throws EmployeeException {

		Integer employeeId = employeeService.createEmployee(dto);
		String message = environment.getProperty("EMPLOYEE.API.SUCCESS");

		return ResponseEntity.status(HttpStatus.CREATED).body(message + employeeId);
	}

}
