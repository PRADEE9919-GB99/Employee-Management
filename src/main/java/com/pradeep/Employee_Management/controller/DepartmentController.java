package com.pradeep.Employee_Management.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	public ResponseEntity<Long> createDepartment( @RequestBody DepartmentDTO dto) throws EmployeeException {

		Long departmentId = departmentService.createDepartment(dto);

		return ResponseEntity.status(HttpStatus.CREATED).body(departmentId);
	}
	
}
