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

import com.pradeep.Employee_Management.dto.DepartmentDTO;
import com.pradeep.Employee_Management.exception.EmployeeException;
import com.pradeep.Employee_Management.service.DepartmentService;

@RestController
@RequestMapping("/department")
public class DepartmentController {

	private DepartmentService departmentService;
	private Environment environment;

	public DepartmentController(DepartmentService departmentService, Environment environment) {
		super();
		this.departmentService = departmentService;
		this.environment = environment;
	}

	@PostMapping
	public ResponseEntity<String> createDepartment(@RequestBody DepartmentDTO dto) throws EmployeeException {

		Long departmentId = departmentService.createDepartment(dto);
		String message = environment.getProperty("DEPARTMENT.API.SUCCESS");

		return ResponseEntity.status(HttpStatus.CREATED).body(message + departmentId);
	}

	@GetMapping("/{id}")
	public ResponseEntity<DepartmentDTO> getDepartmentById(@PathVariable Long id) throws EmployeeException {

		DepartmentDTO department = departmentService.getDepartmentById(id);

		return new ResponseEntity<>(department, HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<List<DepartmentDTO>> getAllDepartments() throws EmployeeException {

		List<DepartmentDTO> departments = departmentService.getAllDepartments();

		return new ResponseEntity<>(departments, HttpStatus.OK);
	}
}
