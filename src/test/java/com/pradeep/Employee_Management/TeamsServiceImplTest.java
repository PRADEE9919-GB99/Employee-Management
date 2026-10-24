package com.pradeep.Employee_Management;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.pradeep.Employee_Management.dto.DepartmentDTO;
import com.pradeep.Employee_Management.dto.ManagerDTO;
import com.pradeep.Employee_Management.dto.TeamsDTO;
import com.pradeep.Employee_Management.entity.Department;
import com.pradeep.Employee_Management.entity.Employee;
import com.pradeep.Employee_Management.entity.Manager;
import com.pradeep.Employee_Management.entity.Teams;
import com.pradeep.Employee_Management.exception.EmployeeException;
import com.pradeep.Employee_Management.repository.DepartmentRepository;
import com.pradeep.Employee_Management.repository.EmployeeRepository;
import com.pradeep.Employee_Management.repository.ManagerRepository;
import com.pradeep.Employee_Management.repository.TeamsRepository;
import com.pradeep.Employee_Management.service.TeamsServiceImpl;

@ExtendWith(MockitoExtension.class)
class TeamsServiceImplTest {

	@Mock
	private TeamsRepository teamsRepo;

	@Mock
	private ManagerRepository managerRepo;

	@Mock
	private DepartmentRepository departmentRepo;

	@Mock
	private EmployeeRepository employeeRepo;

	// TeamsUtil is not used by the service constructor or methods.
	// Its constructor argument is not needed for these tests.

	@InjectMocks
	private TeamsServiceImpl teamsService;

	private Teams team;
	private Manager manager;
	private Department department;
	private Employee employee;
	private TeamsDTO teamDTO;

	@BeforeEach
	void setUp() {
		manager = new Manager();
		manager.setManagerId(10);

		department = new Department();
		department.setDepartmentId(20L);

		employee = new Employee();
		employee.setEmployeeId(30);

		team = new Teams();
		team.setTeamsId(1);
		team.setTeamName("QA Team");
		team.setManager(manager);
		team.setDepartment(department);

		teamDTO = new TeamsDTO();
		teamDTO.setTeamName("QA Team");
//		teamDTO.setManagerResponseDTO(managerDTO(10));
//		teamDTO.setDepartment(departmentDTO(20L));
	}

	// CREATE TEAM

	@Test
	void createTeams_Success() throws EmployeeException {
		when(managerRepo.findById(10)).thenReturn(Optional.of(manager));
		when(departmentRepo.findById(20L)).thenReturn(Optional.of(department));
		when(teamsRepo.save(any(Teams.class))).thenAnswer(invocation -> {
			Teams saved = invocation.getArgument(0);
			saved.setTeamsId(1);
			return saved;
		});

		Integer result = teamsService.createTeams(teamDTO);

		assertEquals(1, result);
		verify(managerRepo).findById(10);
		verify(departmentRepo).findById(20L);
		verify(teamsRepo).save(any(Teams.class));
	}

	@Test
	void createTeams_ManagerMissingInDTO_ThrowsException() {
		teamDTO.setManagerResponseDTO(null);

		assertThrows(EmployeeException.class, () -> teamsService.createTeams(teamDTO));

		verify(teamsRepo, never()).save(any(Teams.class));
	}

	@Test
	void createTeams_ManagerIdMissingInDTO_ThrowsException() {
//		teamDTO.setManagerResponseDTO(managerDTO(null));

		assertThrows(EmployeeException.class, () -> teamsService.createTeams(teamDTO));
	}

	@Test
	void createTeams_ManagerNotFound_ThrowsException() {
		when(managerRepo.findById(10)).thenReturn(Optional.empty());

		assertThrows(EmployeeException.class, () -> teamsService.createTeams(teamDTO));

		verify(teamsRepo, never()).save(any(Teams.class));
	}

	@Test
	void createTeams_DepartmentMissingInDTO_ThrowsException() {
		teamDTO.setDepartment(null);

		assertThrows(EmployeeException.class, () -> teamsService.createTeams(teamDTO));
	}

	@Test
	void createTeams_DepartmentIdMissingInDTO_ThrowsException() {
//		teamDTO.setDepartment(departmentDTO(null));

		assertThrows(EmployeeException.class, () -> teamsService.createTeams(teamDTO));
	}

	@Test
	void createTeams_DepartmentNotFound_ThrowsException() {
		when(managerRepo.findById(10)).thenReturn(Optional.of(manager));
		when(departmentRepo.findById(20L)).thenReturn(Optional.empty());

		assertThrows(EmployeeException.class, () -> teamsService.createTeams(teamDTO));

		verify(teamsRepo, never()).save(any(Teams.class));
	}

	// GET TEAM BY ID

