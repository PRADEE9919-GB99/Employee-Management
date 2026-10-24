package com.pradeep.Employee_Management;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.pradeep.Employee_Management.dto.EmployeeDTO;
import com.pradeep.Employee_Management.dto.ManagerDTO;
import com.pradeep.Employee_Management.dto.TeamsDTO;
import com.pradeep.Employee_Management.entity.Employee;
import com.pradeep.Employee_Management.entity.Manager;
import com.pradeep.Employee_Management.entity.Projects;
import com.pradeep.Employee_Management.entity.Teams;
import com.pradeep.Employee_Management.exception.EmployeeException;
import com.pradeep.Employee_Management.repository.EmployeeRepository;
import com.pradeep.Employee_Management.repository.ManagerRepository;
import com.pradeep.Employee_Management.repository.ProjectsRepository;
import com.pradeep.Employee_Management.repository.TeamsRepository;
import com.pradeep.Employee_Management.service.ManagerServiceImpl;

@ExtendWith(MockitoExtension.class)
class ManagerServiceImplTest {

	@Mock
	private ManagerRepository managerRepo;

	@Mock
	private EmployeeRepository employeeRepo;

	@Mock
	private TeamsRepository teamsRepo;

	@Mock
	private ProjectsRepository projectsRepo;

	@InjectMocks
	private ManagerServiceImpl managerService;

	private Employee employee;
	private Manager manager;
	private ManagerDTO managerDTO;

	@BeforeEach
	void setUp() {
		employee = new Employee();
		employee.setEmployeeId(1);
		employee.setEmployeeName("Praveen");
		employee.setEmail("praveen@example.com");
//		employee.setGender(1);

		manager = new Manager();
		manager.setManagerId(10);
		manager.setEmployee(employee);
		manager.setEmployees(new ArrayList<>());
		manager.setProjectsMentored(new ArrayList<>());
		manager.setTeams(new ArrayList<>());

		EmployeeDTO employeeDTO = new EmployeeDTO();
		employeeDTO.setEmployeeId(1);
		employeeDTO.setEmployeeName("Praveen");
		employeeDTO.setEmail("praveen@example.com");
//		employeeDTO.setGender(1);

		managerDTO = new ManagerDTO();
		managerDTO.setEmployee(employeeDTO);
		managerDTO.setTeams(new ArrayList<>());
	}

	// 1. Create manager - success
	@Test
	void createManagerSuccess() throws EmployeeException {
		when(employeeRepo.findById(1)).thenReturn(Optional.of(employee));
		when(managerRepo.existsByEmployee_EmployeeId(1)).thenReturn(false);
		when(managerRepo.save(any(Manager.class))).thenAnswer(invocation -> {
			Manager saved = invocation.getArgument(0);
			saved.setManagerId(10);
			return saved;
		});

		Integer result = managerService.createManager(managerDTO);

		assertEquals(10, result);
		verify(employeeRepo).findById(1);
		verify(managerRepo).existsByEmployee_EmployeeId(1);
		verify(managerRepo).save(any(Manager.class));
	}

	// 2. Create manager - null DTO
	@Test
	void createManagerNullDTO() {
		assertThrows(EmployeeException.class, () -> managerService.createManager(null));

		verify(employeeRepo, never()).findById(any());
		verify(managerRepo, never()).save(any(Manager.class));
	}

	// 3. Create manager - employee details missing
	@Test
	void createManagerEmployeeIdMissing() {
		ManagerDTO dto = new ManagerDTO();

		assertThrows(EmployeeException.class, () -> managerService.createManager(dto));

		verify(managerRepo, never()).save(any(Manager.class));
	}

	// 4. Create manager - employee not found
	@Test
	void createManagerEmployeeNotFound() {
		when(employeeRepo.findById(1)).thenReturn(Optional.empty());

		assertThrows(EmployeeException.class, () -> managerService.createManager(managerDTO));

		verify(managerRepo, never()).save(any(Manager.class));
	}

	// 5. Create manager - employee already has a manager record
	@Test
	void createManagerAlreadyExists() {
		when(employeeRepo.findById(1)).thenReturn(Optional.of(employee));
		when(managerRepo.existsByEmployee_EmployeeId(1)).thenReturn(true);

		assertThrows(EmployeeException.class, () -> managerService.createManager(managerDTO));

		verify(managerRepo, never()).save(any(Manager.class));
	}

