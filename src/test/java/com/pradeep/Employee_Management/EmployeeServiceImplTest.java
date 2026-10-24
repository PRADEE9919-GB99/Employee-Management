package com.pradeep.Employee_Management;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
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

import com.pradeep.Employee_Management.dto.EmployeeDTO;
import com.pradeep.Employee_Management.entity.Address;
import com.pradeep.Employee_Management.entity.Employee;
import com.pradeep.Employee_Management.entity.Manager;
import com.pradeep.Employee_Management.entity.Profile;
import com.pradeep.Employee_Management.entity.Projects;
import com.pradeep.Employee_Management.entity.Teams;
import com.pradeep.Employee_Management.exception.EmployeeException;
import com.pradeep.Employee_Management.repository.AddressRepository;
import com.pradeep.Employee_Management.repository.DepartmentRepository;
import com.pradeep.Employee_Management.repository.EmployeeRepository;
import com.pradeep.Employee_Management.repository.ManagerRepository;
import com.pradeep.Employee_Management.repository.ProfileRepository;
import com.pradeep.Employee_Management.repository.ProjectsRepository;
import com.pradeep.Employee_Management.repository.TeamsRepository;
import com.pradeep.Employee_Management.service.EmployeeServiceImpl;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceImplTest {

	@Mock
	private EmployeeRepository employeeRepo;

	@Mock
	private DepartmentRepository departmentRepo;

	@Mock
	private ManagerRepository managerRepo;

	@Mock
	private TeamsRepository teamsRepo;

	@Mock
	private ProfileRepository profileRepo;

	@Mock
	private AddressRepository addressRepo;

	@Mock
	private ProjectsRepository projectsRepo;

	@InjectMocks
	private EmployeeServiceImpl employeeService;

	private Employee employee;
	private EmployeeDTO employeeDTO;

	@BeforeEach
	void setUp() {
		employee = new Employee();
		employee.setEmployeeId(1);
		employee.setEmployeeName("Praveen");
		employee.setEmail("praveen@example.com");
//		employee.setGender(1);

		employeeDTO = new EmployeeDTO();
		employeeDTO.setEmployeeName("Praveen");
		employeeDTO.setEmail("praveen@example.com");
//		employeeDTO.setGender(1);
	}

	// 1. Create employee - success
	@Test
	void createEmployeeSuccess() throws EmployeeException {

		when(employeeRepo.save(any(Employee.class))).thenAnswer(invocation -> {
			Employee saved = invocation.getArgument(0);
			saved.setEmployeeId(1);
			return saved;
		});

		Integer result = employeeService.createEmployee(employeeDTO);

		assertEquals(1, result);
		verify(employeeRepo).save(any(Employee.class));
	}

	// 2. Get employee by ID - success
	@Test
	void getEmployeeByIdSuccess() throws EmployeeException {

		when(employeeRepo.findById(1)).thenReturn(Optional.of(employee));

		EmployeeDTO result = employeeService.getEmployeeById(1);

		assertNotNull(result);
		assertEquals("Praveen", result.getEmployeeName());
		assertEquals("praveen@example.com", result.getEmail());
		assertEquals(1, result.getGender());

		verify(employeeRepo).findById(1);
	}

	// 3. Get employee by ID - not found
	@Test
	void getEmployeeByIdNotFound() {

		when(employeeRepo.findById(99)).thenReturn(Optional.empty());

		EmployeeException exception = assertThrows(EmployeeException.class, () -> employeeService.getEmployeeById(99));

		assertEquals("EMPLOYEE.SERVICE_EMPLOYEE_NOT_FOUND", exception.getMessage());

		verify(employeeRepo).findById(99);
	}

	// 4. Get all employees - success
	@Test
	void getAllEmployeesSuccess() throws EmployeeException {

		Employee second = new Employee();
		second.setEmployeeId(2);
		second.setEmployeeName("Rahul");
		second.setEmail("rahul@example.com");
//		second.setGender(1);

		when(employeeRepo.findAll()).thenReturn(Arrays.asList(employee, second));

		var result = employeeService.getAllEmployees();

		assertEquals(2, result.size());
		assertEquals("Praveen", result.get(0).getEmployeeName());
		assertEquals("Rahul", result.get(1).getEmployeeName());

		verify(employeeRepo).findAll();
	}

	// 5. Get all employees - empty list
	@Test
	void getAllEmployeesEmpty() throws EmployeeException {

		when(employeeRepo.findAll()).thenReturn(Collections.emptyList());

		var result = employeeService.getAllEmployees();

		assertNotNull(result);
		assertEquals(0, result.size());

		verify(employeeRepo).findAll();
	}

	// 6. Update employee - success
	@Test
	void updateEmployeeSuccess() throws EmployeeException {

		EmployeeDTO updatedDTO = new EmployeeDTO();
		updatedDTO.setEmployeeName("Praveen Biradar");
		updatedDTO.setEmail("praveen.b@example.com");
//		updatedDTO.setGender(1);

		when(employeeRepo.findById(1)).thenReturn(Optional.of(employee));
		when(employeeRepo.save(any(Employee.class))).thenAnswer(invocation -> invocation.getArgument(0));

		EmployeeDTO result = employeeService.updateEmployee(1, updatedDTO);

		assertEquals("Praveen Biradar", result.getEmployeeName());
		assertEquals("praveen.b@example.com", result.getEmail());
		assertEquals(1, result.getGender());

		verify(employeeRepo).findById(1);
		verify(employeeRepo).save(employee);
	}

	// 7. Update employee - ignore null or blank fields
	@Test
	void updateEmployeeIgnoresNullAndBlankFields() throws EmployeeException {

		EmployeeDTO updatedDTO = new EmployeeDTO();
		updatedDTO.setEmployeeName(" ");
		updatedDTO.setEmail(null);
		updatedDTO.setGender(null);

		when(employeeRepo.findById(1)).thenReturn(Optional.of(employee));
		when(employeeRepo.save(any(Employee.class))).thenAnswer(invocation -> invocation.getArgument(0));

		EmployeeDTO result = employeeService.updateEmployee(1, updatedDTO);

		assertEquals("Praveen", result.getEmployeeName());
		assertEquals("praveen@example.com", result.getEmail());
		assertEquals(1, result.getGender());

		verify(employeeRepo).save(employee);
	}

	// 8. Update employee - not found
	@Test
	void updateEmployeeNotFound() {

		when(employeeRepo.findById(99)).thenReturn(Optional.empty());

		assertThrows(EmployeeException.class, () -> employeeService.updateEmployee(99, employeeDTO));

		verify(employeeRepo).findById(99);
		verify(employeeRepo, never()).save(any(Employee.class));
	}

	// 9. Delete employee - success
	@Test
	void deleteEmployeeSuccess() throws EmployeeException {

		when(employeeRepo.findById(1)).thenReturn(Optional.of(employee));

		employeeService.deleteEmployee(1);

		verify(employeeRepo).findById(1);
		verify(employeeRepo).delete(employee);
	}

	// 10. Delete employee - not found
	@Test
	void deleteEmployeeNotFound() {

		when(employeeRepo.findById(99)).thenReturn(Optional.empty());

		assertThrows(EmployeeException.class, () -> employeeService.deleteEmployee(99));

		verify(employeeRepo).findById(99);
		verify(employeeRepo, never()).delete(any(Employee.class));
	}

	// 11. Assign team - success
	@Test
	void assignTeamSuccess() throws EmployeeException {

		Teams team = new Teams();

		when(employeeRepo.findById(1)).thenReturn(Optional.of(employee));
		when(teamsRepo.findById(10)).thenReturn(Optional.of(team));

		employeeService.assignTeam(1, 10);

		assertEquals(team, employee.getTeam());

		verify(teamsRepo).findById(10);
		verify(employeeRepo).save(employee);
	}

	// 12. Assign team - employee not found
	@Test
	void assignTeamEmployeeNotFound() {

		when(employeeRepo.findById(99)).thenReturn(Optional.empty());

		assertThrows(EmployeeException.class, () -> employeeService.assignTeam(99, 10));

		verify(teamsRepo, never()).findById(any());
		verify(employeeRepo, never()).save(any(Employee.class));
	}

	// 13. Assign team - team not found
	@Test
	void assignTeamNotFound() {

		when(employeeRepo.findById(1)).thenReturn(Optional.of(employee));
		when(teamsRepo.findById(99)).thenReturn(Optional.empty());

		assertThrows(EmployeeException.class, () -> employeeService.assignTeam(1, 99));

		verify(employeeRepo, never()).save(any(Employee.class));
	}

	// 14. Assign profile - success
	@Test
	void assignProfileSuccess() throws EmployeeException {

		Profile profile = new Profile();

		when(employeeRepo.findById(1)).thenReturn(Optional.of(employee));
		when(profileRepo.findById(10)).thenReturn(Optional.of(profile));

		employeeService.assignProfile(1, 10);

		assertEquals(profile, employee.getProfile());

		verify(profileRepo).findById(10);
		verify(employeeRepo).save(employee);
	}

	// 15. Assign profile - employee not found
	@Test
	void assignProfileEmployeeNotFound() {

		when(employeeRepo.findById(99)).thenReturn(Optional.empty());

		assertThrows(EmployeeException.class, () -> employeeService.assignProfile(99, 10));

		verify(profileRepo, never()).findById(any());
		verify(employeeRepo, never()).save(any(Employee.class));
	}

	// 16. Assign profile - profile not found
	@Test
	void assignProfileNotFound() {

		when(employeeRepo.findById(1)).thenReturn(Optional.of(employee));
		when(profileRepo.findById(99)).thenReturn(Optional.empty());

		assertThrows(EmployeeException.class, () -> employeeService.assignProfile(1, 99));

		verify(employeeRepo, never()).save(any(Employee.class));
	}

	// 17. Assign address - success
	@Test
	void assignAddressSuccess() throws EmployeeException {

		Address address = new Address();

		when(employeeRepo.findById(1)).thenReturn(Optional.of(employee));
		when(addressRepo.findById(10)).thenReturn(Optional.of(address));

		employeeService.assignAddress(1, 10);

		assertEquals(address, employee.getAddress());

		verify(addressRepo).findById(10);
		verify(employeeRepo).save(employee);
	}

	// 18. Assign address - employee not found
	@Test
	void assignAddressEmployeeNotFound() {

		when(employeeRepo.findById(99)).thenReturn(Optional.empty());

		assertThrows(EmployeeException.class, () -> employeeService.assignAddress(99, 10));

		verify(addressRepo, never()).findById(any());
		verify(employeeRepo, never()).save(any(Employee.class));
	}

	// 19. Assign address - address not found
	@Test
	void assignAddressNotFound() {

		when(employeeRepo.findById(1)).thenReturn(Optional.of(employee));
		when(addressRepo.findById(99)).thenReturn(Optional.empty());

		assertThrows(EmployeeException.class, () -> employeeService.assignAddress(1, 99));

		verify(employeeRepo, never()).save(any(Employee.class));
	}

	// 20. Assign project - success
	@Test
	void assignProjectsSuccess() throws EmployeeException {

		Projects project = new Projects();

		when(employeeRepo.findById(1)).thenReturn(Optional.of(employee));
		when(projectsRepo.findById(10L)).thenReturn(Optional.of(project));

		employeeService.assignProjects(1, 10L);

		assertEquals(employee, project.getEmployee());

		verify(projectsRepo).findById(10L);
		verify(projectsRepo).save(project);
	}

	// 21. Assign project - employee not found
	@Test
	void assignProjectsEmployeeNotFound() {

		when(employeeRepo.findById(99)).thenReturn(Optional.empty());

		assertThrows(EmployeeException.class, () -> employeeService.assignProjects(99, 10L));

		verify(projectsRepo, never()).findById(any());
		verify(projectsRepo, never()).save(any(Projects.class));
	}

	// 22. Assign project - project not found
	@Test
	void assignProjectsNotFound() {

		when(employeeRepo.findById(1)).thenReturn(Optional.of(employee));
		when(projectsRepo.findById(99L)).thenReturn(Optional.empty());

		assertThrows(EmployeeException.class, () -> employeeService.assignProjects(1, 99L));

		verify(projectsRepo, never()).save(any(Projects.class));
	}

	// 23. Assign reporting manager - success
	@Test
	void assignReportingManagerSuccess() throws EmployeeException {

		Employee managerEmployee = new Employee();
		managerEmployee.setEmployeeId(2);
		managerEmployee.setEmployeeName("Manager");

		Manager manager = new Manager();
		manager.setEmployee(managerEmployee);

		when(employeeRepo.findById(1)).thenReturn(Optional.of(employee));
		when(managerRepo.findById(2)).thenReturn(Optional.of(manager));

		employeeService.assignReportingManager(1, 2);

		assertEquals(manager, employee.getManager());

		verify(managerRepo).findById(2);
		verify(employeeRepo).save(employee);
	}

	// 24. Assign reporting manager - employee not found
	@Test
	void assignReportingManagerEmployeeNotFound() {

		when(employeeRepo.findById(99)).thenReturn(Optional.empty());

		assertThrows(EmployeeException.class, () -> employeeService.assignReportingManager(99, 2));

		verify(managerRepo, never()).findById(any());
		verify(employeeRepo, never()).save(any(Employee.class));
	}

	// 25. Assign reporting manager - manager not found
	@Test
	void assignReportingManagerNotFound() {

		when(employeeRepo.findById(1)).thenReturn(Optional.of(employee));
		when(managerRepo.findById(99)).thenReturn(Optional.empty());

		assertThrows(EmployeeException.class, () -> employeeService.assignReportingManager(1, 99));

		verify(employeeRepo, never()).save(any(Employee.class));
	}

	// 26. Prevent employee from reporting to themselves
	@Test
	void assignReportingManagerSelfAssignmentNotAllowed() {

		Manager manager = new Manager();
		manager.setEmployee(employee);

		when(employeeRepo.findById(1)).thenReturn(Optional.of(employee));
		when(managerRepo.findById(1)).thenReturn(Optional.of(manager));

		EmployeeException exception = assertThrows(EmployeeException.class,
				() -> employeeService.assignReportingManager(1, 1));

		assertEquals("SERVICE.EMPLOYEE_CANNOT_BE_OWN_MANAGER", exception.getMessage());

		verify(employeeRepo, never()).save(any(Employee.class));
	}

	// 27. Remove employee from team - success
	@Test
	void removeEmployeeFromTeamSuccess() throws EmployeeException {

		employee.setTeam(new Teams());

		when(employeeRepo.findById(1)).thenReturn(Optional.of(employee));

		employeeService.removeEmployeeFromTeam(1);

		assertNull(employee.getTeam());
		verify(employeeRepo).save(employee);
	}

	// 28. Remove employee from team - employee not found
	@Test
	void removeEmployeeFromTeamNotFound() {

		when(employeeRepo.findById(99)).thenReturn(Optional.empty());

		assertThrows(EmployeeException.class, () -> employeeService.removeEmployeeFromTeam(99));

		verify(employeeRepo, never()).save(any(Employee.class));
	}

	// 29. Change employee team - success
	@Test
	void changeEmployeeTeamSuccess() throws EmployeeException {

		Teams newTeam = new Teams();

		when(employeeRepo.findById(1)).thenReturn(Optional.of(employee));
		when(teamsRepo.findById(20)).thenReturn(Optional.of(newTeam));

		employeeService.changeEmployeeTeam(1, 20);

		assertEquals(newTeam, employee.getTeam());

		verify(teamsRepo).findById(20);
		verify(employeeRepo).save(employee);
	}

	// 30. Change employee team - employee not found
	@Test
	void changeEmployeeTeamEmployeeNotFound() {

		when(employeeRepo.findById(99)).thenReturn(Optional.empty());

		assertThrows(EmployeeException.class, () -> employeeService.changeEmployeeTeam(99, 20));

		verify(teamsRepo, never()).findById(any());
		verify(employeeRepo, never()).save(any(Employee.class));
	}

	// 31. Change employee team - new team not found
	@Test
	void changeEmployeeTeamNotFound() {

		when(employeeRepo.findById(1)).thenReturn(Optional.of(employee));
		when(teamsRepo.findById(99)).thenReturn(Optional.empty());

		assertThrows(EmployeeException.class, () -> employeeService.changeEmployeeTeam(1, 99));

		verify(employeeRepo, never()).save(any(Employee.class));
	}
}
