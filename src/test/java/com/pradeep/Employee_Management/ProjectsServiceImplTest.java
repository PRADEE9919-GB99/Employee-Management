package com.pradeep.Employee_Management;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
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

import com.pradeep.Employee_Management.dto.ProjectsDTO;
import com.pradeep.Employee_Management.entity.Employee;
import com.pradeep.Employee_Management.entity.Manager;
import com.pradeep.Employee_Management.entity.Projects;
import com.pradeep.Employee_Management.exception.EmployeeException;
import com.pradeep.Employee_Management.repository.EmployeeRepository;
import com.pradeep.Employee_Management.repository.ManagerRepository;
import com.pradeep.Employee_Management.repository.ProjectsRepository;
import com.pradeep.Employee_Management.service.ProjectsServiceImpl;

@ExtendWith(MockitoExtension.class)
class ProjectsServiceImplTest {

	@Mock
	private ProjectsRepository projectsRepo;

	@Mock
	private EmployeeRepository employeeRepo;

	@Mock
	private ManagerRepository managerRepo;

	@InjectMocks
	private ProjectsServiceImpl projectsService;

	private Projects project;
	private Employee employee;
	private Manager manager;
	private ProjectsDTO projectDTO;

	@BeforeEach
	void setUp() {
		employee = new Employee();
		employee.setEmployeeId(10);

		manager = new Manager();
		manager.setManagerId(20);

		project = new Projects();
		project.setProjectId(1L);
		project.setProjectName("Employee Management");
		project.setEmployee(employee);
		project.setManager(manager);

		projectDTO = new ProjectsDTO();
		projectDTO.setProjectName("Employee Management");
	}

	// CREATE PROJECT

	@Test
	void createProject_Success() throws EmployeeException {
		when(projectsRepo.save(any(Projects.class))).thenAnswer(invocation -> {
			Projects saved = invocation.getArgument(0);
			saved.setProjectId(1L);
			return saved;
		});

		Integer result = projectsService.createProject(projectDTO);

		assertEquals(1, result);
		verify(projectsRepo).save(any(Projects.class));
	}

	@Test
	void createProject_WithEmployee_Success() throws EmployeeException {
		projectDTO.setEmployee(employeeDTO(10));

		when(employeeRepo.findById(10)).thenReturn(Optional.of(employee));
		when(projectsRepo.save(any(Projects.class))).thenAnswer(invocation -> {
			Projects saved = invocation.getArgument(0);
			saved.setProjectId(2L);
			return saved;
		});

		Integer result = projectsService.createProject(projectDTO);

		assertEquals(2, result);
		verify(employeeRepo).findById(10);
		verify(projectsRepo).save(any(Projects.class));
	}

	@Test
	void createProject_EmployeeNotFound_ThrowsException() {
		projectDTO.setEmployee(employeeDTO(99));
		when(employeeRepo.findById(99)).thenReturn(Optional.empty());

		assertThrows(EmployeeException.class, () -> projectsService.createProject(projectDTO));

		verify(projectsRepo, never()).save(any(Projects.class));
	}

	@Test
	void createProject_ManagerNotFound_ThrowsException() {
		projectDTO.setManager(managerDTO(99));
		when(managerRepo.findById(99)).thenReturn(Optional.empty());

		assertThrows(EmployeeException.class, () -> projectsService.createProject(projectDTO));

		verify(projectsRepo, never()).save(any(Projects.class));
	}

	// GET PROJECT BY ID

	@Test
	void getProjectById_Success() throws EmployeeException {
		when(projectsRepo.findById(1L)).thenReturn(Optional.of(project));

		ProjectsDTO result = projectsService.getProjectById(1L);

		assertNotNull(result);
		assertEquals("Employee Management", result.getProjectName());
		verify(projectsRepo).findById(1L);
	}

	@Test
	void getProjectById_NotFound_ThrowsException() {
		when(projectsRepo.findById(99L)).thenReturn(Optional.empty());

		assertThrows(EmployeeException.class, () -> projectsService.getProjectById(99L));
	}

	// GET ALL PROJECTS

