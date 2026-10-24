
package com.pradeep.Employee_Management.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pradeep.Employee_Management.dto.ManagerDTO;
import com.pradeep.Employee_Management.entity.Employee;
import com.pradeep.Employee_Management.entity.Manager;
import com.pradeep.Employee_Management.entity.Projects;
import com.pradeep.Employee_Management.entity.Teams;
import com.pradeep.Employee_Management.exception.EmployeeException;
import com.pradeep.Employee_Management.repository.EmployeeRepository;
import com.pradeep.Employee_Management.repository.ManagerRepository;
import com.pradeep.Employee_Management.repository.ProjectsRepository;
import com.pradeep.Employee_Management.repository.TeamsRepository;
import com.pradeep.Employee_Management.util.ManagerUtil;

@Service
@Transactional
public class ManagerServiceImpl implements ManagerService {

	private final ManagerRepository managerRepo;
	private final EmployeeRepository employeeRepo;
	private final TeamsRepository teamsRepo;
	private final ProjectsRepository projectsRepo;

	public ManagerServiceImpl(ManagerRepository managerRepo, EmployeeRepository employeeRepo, TeamsRepository teamsRepo,
			ProjectsRepository projectsRepo) {

		this.managerRepo = managerRepo;
		this.employeeRepo = employeeRepo;
		this.teamsRepo = teamsRepo;
		this.projectsRepo = projectsRepo;
	}

	@Override
	public Integer createManager(ManagerDTO dto) throws EmployeeException {

		if (dto == null || dto.getEmployee() == null || dto.getEmployee().getEmployeeId() == null) {
			throw new EmployeeException("EMPLOYEE.SERVICE_EMPLOYEE_NOT_FOUND");
		}

		Integer employeeId = dto.getEmployee().getEmployeeId();

		Employee employee = employeeRepo.findById(employeeId)
				.orElseThrow(() -> new EmployeeException("EMPLOYEE.SERVICE_EMPLOYEE_NOT_FOUND"));

		if (managerRepo.existsByEmployee_EmployeeId(employeeId)) {
			throw new EmployeeException("MANAGER.ALREADY_EXISTS");
		}

		List<Teams> teams = new ArrayList<>();

		if (dto.getTeams() != null) {
			for (var teamDTO : dto.getTeams()) {
				if (teamDTO != null && teamDTO.getTeamsId() != null) {

					Teams team = teamsRepo.findById(teamDTO.getTeamsId())
							.orElseThrow(() -> new EmployeeException("TEAMS.SERVICE_NOT_FOUND"));

					teams.add(team);
				}
			}
		}

		Manager manager = ManagerUtil.toEntity(dto, employee, teams);

		return managerRepo.save(manager).getManagerId();
	}

	@Override
	@Transactional(readOnly = true)
	public ManagerDTO getManagerById(Integer id) throws EmployeeException {

		Manager manager = managerRepo.findById(id).orElseThrow(() -> new EmployeeException("MANAGER.SERVICE_MANAGER_NOT_FOUND"));

		return ManagerUtil.toDTO(manager);
	}

	@Override
	@Transactional(readOnly = true)
	public List<ManagerDTO> getAllManagers() throws EmployeeException {

		List<ManagerDTO> managerDTOs = new ArrayList<>();

		for (Manager manager : managerRepo.findAll()) {
			managerDTOs.add(ManagerUtil.toDTO(manager));
		}

		return managerDTOs;
	}

	@Override
	public ManagerDTO updateManager(Integer id, ManagerDTO dto) throws EmployeeException {

		if (dto == null) {
			throw new EmployeeException("GENERAL.ITEM_NOT_NULL");
		}

		Manager existingManager = managerRepo.findById(id)
				.orElseThrow(() -> new EmployeeException("MANAGER.SERVICE_MANAGER_NOT_FOUND"));

		if (dto.getEmployee() != null && dto.getEmployee().getEmployeeId() != null) {

			Integer employeeId = dto.getEmployee().getEmployeeId();

			Employee employee = employeeRepo.findById(employeeId)
					.orElseThrow(() -> new EmployeeException("EMPLOYEE.SERVICE_EMPLOYEE_NOT_FOUND"));

			if (managerRepo.existsByEmployee_EmployeeId(employeeId)
					&& !existingManager.getEmployee().getEmployeeId().equals(employeeId)) {
				throw new EmployeeException("MANAGER.ALREADY_EXISTS");
			}

			existingManager.setEmployee(employee);
		}

		if (dto.getTeams() != null) {

			List<Teams> teams = new ArrayList<>();

			for (var teamDTO : dto.getTeams()) {
				if (teamDTO != null && teamDTO.getTeamsId() != null) {

					Teams team = teamsRepo.findById(teamDTO.getTeamsId())
							.orElseThrow(() -> new EmployeeException("TEAMS.SERVICE_NOT_FOUND"));

					teams.add(team);
				}
			}

			existingManager.setTeams(teams);
		}

		Manager savedManager = managerRepo.save(existingManager);

		return ManagerUtil.toDTO(savedManager);
	}

	@Override
	public void deleteManager(Integer id) throws EmployeeException {

		Manager manager = managerRepo.findById(id).orElseThrow(() -> new EmployeeException("MANAGER.SERVICE_MANAGER_NOT_FOUND"));

		// Remove relationships from the owning side where applicable.
		if (manager.getEmployees() != null) {
			for (Employee employee : manager.getEmployees()) {
				employee.setManager(null);
			}
			employeeRepo.saveAll(manager.getEmployees());
		}

		if (manager.getProjectsMentored() != null) {
			for (Projects project : manager.getProjectsMentored()) {
				project.setManager(null);
			}
			projectsRepo.saveAll(manager.getProjectsMentored());
		}

		// Teams own the manager relationship through Teams.manager.
		if (manager.getTeams() != null) {
			for (Teams team : manager.getTeams()) {
				team.setManager(null);
			}
			teamsRepo.saveAll(manager.getTeams());
		}

		manager.setEmployee(null);
		manager.setEmployees(new ArrayList<>());
		manager.setProjectsMentored(new ArrayList<>());
		manager.setTeams(new ArrayList<>());

		managerRepo.delete(manager);
	}

	@Override
	public void assignEmployee(Integer id, Integer employeeId) throws EmployeeException {

		Manager manager = managerRepo.findById(id)
				.orElseThrow(() -> new EmployeeException("MANAGER.SERVICE_MANAGER_NOT_FOUND"));

		Employee employee = employeeRepo.findById(employeeId)
				.orElseThrow(() -> new EmployeeException("EMPLOYEE.SERVICE_EMPLOYEE_NOT_FOUND"));

		employee.setManager(manager);

		employeeRepo.save(employee);
	}

	@Override
	public void assignProjects(Integer id, Integer projectId) throws EmployeeException {

		Manager manager = managerRepo.findById(id)
				.orElseThrow(() -> new EmployeeException("MANAGER.SERVICE_MANAGER_NOT_FOUND"));

		Projects project = projectsRepo.findById(projectId.longValue())
				.orElseThrow(() -> new EmployeeException("PROJECT.NOT_FOUND"));

		project.setManager(manager);

		projectsRepo.save(project);
	}

	
}