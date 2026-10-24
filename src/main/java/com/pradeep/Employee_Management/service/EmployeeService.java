package com.pradeep.Employee_Management.service;

import java.util.List;

import com.pradeep.Employee_Management.dto.EmployeeDTO;
import com.pradeep.Employee_Management.exception.EmployeeException;

public interface EmployeeService {
	Integer createEmployee(EmployeeDTO dto) throws EmployeeException;

	EmployeeDTO getEmployeeById(Integer id) throws EmployeeException;

	List<EmployeeDTO> getAllEmployees() throws EmployeeException;

	EmployeeDTO updateEmployee(Integer id, EmployeeDTO dto) throws EmployeeException;

	void deleteEmployee(Integer id) throws EmployeeException;

//	List<EmployeeDTO> getEmployeesByDepartment(Long departmentId) throws EmployeeException;

	List<EmployeeDTO> getEmployeesByManager(Integer managerId) throws EmployeeException;

	void assignTeam(Integer employeeId, Integer teamId) throws EmployeeException;
//
//	void assignDepartment(Integer employeeId, Long departmentId) throws EmployeeException;
//
//	void assignManager(Integer employeeId, Integer managerId) throws EmployeeException;
//
//	void unAssignTeam(Integer employeeId) throws EmployeeException;
//
//	void unAassignDepartment(Integer employeeId) throws EmployeeException;
//
//	void unAassignManager(Integer employeeId) throws EmployeeException;

	void assignReportingManager(Integer employeeId, Integer managerId) throws EmployeeException;

	void removeEmployeeFromTeam(Integer employeeId) throws EmployeeException;

	void changeEmployeeTeam(Integer employeeId, Integer newTeamId) throws EmployeeException;

	void assignProfile(Integer employeeId, Integer profileId) throws EmployeeException;

	void assignAddress(Integer employeeId, Integer addressId) throws EmployeeException;

	void assignProjects(Integer employeeId, Long projectId) throws EmployeeException;

	void changeReportingManager(Integer employeeId, Integer newManagerId) throws EmployeeException;

//	void unAassignProfile(Integer employeeId) throws EmployeeException;
//	void unAassignAddress(Integer employeeId) throws EmployeeException;
//	void unAassignProjects(Integer employeeId) throws EmployeeException;
}
