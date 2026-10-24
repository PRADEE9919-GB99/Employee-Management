package com.pradeep.Employee_Management.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
import com.pradeep.Employee_Management.util.TeamsUtil;

@Service
@Transactional
public class TeamsServiceImpl implements TeamsService {
	private TeamsRepository teamsRepo;
//	private TeamsUtil teamsUtil;
	private ManagerRepository managerRepo;
	private DepartmentRepository departmentRepo;
	private EmployeeRepository employeeRepo;

	public TeamsServiceImpl(TeamsRepository teamsRepo, TeamsUtil teamsUtil, ManagerRepository managerRepo,
			DepartmentRepository departmentRepo, EmployeeRepository employeeRepo) {
		super();
		this.teamsRepo = teamsRepo;
//		this.teamsUtil = teamsUtil;
		this.managerRepo = managerRepo;
		this.departmentRepo = departmentRepo;
		this.employeeRepo = employeeRepo;
	}

	@Override
	public Integer createTeams(TeamsDTO dto) throws EmployeeException {
		// TODO Auto-generated method stub
		if (dto.getManagerResponseDTO() == null || dto.getManagerResponseDTO().getManagerId() == null) {
			throw new EmployeeException("MANAGER.SERVICE_MANAGER_NOT_FOUND");
		}

		if (dto.getDepartment() == null || dto.getDepartment().getDepartmentId() == null) {
			throw new EmployeeException("DEPARTMENT.SERVICE_DEPARTMENT_NOT_FOUND");
		}

		Manager manager = managerRepo.findById(dto.getManagerResponseDTO().getManagerId())
				.orElseThrow(() -> new EmployeeException("MANAGER.SERVICE_MANAGER_NOT_FOUND"));

		Department department = departmentRepo.findById(dto.getDepartment().getDepartmentId())
				.orElseThrow(() -> new EmployeeException("DEPARTMENT.SERVICE_DEPARTMENT_NOT_FOUND"));

		Teams team = new Teams();
		team.setTeamName(dto.getTeamName());
		team.setManager(manager);
		team.setDepartment(department);

		return teamsRepo.save(team).getTeamsId();
	}

	@Override
	public TeamsDTO getTeamsById(Integer teamsId) throws EmployeeException {
		Teams teams = teamsRepo.findById(teamsId).orElseThrow(() -> new EmployeeException("TEAMS.SERVICE_NOT_FOUND"));

		return TeamsUtil.toDTO(teams);
	}

	@Override
	public List<TeamsDTO> getAllTeams() throws EmployeeException {
		// TODO Auto-generated method stub
		List<TeamsDTO> teamsList = new java.util.ArrayList<>();

		for (Teams teams : teamsRepo.findAll()) {
			teamsList.add(TeamsUtil.toDTO(teams));
		}

		return teamsList;
	}

	@Override
	public void updateTeams(Integer teamsId, TeamsDTO dto) throws EmployeeException {
		// TODO Auto-generated method stub

		Teams existingTeams = teamsRepo.findById(teamsId)
				.orElseThrow(() -> new EmployeeException("TEAMS.SERVICE_NOT_FOUND"));

		existingTeams.setTeamName(dto.getTeamName());

		if (dto.getDepartment() != null && dto.getDepartment().getDepartmentId() != null) {

			Department department = departmentRepo.findById(dto.getDepartment().getDepartmentId())
					.orElseThrow(() -> new EmployeeException("DEPARTMENT.SERVICE_DEPARTMENT_NOT_FOUND"));

			existingTeams.setDepartment(department);
		}

		if (dto.getManagerResponseDTO() != null && dto.getManagerResponseDTO().getManagerId() != null) {

			Manager manager = managerRepo.findById(dto.getManagerResponseDTO().getManagerId())
					.orElseThrow(() -> new EmployeeException("MANAGER.SERVICE_MANAGER_NOT_FOUND"));

			existingTeams.setManager(manager);
		}

		teamsRepo.save(existingTeams);
	}

	@Override
	public void deleteTeams(Integer teamsId) throws EmployeeException {
		// TODO Auto-generated method stub

		Teams teams = teamsRepo.findById(teamsId).orElseThrow(() -> new EmployeeException("TEAMS.SERVICE_NOT_FOUND"));

		teamsRepo.delete(teams);
	}

	@Override
	public void asssignManager(Integer teamsId, Integer managerId) throws EmployeeException {
		// TODO Auto-generated method stub
		Teams team = teamsRepo.findById(teamsId).orElseThrow(() -> new EmployeeException("TEAMS.SERVICE_NOT_FOUND"));

		Manager manager = managerRepo.findById(managerId)
				.orElseThrow(() -> new EmployeeException("MANAGER.SERVICE_MANAGER_NOT_FOUND"));

		team.setManager(manager);

		teamsRepo.save(team);
	}

	@Override
	public void asssignDepartment(Integer teamsId, Long departmentId) throws EmployeeException {
		// TODO Auto-generated method stub
		Teams team = teamsRepo.findById(teamsId).orElseThrow(() -> new EmployeeException("TEAMS.SERVICE_NOT_FOUND"));

		Department department = departmentRepo.findById(departmentId)
				.orElseThrow(() -> new EmployeeException("DEPARTMENT.SERVICE_DEPARTMENT_NOT_FOUND"));

		team.setDepartment(department);

		teamsRepo.save(team);
	}

	@Override
	public void changeTeamManager(Integer teamId, Integer newManagerId) {
		// TODO Auto-generated method stub

	}

	@Override
	public void assignEmployeeToTeam(Integer teamId, Integer employeeId) throws EmployeeException {
		// TODO Auto-generated method stub
		Teams team = teamsRepo.findById(teamId).orElseThrow(() -> new EmployeeException("TEAMS.SERVICE_NOT_FOUND"));

		Employee employee = employeeRepo.findById(employeeId)
				.orElseThrow(() -> new EmployeeException("SERVICE.EMPLOYEE_NOT_FOUND"));

		employee.setTeam(team);

		employeeRepo.save(employee);
	}

}
