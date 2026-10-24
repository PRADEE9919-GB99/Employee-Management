package com.pradeep.Employee_Management;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
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

import com.pradeep.Employee_Management.dto.DepartmentDTO;
import com.pradeep.Employee_Management.entity.Department;
import com.pradeep.Employee_Management.entity.Teams;
import com.pradeep.Employee_Management.exception.EmployeeException;
import com.pradeep.Employee_Management.repository.DepartmentRepository;
import com.pradeep.Employee_Management.repository.EmployeeRepository;
import com.pradeep.Employee_Management.repository.TeamsRepository;
import com.pradeep.Employee_Management.service.DepartmentServiceImpl;
@ExtendWith(MockitoExtension.class)
public class DepartmentServiceImplTest {
	@Mock
	private DepartmentRepository departmentRepo;

	@Mock
	private EmployeeRepository employeeRepo;

	@Mock
	private TeamsRepository teamsRepo;

	@InjectMocks
	private DepartmentServiceImpl departmentService;

	private Department department;
	private DepartmentDTO departmentDTO;

	@BeforeEach
	void setUp() {
		department = new Department();
		department.setDepartmentId(1L);
		department.setDepartmentName("Development");

		departmentDTO = new DepartmentDTO();
		departmentDTO.setDepartmentId(1L);
		departmentDTO.setDepartmentName("Development");
	}

	// 1. createDepartment - success
	@Test
	void createDepartmentSuccess() throws EmployeeException {

		when(departmentRepo.save(any(Department.class))).thenAnswer(invocation -> {
			Department saved = invocation.getArgument(0);
			saved.setDepartmentId(1L);
			return saved;
		});

		Long result = departmentService.createDepartment(departmentDTO);

		assertEquals(1L, result);
		verify(departmentRepo).save(any(Department.class));
	}

	// 2. getDepartmentById - success
	@Test
	void getDepartmentByIdSuccess() throws EmployeeException {

		when(departmentRepo.findById(1L)).thenReturn(Optional.of(department));

		DepartmentDTO result = departmentService.getDepartmentById(1L);

		assertNotNull(result);
		assertEquals(1L, result.getDepartmentId());
		assertEquals("Development", result.getDepartmentName());

		verify(departmentRepo).findById(1L);
	}

	// 3. getDepartmentById - invalid ID
	@Test
	void getDepartmentByIdInvalidId() {

		assertThrows(EmployeeException.class, () -> departmentService.getDepartmentById(0L));

		assertThrows(EmployeeException.class, () -> departmentService.getDepartmentById(-1L));

		assertThrows(EmployeeException.class, () -> departmentService.getDepartmentById(null));

		verify(departmentRepo, never()).findById(any());
	}

	// 4. getDepartmentById - department not found
	@Test
	void getDepartmentByIdNotFound() {

		when(departmentRepo.findById(99L)).thenReturn(Optional.empty());

		EmployeeException exception = assertThrows(EmployeeException.class,
				() -> departmentService.getDepartmentById(99L));

		assertEquals("DEPARTMENT.SERVICE_DEPARTMENT_NOT_FOUND", exception.getMessage());

		verify(departmentRepo).findById(99L);
	}

	// 5. getAllDepartments - success
	@Test
	void getAllDepartmentsSuccess() throws EmployeeException {

		Department secondDepartment = new Department();
		secondDepartment.setDepartmentId(2L);
		secondDepartment.setDepartmentName("Testing");

		when(departmentRepo.findAll()).thenReturn(Arrays.asList(department, secondDepartment));

		var result = departmentService.getAllDepartments();

		assertEquals(2, result.size());
		assertEquals("Development", result.get(0).getDepartmentName());
		assertEquals("Testing", result.get(1).getDepartmentName());

		verify(departmentRepo).findAll();
	}

	// 6. getAllDepartments - empty list
	@Test
	void getAllDepartmentsEmpty() throws EmployeeException {

		when(departmentRepo.findAll()).thenReturn(Collections.emptyList());

		var result = departmentService.getAllDepartments();

		assertNotNull(result);
		assertEquals(0, result.size());

		verify(departmentRepo).findAll();
	}