	// 6. Create manager - team not found
	@Test
	void createManagerTeamNotFound() {
		TeamsDTO teamDTO = new TeamsDTO();
		teamDTO.setTeamsId(99);
		managerDTO.setTeams(Arrays.asList(teamDTO));

		when(employeeRepo.findById(1)).thenReturn(Optional.of(employee));
		when(managerRepo.existsByEmployee_EmployeeId(1)).thenReturn(false);
		when(teamsRepo.findById(99)).thenReturn(Optional.empty());

		assertThrows(EmployeeException.class, () -> managerService.createManager(managerDTO));

		verify(managerRepo, never()).save(any(Manager.class));
	}

	// 7. Get manager by ID - success
	@Test
	void getManagerByIdSuccess() throws EmployeeException {
		when(managerRepo.findById(10)).thenReturn(Optional.of(manager));

		ManagerDTO result = managerService.getManagerById(10);

		assertNotNull(result);
		assertNotNull(result.getEmployee());
		assertEquals(1, result.getEmployee().getEmployeeId());

		verify(managerRepo).findById(10);
	}

	// 8. Get manager by ID - not found
	@Test
	void getManagerByIdNotFound() {
		when(managerRepo.findById(99)).thenReturn(Optional.empty());

		EmployeeException exception = assertThrows(EmployeeException.class, () -> managerService.getManagerById(99));

		assertEquals("MANAGER.SERVICE_MANAGER_NOT_FOUND", exception.getMessage());

		verify(managerRepo).findById(99);
	}

	// 9. Get all managers - success
	@Test
	void getAllManagersSuccess() throws EmployeeException {
		Manager secondManager = new Manager();
		secondManager.setManagerId(20);
		secondManager.setEmployee(employee);
		secondManager.setEmployees(new ArrayList<>());
		secondManager.setProjectsMentored(new ArrayList<>());
		secondManager.setTeams(new ArrayList<>());

		when(managerRepo.findAll()).thenReturn(Arrays.asList(manager, secondManager));

		var result = managerService.getAllManagers();

		assertEquals(2, result.size());
		assertNotNull(result.get(0));
		assertNotNull(result.get(1));

		verify(managerRepo).findAll();
	}

	// 10. Get all managers - empty list
	@Test
	void getAllManagersEmpty() throws EmployeeException {
		when(managerRepo.findAll()).thenReturn(Collections.emptyList());

		var result = managerService.getAllManagers();

		assertNotNull(result);
		assertEquals(0, result.size());

		verify(managerRepo).findAll();
	}

	// 11. Update manager - success
	@Test
	void updateManagerSuccess() throws EmployeeException {
		ManagerDTO updatedDTO = new ManagerDTO();
		updatedDTO.setTeams(new ArrayList<>());

		when(managerRepo.findById(10)).thenReturn(Optional.of(manager));
		when(managerRepo.save(any(Manager.class))).thenAnswer(invocation -> invocation.getArgument(0));

		ManagerDTO result = managerService.updateManager(10, updatedDTO);

		assertNotNull(result);
		verify(managerRepo).findById(10);
		verify(managerRepo).save(manager);
	}

	// 12. Update manager - null DTO
	@Test
	void updateManagerNullDTO() {
		assertThrows(EmployeeException.class, () -> managerService.updateManager(10, null));

		verify(managerRepo, never()).save(any(Manager.class));
	}

	// 13. Update manager - manager not found
	@Test
	void updateManagerNotFound() {
		when(managerRepo.findById(99)).thenReturn(Optional.empty());

		assertThrows(EmployeeException.class, () -> managerService.updateManager(99, managerDTO));

		verify(managerRepo, never()).save(any(Manager.class));
	}

	// 14. Update manager - replacement employee not found
	@Test
	void updateManagerEmployeeNotFound() {
		EmployeeDTO replacementDTO = new EmployeeDTO();
		replacementDTO.setEmployeeId(99);

		ManagerDTO dto = new ManagerDTO();
		dto.setEmployee(replacementDTO);

		when(managerRepo.findById(10)).thenReturn(Optional.of(manager));
		when(employeeRepo.findById(99)).thenReturn(Optional.empty());

		assertThrows(EmployeeException.class, () -> managerService.updateManager(10, dto));

		verify(managerRepo, never()).save(any(Manager.class));
	}

	// 15. Update manager - employee already belongs to another manager
	@Test
	void updateManagerDuplicateEmployee() {
		Employee otherEmployee = new Employee();
		otherEmployee.setEmployeeId(2);

		EmployeeDTO replacementDTO = new EmployeeDTO();
		replacementDTO.setEmployeeId(2);

		ManagerDTO dto = new ManagerDTO();
		dto.setEmployee(replacementDTO);

		when(managerRepo.findById(10)).thenReturn(Optional.of(manager));
		when(employeeRepo.findById(2)).thenReturn(Optional.of(otherEmployee));
		when(managerRepo.existsByEmployee_EmployeeId(2)).thenReturn(true);

		assertThrows(EmployeeException.class, () -> managerService.updateManager(10, dto));

		verify(managerRepo, never()).save(any(Manager.class));
	}