	@Test
	void getTeamsById_Success() throws EmployeeException {
		when(teamsRepo.findById(1)).thenReturn(Optional.of(team));

		TeamsDTO result = teamsService.getTeamsById(1);

		assertNotNull(result);
		assertEquals("QA Team", result.getTeamName());
		verify(teamsRepo).findById(1);
	}

	@Test
	void getTeamsById_NotFound_ThrowsException() {
		when(teamsRepo.findById(99)).thenReturn(Optional.empty());

		assertThrows(EmployeeException.class, () -> teamsService.getTeamsById(99));
	}

	// GET ALL TEAMS

	@Test
	void getAllTeams_Success() throws EmployeeException {
		Teams secondTeam = new Teams();
		secondTeam.setTeamsId(2);
		secondTeam.setTeamName("Development Team");

		when(teamsRepo.findAll()).thenReturn(Arrays.asList(team, secondTeam));

		var result = teamsService.getAllTeams();

		assertEquals(2, result.size());
		assertEquals("QA Team", result.get(0).getTeamName());
		assertEquals("Development Team", result.get(1).getTeamName());
	}

	@Test
	void getAllTeams_EmptyList() throws EmployeeException {
		when(teamsRepo.findAll()).thenReturn(Collections.emptyList());

		var result = teamsService.getAllTeams();

		assertNotNull(result);
		assertTrue(result.isEmpty());
	}

	// UPDATE TEAM

	@Test
	void updateTeams_Success() throws EmployeeException {
		TeamsDTO updateDTO = new TeamsDTO();
		updateDTO.setTeamName("Automation Team");

		when(teamsRepo.findById(1)).thenReturn(Optional.of(team));
		when(teamsRepo.save(any(Teams.class))).thenAnswer(invocation -> invocation.getArgument(0));

		teamsService.updateTeams(1, updateDTO);

		assertEquals("Automation Team", team.getTeamName());
		verify(teamsRepo).save(team);
	}

	@Test
	void updateTeams_UpdateDepartment_Success() throws EmployeeException {
		Department newDepartment = new Department();
		newDepartment.setDepartmentId(21L);

		TeamsDTO updateDTO = new TeamsDTO();
		updateDTO.setTeamName("QA Team");
//		updateDTO.setDepartment(departmentDTO(21L));

		when(teamsRepo.findById(1)).thenReturn(Optional.of(team));
		when(departmentRepo.findById(21L)).thenReturn(Optional.of(newDepartment));
		when(teamsRepo.save(any(Teams.class))).thenAnswer(invocation -> invocation.getArgument(0));

		teamsService.updateTeams(1, updateDTO);

		assertSame(newDepartment, team.getDepartment());
		verify(teamsRepo).save(team);
	}

	@Test
	void updateTeams_DepartmentNotFound_ThrowsException() {
		TeamsDTO updateDTO = new TeamsDTO();
//		updateDTO.setDepartment(departmentDTO(99L));

		when(teamsRepo.findById(1)).thenReturn(Optional.of(team));
		when(departmentRepo.findById(99L)).thenReturn(Optional.empty());

		assertThrows(EmployeeException.class, () -> teamsService.updateTeams(1, updateDTO));

		verify(teamsRepo, never()).save(any(Teams.class));
	}

	@Test
	void updateTeams_UpdateManager_Success() throws EmployeeException {
		Manager newManager = new Manager();
		newManager.setManagerId(11);

		TeamsDTO updateDTO = new TeamsDTO();
		updateDTO.setTeamName("QA Team");
//		updateDTO.setManagerResponseDTO(managerDTO(11));

		when(teamsRepo.findById(1)).thenReturn(Optional.of(team));
		when(managerRepo.findById(11)).thenReturn(Optional.of(newManager));
		when(teamsRepo.save(any(Teams.class))).thenAnswer(invocation -> invocation.getArgument(0));

		teamsService.updateTeams(1, updateDTO);

		assertSame(newManager, team.getManager());
		verify(teamsRepo).save(team);
	}

	@Test
	void updateTeams_ManagerNotFound_ThrowsException() {
		TeamsDTO updateDTO = new TeamsDTO();
//		updateDTO.setManagerResponseDTO(managerDTO(99));

		when(teamsRepo.findById(1)).thenReturn(Optional.of(team));
		when(managerRepo.findById(99)).thenReturn(Optional.empty());

		assertThrows(EmployeeException.class, () -> teamsService.updateTeams(1, updateDTO));

		verify(teamsRepo, never()).save(any(Teams.class));
	}

	@Test
	void updateTeams_TeamNotFound_ThrowsException() {
		when(teamsRepo.findById(99)).thenReturn(Optional.empty());

		assertThrows(EmployeeException.class, () -> teamsService.updateTeams(99, teamDTO));

		verify(teamsRepo, never()).save(any(Teams.class));
	}

