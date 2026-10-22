package com.pradeep.Employee_Management.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pradeep.Employee_Management.dto.ManagerDTO;
import com.pradeep.Employee_Management.entity.Employee;
import com.pradeep.Employee_Management.entity.Manager;
import com.pradeep.Employee_Management.entity.Teams;
import com.pradeep.Employee_Management.exception.EmployeeException;
import com.pradeep.Employee_Management.repository.EmployeeRepository;
import com.pradeep.Employee_Management.repository.ManagerRepository;
import com.pradeep.Employee_Management.repository.TeamsRepository;
import com.pradeep.Employee_Management.util.ManagerUtil;

@Service
@Transactional
public class ManagerServiceImpl implements ManagerService {
	private ManagerRepository managerRepo;
//	private ManagerUtil managerUtil;
	private EmployeeRepository employeeRepo;
	private TeamsRepository teamsRepo;

	public ManagerServiceImpl(ManagerRepository managerRepo, ManagerUtil managerUtil, EmployeeRepository employeeRepo,
			TeamsRepository teamsRepo) {
		super();
		this.managerRepo = managerRepo;
//		this.managerUtil = managerUtil;
		this.employeeRepo = employeeRepo;
		this.teamsRepo = teamsRepo;
	}

	@Override
	public Integer createManager(ManagerDTO dto) throws EmployeeException {
		// TODO Auto-generated method stub
		Integer employeeId = dto.getEmployee().getEmployeeId();

		Employee employee = employeeRepo.findById(employeeId)
				.orElseThrow(() -> new EmployeeException("EMPLOYEE.NOT_FOUND"));

		if (managerRepo.existsByEmployee_EmployeeId(employeeId)) {
			throw new EmployeeException("MANAGER.ALREADY_EXISTS");
		}

		Teams team = null;

		if (dto.getTeam() != null && dto.getTeam().getTeamsId() != null) {
			Integer teamId = dto.getTeam().getTeamsId();

			team = teamsRepo.findById(teamId).orElseThrow(() -> new EmployeeException("TEAM.NOT_FOUND"));
		}

		Manager manager = ManagerUtil.toEntity(dto, employee, team);

		return managerRepo.save(manager).getManagerId();
	}

	@Override
	public ManagerDTO getManagerById(Integer id) throws EmployeeException {
		// TODO Auto-generated method stub
		Manager manager = managerRepo.findById(id).orElseThrow(() -> new EmployeeException("MANAGER.NOT_FOUND"));

		return ManagerUtil.toDTO(manager);
	}

	@Override
	public List<ManagerDTO> getAllManagers() throws EmployeeException {
		// TODO Auto-generated method stub
		List<ManagerDTO> managerDTOs = new ArrayList<>();

		for (Manager manager : managerRepo.findAll()) {
			managerDTOs.add(ManagerUtil.toDTO(manager));
		}

		return managerDTOs;
	}

	@Override
	public ManagerDTO updateManager(Integer id, ManagerDTO dto) throws EmployeeException {
		// TODO Auto-generated method stub
		Manager existingManager = managerRepo.findById(id)
				.orElseThrow(() -> new EmployeeException("MANAGER.NOT_FOUND"));

		if (dto.getEmployee() != null && dto.getEmployee().getEmployeeId() != null) {

			Integer employeeId = dto.getEmployee().getEmployeeId();

			Employee employee = employeeRepo.findById(employeeId)
					.orElseThrow(() -> new EmployeeException("EMPLOYEE.NOT_FOUND"));

//			if (managerRepo.existsByEmployee_EmployeeIdAndManagerIdNot(employeeId, id)) {
//				throw new EmployeeException("MANAGER.ALREADY_EXISTS");
//			}

			existingManager.setEmployee(employee);
		}

		if (dto.getTeam() != null && dto.getTeam().getTeamsId() != null) {

			Integer teamId = dto.getTeam().getTeamsId();

			Teams team = teamsRepo.findById(teamId).orElseThrow(() -> new EmployeeException("TEAM.NOT_FOUND"));

			existingManager.setTeam(team);
		}

		return ManagerUtil.toDTO(managerRepo.save(existingManager));

	}

	@Override
	public void deleteManager(Integer id) throws EmployeeException {
		// TODO Auto-generated method stub
		Manager manager = managerRepo.findById(id).orElseThrow(() -> new EmployeeException("MANAGER.NOT_FOUND"));
		manager.setEmployee(null);
		manager.setEmployees(null);
		manager.setProjectsMentored(null);
		manager.setTeam(null);
		managerRepo.deleteById(id);
	}

}
