package com.pradeep.Employee_Management.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pradeep.Employee_Management.dto.TeamsDTO;
import com.pradeep.Employee_Management.entity.Teams;
import com.pradeep.Employee_Management.exception.EmployeeException;
import com.pradeep.Employee_Management.repository.TeamsRepository;
import com.pradeep.Employee_Management.util.TeamsUtil;

@Service
@Transactional
public class TeamsServiceImpl implements TeamsService {
	private TeamsRepository teamsRepo;
	private TeamsUtil teamsUtil;

	public TeamsServiceImpl(TeamsRepository teamsRepo, TeamsUtil teamsUtil) {
		super();
		this.teamsRepo = teamsRepo;
		this.teamsUtil = teamsUtil;
	}

	@Override
	public Integer createTeams(TeamsDTO dto) throws EmployeeException {
		// TODO Auto-generated method stub
		Teams teams = new Teams();
		teams = TeamsUtil.toEntity(dto);
		teamsRepo.save(teams);
		return teams.getTeamsId();
	}

}