	@Test
	void getAllProjects_Success() throws EmployeeException {
		Projects secondProject = new Projects();
		secondProject.setProjectId(2L);
		secondProject.setProjectName("QA Automation");

		when(projectsRepo.findAll()).thenReturn(Arrays.asList(project, secondProject));

		var result = projectsService.getAllProjects();

		assertEquals(2, result.size());
		assertEquals("Employee Management", result.get(0).getProjectName());
		assertEquals("QA Automation", result.get(1).getProjectName());
		verify(projectsRepo).findAll();
	}

	@Test
	void getAllProjects_EmptyList() throws EmployeeException {
		when(projectsRepo.findAll()).thenReturn(Collections.emptyList());

		var result = projectsService.getAllProjects();

		assertNotNull(result);
		assertTrue(result.isEmpty());
	}

	// UPDATE PROJECT

	@Test
	void updateProject_Success() throws EmployeeException {
		ProjectsDTO updateDTO = new ProjectsDTO();
		updateDTO.setProjectName("Updated Project");

		when(projectsRepo.findById(1L)).thenReturn(Optional.of(project));
		when(projectsRepo.save(any(Projects.class))).thenAnswer(invocation -> invocation.getArgument(0));

		ProjectsDTO result = projectsService.updateProject(1L, updateDTO);

		assertEquals("Updated Project", result.getProjectName());
		verify(projectsRepo).save(project);
	}

	@Test
	void updateProject_ProjectNotFound_ThrowsException() {
		when(projectsRepo.findById(99L)).thenReturn(Optional.empty());

		assertThrows(EmployeeException.class, () -> projectsService.updateProject(99L, projectDTO));

		verify(projectsRepo, never()).save(any(Projects.class));
	}

	@Test
	void updateProject_EmployeeNotFound_ThrowsException() {
		ProjectsDTO updateDTO = new ProjectsDTO();
		updateDTO.setEmployee(employeeDTO(99));

		when(projectsRepo.findById(1L)).thenReturn(Optional.of(project));
		when(employeeRepo.findById(99)).thenReturn(Optional.empty());

		assertThrows(EmployeeException.class, () -> projectsService.updateProject(1L, updateDTO));

		verify(projectsRepo, never()).save(any(Projects.class));
	}

	@Test
	void updateProject_ManagerNotFound_ThrowsException() {
		ProjectsDTO updateDTO = new ProjectsDTO();
		updateDTO.setManager(managerDTO(99));

		when(projectsRepo.findById(1L)).thenReturn(Optional.of(project));
		when(managerRepo.findById(99)).thenReturn(Optional.empty());

		assertThrows(EmployeeException.class, () -> projectsService.updateProject(1L, updateDTO));

		verify(projectsRepo, never()).save(any(Projects.class));
	}

	// DELETE PROJECT

	@Test
	void deleteProject_Success() throws EmployeeException {
		when(projectsRepo.findById(1L)).thenReturn(Optional.of(project));

		projectsService.deleteProject(1L);

		assertNull(project.getEmployee());
		assertNull(project.getManager());
		verify(projectsRepo).deleteById(1L);
	}

	@Test
	void deleteProject_NotFound_ThrowsException() {
		when(projectsRepo.findById(99L)).thenReturn(Optional.empty());

		assertThrows(EmployeeException.class, () -> projectsService.deleteProject(99L));

		verify(projectsRepo, never()).deleteById(99L);
	}

	// GET PROJECTS BY EMPLOYEE

	@Test
	void getProjectsByEmployee_Success() throws EmployeeException {
		when(employeeRepo.existsById(10)).thenReturn(true);
		when(projectsRepo.findByEmployee_EmployeeId(10)).thenReturn(Collections.singletonList(project));

		var result = projectsService.getProjectsByEmployee(10);

		assertEquals(1, result.size());
		assertEquals("Employee Management", result.get(0).getProjectName());
		verify(projectsRepo).findByEmployee_EmployeeId(10);
	}

	@Test
	void getProjectsByEmployee_EmployeeNotFound_ThrowsException() {
		when(employeeRepo.existsById(99)).thenReturn(false);

		assertThrows(EmployeeException.class, () -> projectsService.getProjectsByEmployee(99));

		verify(projectsRepo, never()).findByEmployee_EmployeeId(99);
	}

