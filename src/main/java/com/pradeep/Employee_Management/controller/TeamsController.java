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

import com.pradeep.Employee_Management.dto.TeamsDTO;
import com.pradeep.Employee_Management.exception.EmployeeException;
import com.pradeep.Employee_Management.service.TeamsService;

@RestController
@RequestMapping("/teams")
public class TeamsController {

	private TeamsService teamsService;
	private Environment environment;

	public TeamsController(TeamsService teamsService, Environment environment) {
		super();
		this.teamsService = teamsService;
		this.environment = environment;
	}

	@PostMapping
	public ResponseEntity<String> createTeams(@RequestBody TeamsDTO dto) throws EmployeeException {

		Integer teamsId = teamsService.createTeams(dto);
		String message = environment.getProperty("TEAMS.API.SUCCESS");

		return ResponseEntity.status(HttpStatus.CREATED).body(message + teamsId);
	}

	@GetMapping("/{teamsId}")
	public ResponseEntity<TeamsDTO> getTeamsById(@PathVariable Integer teamsId) throws EmployeeException {

		TeamsDTO dto = teamsService.getTeamsById(teamsId);

		return ResponseEntity.ok(dto);
	}

	@GetMapping
	public ResponseEntity<List<TeamsDTO>> getAllTeams() throws EmployeeException {

		List<TeamsDTO> teams = teamsService.getAllTeams();

		return ResponseEntity.ok(teams);
	}

	@PutMapping("/{teamsId}")
	public ResponseEntity<String> updateTeams(@PathVariable Integer teamsId, @RequestBody TeamsDTO dto)
			throws EmployeeException {

		teamsService.updateTeams(teamsId, dto);

		return ResponseEntity.ok("Team updated successfully with ID: " + teamsId);
	}

	@DeleteMapping("/{teamsId}")
	public ResponseEntity<String> deleteTeams(@PathVariable Integer teamsId) throws EmployeeException {

		teamsService.deleteTeams(teamsId);

		return ResponseEntity.ok("Team deleted successfully with ID: " + teamsId);
	}

	@PutMapping("/{teamsId}/manager/{managerId}")
	public ResponseEntity<String> assignManager(@PathVariable Integer teamsId, @PathVariable Integer managerId)
			throws EmployeeException {

		teamsService.asssignManager(teamsId, managerId);
		String message = environment.getProperty("TEAMS.API.ASSIGN_MANAGER_SUCCESS");
		return ResponseEntity.ok(message);
	}

	@PutMapping("/{teamsId}/department/{departmentId}")
	public ResponseEntity<String> assignDepartment(@PathVariable Integer teamsId, @PathVariable Long departmentId)
			throws EmployeeException {

		teamsService.asssignDepartment(teamsId, departmentId);
		String message = environment.getProperty("TEAMS.API.ASSIGN_DEPARTMENT_SUCCESS");
		return ResponseEntity.ok(message);
	}

	@PutMapping("/{teamId}/employees/{employeeId}")
	public ResponseEntity<String> assignEmployeeToTeam(@PathVariable Integer teamId, @PathVariable Integer employeeId)
			throws EmployeeException {

		teamsService.assignEmployeeToTeam(teamId, employeeId);

		return ResponseEntity.ok("TEAMS.API.ASSIGN_EMPLOYEE_SUCCESS");
	}
}
