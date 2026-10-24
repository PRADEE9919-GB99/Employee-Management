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

	@PutMapping("/{id}")
	public ResponseEntity<String> updateDepartment(@PathVariable Long id, @RequestBody DepartmentDTO dto)
			throws EmployeeException {

		departmentService.updateDepartment(id, dto);
		String message = environment.getProperty("DEPARTMENT.API.UPDATE_SUCCESS");
		return new ResponseEntity<>(message, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteDepartment(@PathVariable Long id) throws EmployeeException {

		departmentService.deleteDepartment(id);

		String message = environment.getProperty("DEPARTMENT.API.DELETE_SUCCESS");
		return new ResponseEntity<>(message, HttpStatus.OK);
	}

//	@PutMapping("/{id}/employee/{employeeId}")
//	public ResponseEntity<String> assignEmployee(@PathVariable Long id, @PathVariable Integer employeeId)
//			throws EmployeeException {
//
//		departmentService.assignEmployee(id, employeeId);
//		String message = environment.getProperty("DEPARTMENT.API.ASSIGNE_EMPLOYEE_SUCCESS");
//		return ResponseEntity.ok(message);
//	}

	@PutMapping("/{id}/teams/{teamsId}")
	public ResponseEntity<String> assignTeamToDepartment(@PathVariable Long id, @PathVariable Integer teamsId)
			throws EmployeeException {

		departmentService.assignTeamToDepartment(id, teamsId);
		String message = environment.getProperty("DEPARTMENT.API.ASSIGN_TEAMS_SUCCESS");
		return ResponseEntity.ok(message);
	}
}