	// DELETE TEAM

	@Test
	void deleteTeams_Success() throws EmployeeException {
		when(teamsRepo.findById(1)).thenReturn(Optional.of(team));

		teamsService.deleteTeams(1);

		verify(teamsRepo).delete(team);
	}

	@Test
	void deleteTeams_NotFound_ThrowsException() {
		when(teamsRepo.findById(99)).thenReturn(Optional.empty());

		assertThrows(EmployeeException.class, () -> teamsService.deleteTeams(99));

		verify(teamsRepo, never()).delete(any(Teams.class));
	}

	// ASSIGN MANAGER

	@Test
	void assignManager_Success() throws EmployeeException {
		Manager newManager = new Manager();
		newManager.setManagerId(11);

		when(teamsRepo.findById(1)).thenReturn(Optional.of(team));
		when(managerRepo.findById(11)).thenReturn(Optional.of(newManager));
		when(teamsRepo.save(any(Teams.class))).thenAnswer(invocation -> invocation.getArgument(0));

		teamsService.asssignManager(1, 11);

		assertSame(newManager, team.getManager());
		verify(teamsRepo).save(team);
	}

	@Test
	void assignManager_TeamNotFound_ThrowsException() {
		when(teamsRepo.findById(99)).thenReturn(Optional.empty());

		assertThrows(EmployeeException.class, () -> teamsService.asssignManager(99, 11));

		verify(managerRepo, never()).findById(11);
	}

	@Test
	void assignManager_ManagerNotFound_ThrowsException() {
		when(teamsRepo.findById(1)).thenReturn(Optional.of(team));
		when(managerRepo.findById(99)).thenReturn(Optional.empty());

		assertThrows(EmployeeException.class, () -> teamsService.asssignManager(1, 99));
	}

	// ASSIGN DEPARTMENT

	@Test
	void assignDepartment_Success() throws EmployeeException {
		Department newDepartment = new Department();
		newDepartment.setDepartmentId(21L);

		when(teamsRepo.findById(1)).thenReturn(Optional.of(team));
		when(departmentRepo.findById(21L)).thenReturn(Optional.of(newDepartment));
		when(teamsRepo.save(any(Teams.class))).thenAnswer(invocation -> invocation.getArgument(0));

		teamsService.asssignDepartment(1, 21L);

		assertSame(newDepartment, team.getDepartment());
		verify(teamsRepo).save(team);
	}

	@Test
	void assignDepartment_TeamNotFound_ThrowsException() {
		when(teamsRepo.findById(99)).thenReturn(Optional.empty());

		assertThrows(EmployeeException.class, () -> teamsService.asssignDepartment(99, 21L));

		verify(departmentRepo, never()).findById(21L);
	}

	@Test
	void assignDepartment_DepartmentNotFound_ThrowsException() {
		when(teamsRepo.findById(1)).thenReturn(Optional.of(team));
		when(departmentRepo.findById(99L)).thenReturn(Optional.empty());

		assertThrows(EmployeeException.class, () -> teamsService.asssignDepartment(1, 99L));
	}

	// ASSIGN EMPLOYEE TO TEAM

	@Test
	void assignEmployeeToTeam_Success() throws EmployeeException {
		when(teamsRepo.findById(1)).thenReturn(Optional.of(team));
		when(employeeRepo.findById(30)).thenReturn(Optional.of(employee));
		when(employeeRepo.save(any(Employee.class))).thenAnswer(invocation -> invocation.getArgument(0));

		teamsService.assignEmployeeToTeam(1, 30);

		assertSame(team, employee.getTeam());
		verify(employeeRepo).save(employee);
	}

	@Test
	void assignEmployeeToTeam_TeamNotFound_ThrowsException() {
		when(teamsRepo.findById(99)).thenReturn(Optional.empty());

		assertThrows(EmployeeException.class, () -> teamsService.assignEmployeeToTeam(99, 30));

		verify(employeeRepo, never()).findById(30);
	}

	@Test
	void assignEmployeeToTeam_EmployeeNotFound_ThrowsException() {
		when(teamsRepo.findById(1)).thenReturn(Optional.of(team));
		when(employeeRepo.findById(99)).thenReturn(Optional.empty());

		assertThrows(EmployeeException.class, () -> teamsService.assignEmployeeToTeam(1, 99));

		verify(employeeRepo, never()).save(any(Employee.class));
	}

	// HELPER METHODS

	private ManagerDTO managerDTO(Integer managerId) {
		ManagerDTO dto = new ManagerDTO();
		dto.setManagerId(managerId);
		return dto;
	}

	private DepartmentDTO departmentDTO(Long departmentId) {
		DepartmentDTO dto = new DepartmentDTO();
		dto.setDepartmentId(departmentId);
		return dto;
	}
}
