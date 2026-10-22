package com.pradeep.Employee_Management.service;

import java.util.List;

import com.pradeep.Employee_Management.dto.TeamsDTO;
import com.pradeep.Employee_Management.exception.EmployeeException;

public interface TeamsService {
	public Integer createTeams(TeamsDTO dto) throws EmployeeException;

	public TeamsDTO getTeamsById(Integer teamsId) throws EmployeeException;

	public List<TeamsDTO> getAllTeams() throws EmployeeException;

	public void updateTeams(Integer teamsId, TeamsDTO dto) throws EmployeeException;

	public void deleteTeams(Integer teamsId) throws EmployeeException;
}