	@Test
	void getProjectsByEmployee_EmptyList() throws EmployeeException {
		when(employeeRepo.existsById(10)).thenReturn(true);
		when(projectsRepo.findByEmployee_EmployeeId(10)).thenReturn(Collections.emptyList());

		var result = projectsService.getProjectsByEmployee(10);

		assertTrue(result.isEmpty());
	}

	// GET PROJECTS BY MANAGER

	@Test
	void getProjectsByManager_Success() throws EmployeeException {
		when(managerRepo.existsById(20)).thenReturn(true);
		when(projectsRepo.findByManager_ManagerId(20)).thenReturn(Collections.singletonList(project));

		var result = projectsService.getProjectsByManager(20);

		assertEquals(1, result.size());
		assertEquals("Employee Management", result.get(0).getProjectName());
		verify(projectsRepo).findByManager_ManagerId(20);
	}

	@Test
	void getProjectsByManager_ManagerNotFound_ThrowsException() {
		when(managerRepo.existsById(99)).thenReturn(false);

		assertThrows(EmployeeException.class, () -> projectsService.getProjectsByManager(99));

		verify(projectsRepo, never()).findByManager_ManagerId(99);
	}

	@Test
	void getProjectsByManager_EmptyList() throws EmployeeException {
		when(managerRepo.existsById(20)).thenReturn(true);
		when(projectsRepo.findByManager_ManagerId(20)).thenReturn(Collections.emptyList());

		var result = projectsService.getProjectsByManager(20);

		assertTrue(result.isEmpty());
	}

	// ASSIGN EMPLOYEE TO PROJECT

	@Test
	void assignProject_Success() throws EmployeeException {
		when(projectsRepo.findById(1L)).thenReturn(Optional.of(project));
		when(employeeRepo.findById(10)).thenReturn(Optional.of(employee));

		projectsService.assignProject(1L, 10);

		assertSame(employee, project.getEmployee());
	}

	@Test
	void assignProject_ProjectNotFound_ThrowsException() {
		when(projectsRepo.findById(99L)).thenReturn(Optional.empty());

		assertThrows(EmployeeException.class, () -> projectsService.assignProject(99L, 10));

		verify(employeeRepo, never()).findById(10);
	}

	@Test
	void assignProject_EmployeeNotFound_ThrowsException() {
		when(projectsRepo.findById(1L)).thenReturn(Optional.of(project));
		when(employeeRepo.findById(99)).thenReturn(Optional.empty());

		assertThrows(EmployeeException.class, () -> projectsService.assignProject(1L, 99));
	}

	// ASSIGN MANAGER TO PROJECT

	@Test
	void assignManager_Success() throws EmployeeException {
		when(projectsRepo.findById(1L)).thenReturn(Optional.of(project));
		when(managerRepo.findById(20)).thenReturn(Optional.of(manager));

		projectsService.assignManager(1L, 20);

		assertSame(manager, project.getManager());
	}

	@Test
	void assignManager_ProjectNotFound_ThrowsException() {
		when(projectsRepo.findById(99L)).thenReturn(Optional.empty());

		assertThrows(EmployeeException.class, () -> projectsService.assignManager(99L, 20));

		verify(managerRepo, never()).findById(20);
	}

	@Test
	void assignManager_ManagerNotFound_ThrowsException() {
		when(projectsRepo.findById(1L)).thenReturn(Optional.of(project));
		when(managerRepo.findById(99)).thenReturn(Optional.empty());

		assertThrows(EmployeeException.class, () -> projectsService.assignManager(1L, 99));
	}

	// HELPER METHODS

	private com.pradeep.Employee_Management.dto.EmployeeDTO employeeDTO(Integer employeeId) {
		com.pradeep.Employee_Management.dto.EmployeeDTO dto = new com.pradeep.Employee_Management.dto.EmployeeDTO();
		dto.setEmployeeId(employeeId);
		return dto;
	}

	private com.pradeep.Employee_Management.dto.ManagerDTO managerDTO(Integer managerId) {
		com.pradeep.Employee_Management.dto.ManagerDTO dto = new com.pradeep.Employee_Management.dto.ManagerDTO();
		dto.setManagerId(managerId);
		return dto;
	}
}