	// 16. Update manager - team not found
	@Test
	void updateManagerTeamNotFound() {
		TeamsDTO teamDTO = new TeamsDTO();
		teamDTO.setTeamsId(99);

		ManagerDTO dto = new ManagerDTO();
		dto.setTeams(Arrays.asList(teamDTO));

		when(managerRepo.findById(10)).thenReturn(Optional.of(manager));
		when(teamsRepo.findById(99)).thenReturn(Optional.empty());

		assertThrows(EmployeeException.class, () -> managerService.updateManager(10, dto));

		verify(managerRepo, never()).save(any(Manager.class));
	}

	// 17. Delete manager - success and relationship cleanup
	@Test
	void deleteManagerSuccess() throws EmployeeException {
		Employee report = new Employee();
		report.setEmployeeId(2);
		report.setManager(manager);

		Projects project = new Projects();
		project.setManager(manager);

		Teams team = new Teams();
		team.setManager(manager);

		manager.setEmployees(new ArrayList<>(Arrays.asList(report)));
		manager.setProjectsMentored(new ArrayList<>(Arrays.asList(project)));
		manager.setTeams(new ArrayList<>(Arrays.asList(team)));

		when(managerRepo.findById(10)).thenReturn(Optional.of(manager));

		managerService.deleteManager(10);

		assertNull(report.getManager());
		assertNull(project.getManager());
		assertNull(team.getManager());
		assertNull(manager.getEmployee());
		assertEquals(0, manager.getEmployees().size());
		assertEquals(0, manager.getProjectsMentored().size());
		assertEquals(0, manager.getTeams().size());

		verify(employeeRepo).saveAll(manager.getEmployees());
		verify(projectsRepo).saveAll(manager.getProjectsMentored());
		verify(teamsRepo).saveAll(manager.getTeams());
		verify(managerRepo).delete(manager);
	}

	// 18. Delete manager - not found
	@Test
	void deleteManagerNotFound() {
		when(managerRepo.findById(99)).thenReturn(Optional.empty());

		assertThrows(EmployeeException.class, () -> managerService.deleteManager(99));

		verify(managerRepo, never()).delete(any(Manager.class));
	}

	// 19. Assign employee - success
	@Test
	void assignEmployeeSuccess() throws EmployeeException {
		when(managerRepo.findById(10)).thenReturn(Optional.of(manager));
		when(employeeRepo.findById(2)).thenReturn(Optional.of(employee));

		managerService.assignEmployee(10, 2);

		assertEquals(manager, employee.getManager());
		verify(employeeRepo).save(employee);
	}

	// 20. Assign employee - manager not found
	@Test
	void assignEmployeeManagerNotFound() {
		when(managerRepo.findById(99)).thenReturn(Optional.empty());

		assertThrows(EmployeeException.class, () -> managerService.assignEmployee(99, 2));

		verify(employeeRepo, never()).save(any(Employee.class));
	}

	// 21. Assign employee - employee not found
	@Test
	void assignEmployeeEmployeeNotFound() {
		when(managerRepo.findById(10)).thenReturn(Optional.of(manager));
		when(employeeRepo.findById(99)).thenReturn(Optional.empty());

		assertThrows(EmployeeException.class, () -> managerService.assignEmployee(10, 99));

		verify(employeeRepo, never()).save(any(Employee.class));
	}

	// 22. Assign project - success
	@Test
	void assignProjectsSuccess() throws EmployeeException {
		Projects project = new Projects();

		when(managerRepo.findById(10)).thenReturn(Optional.of(manager));
		when(projectsRepo.findById(20L)).thenReturn(Optional.of(project));

		managerService.assignProjects(10, 20);

		assertEquals(manager, project.getManager());
		verify(projectsRepo).save(project);
	}

	// 23. Assign project - manager not found
	@Test
	void assignProjectsManagerNotFound() {
		when(managerRepo.findById(99)).thenReturn(Optional.empty());

		assertThrows(EmployeeException.class, () -> managerService.assignProjects(99, 20));

		verify(projectsRepo, never()).save(any(Projects.class));
	}

	// 24. Assign project - project not found
	@Test
	void assignProjectsProjectNotFound() {
		when(managerRepo.findById(10)).thenReturn(Optional.of(manager));
		when(projectsRepo.findById(99L)).thenReturn(Optional.empty());

		assertThrows(EmployeeException.class, () -> managerService.assignProjects(10, 99));

		verify(projectsRepo, never()).save(any(Projects.class));
	}
}
