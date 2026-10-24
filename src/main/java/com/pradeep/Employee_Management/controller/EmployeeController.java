
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

import com.pradeep.Employee_Management.dto.EmployeeDTO;
import com.pradeep.Employee_Management.exception.EmployeeException;
import com.pradeep.Employee_Management.service.EmployeeService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;

@RestController
@RequestMapping("/employee")
@Validated
public class EmployeeController {

	private final EmployeeService employeeService;
	private final Environment environment;

	public EmployeeController(EmployeeService employeeService, Environment environment) {
		this.employeeService = employeeService;
		this.environment = environment;
	}

	@PostMapping
	public ResponseEntity<String> createEmployee(@Valid @RequestBody EmployeeDTO dto) throws EmployeeException {

		Integer employeeId = employeeService.createEmployee(dto);
		String message = environment.getProperty("EMPLOYEE.API.SUCCESS");

		return ResponseEntity.status(HttpStatus.CREATED).body(message + employeeId);
	}

	@GetMapping("/{id}")
	public ResponseEntity<EmployeeDTO> getEmployeeById(
			@PathVariable @Positive(message = "{employee.id.positive}") Integer id) throws EmployeeException {

		EmployeeDTO employee = employeeService.getEmployeeById(id);
		return ResponseEntity.ok(employee);
	}

	@GetMapping
	public ResponseEntity<List<EmployeeDTO>> getAllEmployees() throws EmployeeException {

		List<EmployeeDTO> employees = employeeService.getAllEmployees();
		return ResponseEntity.ok(employees);
	}

	@PutMapping("/{employeeId}")
	public ResponseEntity<String> updateEmployee(
			@PathVariable @Positive(message = "{employee.id.positive}") Integer employeeId,
			@Valid @RequestBody EmployeeDTO dto) throws EmployeeException {

		employeeService.updateEmployee(employeeId, dto);
		String message = environment.getProperty("EMPLOYEE.API.UPDATE_SUCCESS");

		return ResponseEntity.ok(message);
	}

	@DeleteMapping("/{employeeId}")
	public ResponseEntity<String> deleteEmployee(
			@PathVariable @Positive(message = "{employee.id.positive}") Integer employeeId) throws EmployeeException {

		employeeService.deleteEmployee(employeeId);
		String message = environment.getProperty("EMPLOYEE.API.DELETE_SUCCESS");

		return ResponseEntity.ok(message);
	}

//	@GetMapping("/department/{departmentId}")
//	public ResponseEntity<List<EmployeeDTO>> getEmployeesByDepartment(
//			@PathVariable @Positive(message = "{department.id.positive}") Long departmentId) throws EmployeeException {
//
//		List<EmployeeDTO> employees = employeeService.getEmployeesByDepartment(departmentId);
//
//		return ResponseEntity.ok(employees);
//	}

//	@GetMapping("/manager/{managerId}")
//	public ResponseEntity<List<EmployeeDTO>> getEmployeesByManager(
//			@PathVariable @Positive(message = "{manager.id.positive}") Long managerId) throws EmployeeException {
//
//		List<EmployeeDTO> employees = employeeService.getEmployeesByManager(managerId);
//
//		return ResponseEntity.ok(employees);
//	}

	@PutMapping("/teams/{employeeId}/{teamId}")
	public ResponseEntity<String> assignTeam(
			@PathVariable @Positive(message = "{employee.id.positive}") Integer employeeId,
			@PathVariable @Positive(message = "{teams.id.positive}") Integer teamId) throws EmployeeException {

		employeeService.assignTeam(employeeId, teamId);
		String message = environment.getProperty("EMPLOYEE.API.ASSIGN_TEAM");

		return ResponseEntity.ok(message);
	}

	@PutMapping("/{employeeId}/manager/{managerId}")
	public ResponseEntity<String> assignManager(
			@PathVariable @Positive(message = "{employee.id.positive}") Integer employeeId,
			@PathVariable @Positive(message = "{manager.id.positive}") Integer managerId) throws EmployeeException {

		employeeService.assignReportingManager(employeeId, managerId);
		String message = environment.getProperty("EMPLOYEE.API.ASSIGN_MANAGER");

		return ResponseEntity.ok(message);
	}

	@PutMapping("/{employeeId}/profile/{profileId}")
	public ResponseEntity<String> assignProfile(
			@PathVariable @Positive(message = "{employee.id.positive}") Integer employeeId,
			@PathVariable @Positive(message = "{profile.id.positive}") Integer profileId) throws EmployeeException {

		employeeService.assignProfile(employeeId, profileId);
		String message = environment.getProperty("EMPLOYEE.API.ASSIGN_PROFILE_SUCCESS");

		return ResponseEntity.ok(message);
	}

	@PutMapping("/{employeeId}/address/{addressId}")
	public ResponseEntity<String> assignAddress(
			@PathVariable @Positive(message = "{employee.id.positive}") Integer employeeId,
			@PathVariable @Positive(message = "{address.id.positive}") Integer addressId) throws EmployeeException {

		employeeService.assignAddress(employeeId, addressId);
		String message = environment.getProperty("EMPLOYEE.API.ASSIGN_ADDRESS_SUCCESS");

		return ResponseEntity.ok(message);
	}

	@PutMapping("/{employeeId}/projects/{projectId}")
	public ResponseEntity<String> assignProjects(
			@PathVariable @Positive(message = "{employee.id.positive}") Integer employeeId,
			@PathVariable @Positive(message = "{projects.id.positive}") Long projectId) throws EmployeeException {

		employeeService.assignProjects(employeeId, projectId);
		String message = environment.getProperty("EMPLOYEE.API.ASSIGN_PROJECTS_SUCCESS");

		return ResponseEntity.ok(message);
	}

	@DeleteMapping("/{employeeId}/team")
	public ResponseEntity<String> removeEmployeeFromTeam(
			@PathVariable @Positive(message = "{employee.id.positive}") Integer employeeId) throws EmployeeException {

		employeeService.removeEmployeeFromTeam(employeeId);
		return ResponseEntity.ok(environment.getProperty("EMPLOYEE.API.UNASSIGN_SUCCESSS"));
	}
}
