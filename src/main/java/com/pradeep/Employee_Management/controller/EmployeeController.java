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

	@GetMapping("/{id}")
	public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable Integer id) throws EmployeeException {

		EmployeeDTO employee = employeeService.getEmployeeById(id);

		return new ResponseEntity<>(employee, HttpStatus.OK);
	}

	@GetMapping()
	public ResponseEntity<List<EmployeeDTO>> getAllEmployees() throws EmployeeException {

		List<EmployeeDTO> employees = employeeService.getAllEmployees();

		return new ResponseEntity<>(employees, HttpStatus.OK);
	}

	@PutMapping("/{employeeId}")
	public ResponseEntity<String> updateEmployee(@PathVariable Integer employeeId, @RequestBody EmployeeDTO dto)
			throws EmployeeException {

		employeeService.updateEmployee(employeeId, dto);
		String message = environment.getProperty("EMPLOYEE.API.UPDATE_SUCCESS");

		return ResponseEntity.status(HttpStatus.OK).body(message);
	}

	@DeleteMapping("/{employeeId}")
	public ResponseEntity<String> deleteEmployee(@PathVariable Integer employeeId) throws EmployeeException {

		employeeService.deleteEmployee(employeeId);
		String message = environment.getProperty("EMPLOYEE.API.DELETE_SUCCESS");

		return ResponseEntity.status(HttpStatus.OK).body(message);
	}

	@GetMapping("/department/{departmentId}")
	public ResponseEntity<List<EmployeeDTO>> getEmployeesByDepartment(@PathVariable Long departmentId)
			throws EmployeeException {

		List<EmployeeDTO> employees = employeeService.getAllEmployees();

		return new ResponseEntity<>(employees, HttpStatus.OK);
	}

	@GetMapping("/manager/{managerId}")
	public ResponseEntity<List<EmployeeDTO>> getEmployeesByManager(@PathVariable Long managerId)
			throws EmployeeException {

		List<EmployeeDTO> employees = employeeService.getAllEmployees();

		return new ResponseEntity<>(employees, HttpStatus.OK);
	}

	@PutMapping("/teams/{employeeId}/{teamId}")
	public ResponseEntity<String> assignTeam(@PathVariable Integer employeeId, @PathVariable Integer teamId)
			throws EmployeeException {

		employeeService.assignTeam(employeeId, teamId);
		String message = environment.getProperty("EMPLOYEE.API.ASSIGN_TEAM");

		return ResponseEntity.status(HttpStatus.OK).body(message);
	}
//
//	@PutMapping("/department/{employeeId}/{departmentId}")
//	public ResponseEntity<String> assignDepartment(@PathVariable Integer employeeId, @PathVariable Long departmentId)
//			throws EmployeeException {
//
//		employeeService.assignDepartment(employeeId, departmentId);
//		String message = environment.getProperty("EMPLOYEE.API.ASSIGN_DEPARTMENT");
//
//		return ResponseEntity.status(HttpStatus.OK).body(message);
//	}

	@PutMapping("/{employeeId}/manager/{managerId}")
	public ResponseEntity<String> assignManager(@PathVariable Integer employeeId, @PathVariable Integer managerId)
			throws EmployeeException {

		employeeService.assignReportingManager(employeeId, managerId);
		String message = environment.getProperty("EMPLOYEE.API.ASSIGN_MANAGER");

		return ResponseEntity.status(HttpStatus.OK).body(message);
	}

//	@PutMapping("/teams/{employeeId}")
//	public ResponseEntity<String> unAssignTeam(@PathVariable Integer employeeId) throws EmployeeException {
//
//		employeeService.unAssignTeam(employeeId);
//		String message = environment.getProperty("EMPLOYEE.API.UNASSIGN_SUCCESSS");
//
//		return ResponseEntity.status(HttpStatus.OK).body(message);
//	}

//	@PutMapping("/department/{employeeId}")
//	public ResponseEntity<String> unAassignDepartment(@PathVariable Integer employeeId) throws EmployeeException {
//
//		employeeService.unAssignTeam(employeeId);
//		String message = environment.getProperty("EMPLOYEE.API.UNASSIGN_SUCCESSS_DEPT");
//
//		return ResponseEntity.status(HttpStatus.OK).body(message);
//	}
//
//	@PutMapping("/manager/{employeeId}")
//	public ResponseEntity<String> unAassignManager(@PathVariable Integer employeeId) throws EmployeeException {
//
//		employeeService.unAassignManager(employeeId);
//		String message = environment.getProperty("EMPLOYEE.API.UNASSIGN_SUCCESSS_MNGR");
//
//		return ResponseEntity.status(HttpStatus.OK).body(message);
//	}

	@PutMapping("/{employeeId}/profile/{profileId}")
	public ResponseEntity<String> assignProfile(@PathVariable Integer employeeId, @PathVariable Integer profileId)
			throws EmployeeException {

		employeeService.assignProfile(employeeId, profileId);
		String message = environment.getProperty("EMPLOYEE.API.ASSIGN_PROFILE_SUCCESS");
		return ResponseEntity.ok(message);
	}

	@PutMapping("/{employeeId}/address/{addressId}")
	public ResponseEntity<String> assignAddress(@PathVariable Integer employeeId, @PathVariable Integer addressId)
			throws EmployeeException {

		employeeService.assignAddress(employeeId, addressId);
		String message = environment.getProperty("EMPLOYEE.API.ASSIGN_ADDRESS_SUCCESS");
		return ResponseEntity.ok(message);
	}

	@PutMapping("/{employeeId}/projects/{projectId}")
	public ResponseEntity<String> assignProjects(@PathVariable Integer employeeId, @PathVariable Long projectId)
			throws EmployeeException {

		employeeService.assignProjects(employeeId, projectId);
		String message = environment.getProperty("EMPLOYEE.API.ASSIGN_PROJECTS_SUCCESS");
		return ResponseEntity.ok(message);
	}

	@DeleteMapping("/{employeeId}/team")
	public ResponseEntity<String> removeEmployeeFromTeam(@PathVariable Integer employeeId) throws EmployeeException {

		employeeService.removeEmployeeFromTeam(employeeId);

		return ResponseEntity.ok("EMPLOYEE.API.UNASSIGN_SUCCESSS");
	}
}
