package com.pradeep.Employee_Management.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pradeep.Employee_Management.dto.TeamsDTO;
import com.pradeep.Employee_Management.entity.Department;
import com.pradeep.Employee_Management.entity.Manager;
import com.pradeep.Employee_Management.entity.Teams;
import com.pradeep.Employee_Management.exception.EmployeeException;
import com.pradeep.Employee_Management.repository.DepartmentRepository;
import com.pradeep.Employee_Management.repository.ManagerRepository;
import com.pradeep.Employee_Management.repository.TeamsRepository;
import com.pradeep.Employee_Management.util.TeamsUtil;

@Service
@Transactional
public class TeamsServiceImpl implements TeamsService {
	private TeamsRepository teamsRepo;
	private TeamsUtil teamsUtil;
	private ManagerRepository managerRepo;
	private DepartmentRepository departmentRepo;

	public TeamsServiceImpl(TeamsRepository teamsRepo, TeamsUtil teamsUtil, ManagerRepository managerRepo,
			DepartmentRepository departmentRepo) {
		super();
		this.teamsRepo = teamsRepo;
		this.teamsUtil = teamsUtil;
		this.managerRepo = managerRepo;
		this.departmentRepo = departmentRepo;
	}

	@Override
	public Integer createTeams(TeamsDTO dto) throws EmployeeException {
		// TODO Auto-generated method stub
		Teams teams = new Teams();
		teams = TeamsUtil.toEntity(dto);
		teamsRepo.save(teams);
		return teams.getTeamsId();
	}

	@Override
	public TeamsDTO getTeamsById(Integer teamsId) throws EmployeeException {
		Teams teams = teamsRepo.findById(teamsId).orElseThrow(() -> new EmployeeException("TEAMS.NOT_FOUND"));

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

		Teams existingTeams = teamsRepo.findById(teamsId).orElseThrow(() -> new EmployeeException("TEAMS.NOT_FOUND"));

		existingTeams.setTeamName(dto.getTeamName());

		if (dto.getDepartment() != null && dto.getDepartment().getDepartmentId() != null) {

			Department department = departmentRepo.findById(dto.getDepartment().getDepartmentId())
					.orElseThrow(() -> new EmployeeException("DEPARTMENT.NOT_FOUND"));

			existingTeams.setDepartment(department);
		}

		if (dto.getManager() != null && dto.getManager().getManagerId() != null) {

			Manager manager = managerRepo.findById(dto.getManager().getManagerId())
					.orElseThrow(() -> new EmployeeException("MANAGER.NOT_FOUND"));

			existingTeams.setManager(manager);
		}

		teamsRepo.save(existingTeams);
	}

	@Override
	public void deleteTeams(Integer teamsId) throws EmployeeException {
		// TODO Auto-generated method stub

		Teams teams = teamsRepo.findById(teamsId).orElseThrow(() -> new EmployeeException("TEAMS.NOT_FOUND"));

		teamsRepo.delete(teams);
	}

}
