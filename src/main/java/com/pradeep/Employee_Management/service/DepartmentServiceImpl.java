package com.pradeep.Employee_Management.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pradeep.Employee_Management.dto.DepartmentDTO;
import com.pradeep.Employee_Management.entity.Department;
import com.pradeep.Employee_Management.entity.Teams;
import com.pradeep.Employee_Management.exception.EmployeeException;
import com.pradeep.Employee_Management.repository.DepartmentRepository;
import com.pradeep.Employee_Management.repository.EmployeeRepository;
import com.pradeep.Employee_Management.repository.TeamsRepository;
import com.pradeep.Employee_Management.util.DepartmentUtil;

@Service
@Transactional
public class DepartmentServiceImpl implements DepartmentService {
//	@Autowired
//	private DepartmentUtil departmentUtil;

	private DepartmentRepository departmentRepo;
	private EmployeeRepository employeeRepo;
	private TeamsRepository teamsRepo;

	public DepartmentServiceImpl(DepartmentRepository departmentRepo, EmployeeRepository employeeRepo,
			TeamsRepository teamsRepo) {
		super();
		this.departmentRepo = departmentRepo;
		this.employeeRepo = employeeRepo;
		this.teamsRepo = teamsRepo;
	}

	@Override
	public Long createDepartment(DepartmentDTO dto) throws EmployeeException {
		Department department = DepartmentUtil.toEntity(dto);
		departmentRepo.save(department);

		return department.getDepartmentId();
	}

	@Override
	public DepartmentDTO getDepartmentById(Long id) throws EmployeeException {
		if (id == null || id <= 0) {
			throw new EmployeeException("GENERAL.ITEM_NOT_NULL");
		}

		Department department = departmentRepo.findById(id)
				.orElseThrow(() -> new EmployeeException("DEPARTMENT.SERVICE_DEPARTMENT_NOT_FOUND"));

		return DepartmentUtil.toDTO(department);
	}

	@Override
	public List<DepartmentDTO> getAllDepartments() throws EmployeeException {
		List<Department> departments = departmentRepo.findAll();
		List<DepartmentDTO> departmentDTOs = new ArrayList<>();

		for (Department department : departments) {
			departmentDTOs.add(DepartmentUtil.toDTO(department));
		}

		return departmentDTOs;
	}

	@Override
	public DepartmentDTO updateDepartment(Long id, DepartmentDTO dto) throws EmployeeException {
		// TODO Auto-generated method stub

		if (id == null || id <= 0) {
			throw new EmployeeException("GENERAL.ITEM_NOT_NULL");
		}

		Department department = departmentRepo.findById(id)
				.orElseThrow(() -> new EmployeeException("DEPARTMENT.SERVICE_DEPARTMENT_NOT_FOUND"));

		department.setDepartmentName(dto.getDepartmentName());

		Department updatedDepartment = departmentRepo.save(department);

		return DepartmentUtil.toDTO(updatedDepartment);
	}

	@Override
	public void deleteDepartment(Long id) throws EmployeeException {
		// TODO Auto-generated method stub
		if (id == null || id <= 0) {
			throw new EmployeeException("GENERAL.ITEM_NOT_NULL");
		}

		Department department = departmentRepo.findById(id)
				.orElseThrow(() -> new EmployeeException("DEPARTMENT.SERVICE_DEPARTMENT_NOT_FOUND"));

		departmentRepo.delete(department);
	}

//	@Override
//	public void assignEmployee(Long id, Integer employeeId) throws EmployeeException {
//		// TODO Auto-generated method stub
//		Department department = departmentRepo.findById(id)
//				.orElseThrow(() -> new EmployeeException("SERVICE.DEPARTMENT_NOT_FOUND"));
//
//		Employee employee = employeeRepo.findById(employeeId)
//				.orElseThrow(() -> new EmployeeException("SERVICE.EMPLOYEE_NOT_FOUND"));
//
//		employee.setDepartment(department);
//
//		employeeRepo.save(employee);
//	}
//
//	@Override
//	public void assignTeams(Long id, Integer teamsId) throws EmployeeException {
//		// TODO Auto-generated method stub
//		Department department = departmentRepo.findById(id)
//				.orElseThrow(() -> new EmployeeException("SERVICE.DEPARTMENT_NOT_FOUND"));
//
//		Teams team = teamsRepo.findById(teamsId).orElseThrow(() -> new EmployeeException("SERVICE.TEAMS_NOT_FOUND"));
//
//		team.setDepartment(department);
//
//		teamsRepo.save(team);
//	}

	@Override
	public void assignTeamToDepartment(Long departmentId, Integer teamId) throws EmployeeException {
		// TODO Auto-generated method stub
		Department department = departmentRepo.findById(departmentId)
				.orElseThrow(() -> new EmployeeException("DEPARTMENT.SERVICE_DEPARTMENT_NOT_FOUND"));
		List<Teams> teams = new ArrayList<>();

		Teams team = teamsRepo.findById(teamId).orElseThrow(() -> new EmployeeException("TEAMS.SERVICE_NOT_FOUND"));

		team.setDepartment(department);
		teamsRepo.save(team);
		teams.add(team);

		department.setTeams(teams);

	}

}
