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

	public void asssignManager(Integer teamsId, Integer managerId) throws EmployeeException;

	public void asssignDepartment(Integer teamsId, Long departmentId) throws EmployeeException;

	void changeTeamManager(Integer teamId, Integer newManagerId);

//	void assignManagerToTeam(Integer teamId, Integer managerId) throws EmployeeException;

	void assignEmployeeToTeam(Integer teamId, Integer employeeId) throws EmployeeException;

//	public void asssignManager(Integer teamsId) throws EmployeeException;
//	public void asssignDepartment(Integer teamsId) throws EmployeeException;

}