	// 7. updateDepartment - success
	@Test
	void updateDepartmentSuccess() throws EmployeeException {

		DepartmentDTO updatedDTO = new DepartmentDTO();
		updatedDTO.setDepartmentName("Quality Assurance");

		when(departmentRepo.findById(1L)).thenReturn(Optional.of(department));
		when(departmentRepo.save(any(Department.class))).thenAnswer(invocation -> invocation.getArgument(0));

		DepartmentDTO result = departmentService.updateDepartment(1L, updatedDTO);

		assertNotNull(result);
		assertEquals(1L, result.getDepartmentId());
		assertEquals("Quality Assurance", result.getDepartmentName());

		verify(departmentRepo).findById(1L);
		verify(departmentRepo).save(department);
	}

	// 8. updateDepartment - invalid ID
	@Test
	void updateDepartmentInvalidId() {

		assertThrows(EmployeeException.class, () -> departmentService.updateDepartment(0L, departmentDTO));

		assertThrows(EmployeeException.class, () -> departmentService.updateDepartment(-1L, departmentDTO));

		assertThrows(EmployeeException.class, () -> departmentService.updateDepartment(null, departmentDTO));

		verify(departmentRepo, never()).findById(any());
		verify(departmentRepo, never()).save(any(Department.class));
	}

	// 9. updateDepartment - department not found
	@Test
	void updateDepartmentNotFound() {

		when(departmentRepo.findById(99L)).thenReturn(Optional.empty());

		assertThrows(EmployeeException.class, () -> departmentService.updateDepartment(99L, departmentDTO));

		verify(departmentRepo).findById(99L);
		verify(departmentRepo, never()).save(any(Department.class));
	}

	// 10. deleteDepartment - success
	@Test
	void deleteDepartmentSuccess() throws EmployeeException {

		when(departmentRepo.findById(1L)).thenReturn(Optional.of(department));

		departmentService.deleteDepartment(1L);

		verify(departmentRepo).findById(1L);
		verify(departmentRepo).delete(department);
	}

	// 11. deleteDepartment - invalid ID
	@Test
	void deleteDepartmentInvalidId() {

		assertThrows(EmployeeException.class, () -> departmentService.deleteDepartment(0L));

		assertThrows(EmployeeException.class, () -> departmentService.deleteDepartment(-1L));

		assertThrows(EmployeeException.class, () -> departmentService.deleteDepartment(null));

		verify(departmentRepo, never()).findById(any());
		verify(departmentRepo, never()).delete(any(Department.class));
	}

	// 12. deleteDepartment - department not found
	@Test
	void deleteDepartmentNotFound() {

		when(departmentRepo.findById(99L)).thenReturn(Optional.empty());

		assertThrows(EmployeeException.class, () -> departmentService.deleteDepartment(99L));

		verify(departmentRepo).findById(99L);
		verify(departmentRepo, never()).delete(any(Department.class));
	}

	// 13. assignTeamToDepartment - success
	@Test
	void assignTeamToDepartmentSuccess() throws EmployeeException {

		Teams team = new Teams();
		team.setTeamsId(10);
		team.setTeamName("Backend Team");

		when(departmentRepo.findById(1L)).thenReturn(Optional.of(department));
		when(teamsRepo.findById(10)).thenReturn(Optional.of(team));

		departmentService.assignTeamToDepartment(1L, 10);

		assertEquals(department, team.getDepartment());
		assertNotNull(department.getTeams());
		assertEquals(1, department.getTeams().size());
		assertEquals(team, department.getTeams().get(0));

		verify(departmentRepo).findById(1L);
		verify(teamsRepo).findById(10);
		verify(teamsRepo).save(team);
	}

	// 14. assignTeamToDepartment - department not found
	@Test
	void assignTeamToDepartmentDepartmentNotFound() {

		when(departmentRepo.findById(99L)).thenReturn(Optional.empty());

		assertThrows(EmployeeException.class, () -> departmentService.assignTeamToDepartment(99L, 10));

		verify(departmentRepo).findById(99L);
		verify(teamsRepo, never()).findById(any());
		verify(teamsRepo, never()).save(any(Teams.class));
	}

	// 15. assignTeamToDepartment - team not found
	@Test
	void assignTeamToDepartmentTeamNotFound() {

		when(departmentRepo.findById(1L)).thenReturn(Optional.of(department));
		when(teamsRepo.findById(99)).thenReturn(Optional.empty());

		assertThrows(EmployeeException.class, () -> departmentService.assignTeamToDepartment(1L, 99));

		verify(departmentRepo).findById(1L);
		verify(teamsRepo).findById(99);
		verify(teamsRepo, never()).save(any(Teams.class));
	}
}
