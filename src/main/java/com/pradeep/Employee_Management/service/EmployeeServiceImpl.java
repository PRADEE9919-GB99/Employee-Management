package com.pradeep.Employee_Management.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pradeep.Employee_Management.dto.EmployeeDTO;
import com.pradeep.Employee_Management.entity.Department;
import com.pradeep.Employee_Management.entity.Employee;
import com.pradeep.Employee_Management.entity.Manager;
import com.pradeep.Employee_Management.entity.Teams;
import com.pradeep.Employee_Management.exception.EmployeeException;
import com.pradeep.Employee_Management.repository.DepartmentRepository;
import com.pradeep.Employee_Management.repository.EmployeeRepository;
import com.pradeep.Employee_Management.repository.ManagerRepository;
import com.pradeep.Employee_Management.repository.TeamsRepository;
import com.pradeep.Employee_Management.util.EmployeeUtil;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

	private EmployeeRepository employeeRepo;
	private DepartmentRepository departmentRepo;
	private ManagerRepository managerRepo;
//	private EmployeeUtil employeeUtil;
	private TeamsRepository teamsRepo;

	public EmployeeServiceImpl(EmployeeRepository employeeRepo, DepartmentRepository departmentRepo,
			ManagerRepository managerRepo, EmployeeUtil employeeUtil, TeamsRepository teamsRepo) {
		super();
		this.employeeRepo = employeeRepo;
		this.departmentRepo = departmentRepo;
		this.managerRepo = managerRepo;
//		this.employeeUtil = employeeUtil;
		this.teamsRepo = teamsRepo;
	}

	@Override
	public Integer createEmployee(EmployeeDTO dto) throws EmployeeException {
		validateEmployee(dto);

		Employee employee = new Employee();

		employee.setEmployeeName(dto.getEmployeeName());
		employee.setEmail(dto.getEmail());
		employee.setGender(dto.getGender());

		Employee savedEmployee = employeeRepo.save(employee);

		return savedEmployee.getEmployeeId();
	}

	@Override
	public EmployeeDTO getEmployeeById(Integer id) throws EmployeeException {
		// TODO Auto-generated method stub

		Employee employee = employeeRepo.findById(id)
				.orElseThrow(() -> new EmployeeException("EMPLOYEE.SERVICE_EMPLOYEE_NOT_FOUND"));

		return EmployeeUtil.toDTO(employee);
	}

	@Override
	public List<EmployeeDTO> getAllEmployees() throws EmployeeException {
		// TODO Auto-generated method stub

		List<Employee> employees = employeeRepo.findAll();
		List<EmployeeDTO> employeesDTO = new ArrayList<>();
		for (Employee employee : employees) {
			EmployeeDTO edto = new EmployeeDTO();
			edto = EmployeeUtil.toDTO(employee);
			employeesDTO.add(edto);
		}

		return employeesDTO;
	}

	@Override
	public EmployeeDTO updateEmployee(Integer id, EmployeeDTO dto) throws EmployeeException {
		// TODO Auto-generated method stub
		Employee employee = employeeRepo.findById(id)
				.orElseThrow(() -> new EmployeeException("EMPLOYEE.SERVICE_EMPLOYEE_NOT_FOUND"));

		if (dto.getEmployeeName() != null && !dto.getEmployeeName().isBlank()) {
			employee.setEmployeeName(dto.getEmployeeName());
		}

		if (dto.getEmail() != null && !dto.getEmail().isBlank()) {
			employee.setEmail(dto.getEmail());
		}

		if (dto.getGender() != null) {
			employee.setGender(dto.getGender());
		}

		Employee updatedEmployee = employeeRepo.save(employee);

		return EmployeeUtil.toDTO(updatedEmployee);
	}

	@Override
	public void deleteEmployee(Integer id) throws EmployeeException {
		// TODO Auto-generated method stub

		Employee employee = employeeRepo.findById(id)
				.orElseThrow(() -> new EmployeeException("EMPLOYEE.SERVICE_EMPLOYEE_NOT_FOUND"));

		employeeRepo.delete(employee);
	}

	@Override
	public List<EmployeeDTO> getEmployeesByDepartment(Long departmentId) throws EmployeeException {
		// TODO Auto-generated method stub
		Department department = departmentRepo.findById(departmentId)
				.orElseThrow(() -> new EmployeeException("DEPARTMENT.SERVICE_DEPARTMENT_NOT_FOUND"));

		List<Employee> employees = employeeRepo.findByDepartment(department.getDepartmentId());
		List<EmployeeDTO> employeesDTO = new ArrayList<>();
		for (Employee employee : employees) {
			EmployeeDTO edto = new EmployeeDTO();
			edto = EmployeeUtil.toDTO(employee);
			employeesDTO.add(edto);
		}

		return employeesDTO;
	}

	@Override
	public List<EmployeeDTO> getEmployeesByManager(Integer managerId) throws EmployeeException {
		// TODO Auto-generated method stub
		managerRepo.findById(managerId).orElseThrow(() -> new EmployeeException("MANAGER.SERVICE_MANAGER_NOT_FOUND"));

		List<Employee> employees = employeeRepo.findByManager(managerId);
		List<EmployeeDTO> employeesDTO = new ArrayList<>();
		for (Employee employee : employees) {
			EmployeeDTO edto = new EmployeeDTO();
			edto = EmployeeUtil.toDTO(employee);
			employeesDTO.add(edto);
		}

		return employeesDTO;

	}

	private void validateEmployee(EmployeeDTO dto) throws EmployeeException {

		if (dto == null) {
			throw new EmployeeException("EMPLOYEE.SERVICE_EMPLOYEE_INVALID");
		}

		if (dto.getEmployeeName() == null || dto.getEmployeeName().isBlank()) {
			throw new EmployeeException("EMPLOYEE.SERVICE_EMPLOYEE_NAME_INVALID");
		}

		if (dto.getEmail() == null || dto.getEmail().isBlank()) {
			throw new EmployeeException("EMPLOYEE.SERVICE_EMPLOYEE_EMAIL_INVALID");
		}

		if (dto.getGender() == null) {
			throw new EmployeeException("EMPLOYEE.SERVICE_EMPLOYEE_DETAILS_INVALID");
		}
	}

	@Override
	public void assignTeam(Integer employeeId, Integer teamId) throws EmployeeException {
		// TODO Auto-generated method stub
		Employee employee = employeeRepo.findById(employeeId)
				.orElseThrow(() -> new EmployeeException("EMPLOYEE.SERVICE_EMPLOYEE_NOT_FOUND"));

		Teams team = teamsRepo.findById(teamId).orElseThrow(() -> new EmployeeException("TEAMS.SERVICE_NOT_FOUND"));

		employee.setTeam(team);

		employeeRepo.save(employee);
	}

	@Override
	public void assignDepartment(Integer employeeId, Long departmentId) throws EmployeeException {
		// TODO Auto-generated method stub
		Employee employee = employeeRepo.findById(employeeId)
				.orElseThrow(() -> new EmployeeException("EMPLOYEE.SERVICE_EMPLOYEE_NOT_FOUND"));

		Department department = departmentRepo.findById(departmentId)
				.orElseThrow(() -> new EmployeeException("DEPARTMENT.SERVICE_DEPARTMENT_NOT_FOUND"));

		employee.setDepartment(department);

		employeeRepo.save(employee);

	}

	@Override
	public void assignManager(Integer employeeId, Integer managerId) throws EmployeeException {
		// TODO Auto-generated method stub
		Employee employee = employeeRepo.findById(employeeId)
				.orElseThrow(() -> new EmployeeException("EMPLOYEE.SERVICE_EMPLOYEE_NOT_FOUND"));

		Manager manager = managerRepo.findById(managerId)
				.orElseThrow(() -> new EmployeeException("MANAGER.SERVICE_MANAGER_NOT_FOUND"));

		employee.setManager(manager);

		employeeRepo.save(employee);

	}

	@Override
	public void unAssignTeam(Integer employeeId) throws EmployeeException {
		// TODO Auto-generated method stub
		Employee employee = employeeRepo.findById(employeeId)
				.orElseThrow(() -> new EmployeeException("EMPLOYEE.SERVICE_EMPLOYEE_NOT_FOUND"));
		employee.setTeam(null);
		employeeRepo.save(employee);
	}

	@Override
	public void unAassignDepartment(Integer employeeId) throws EmployeeException {
		// TODO Auto-generated method stub
		Employee employee = employeeRepo.findById(employeeId)
				.orElseThrow(() -> new EmployeeException("EMPLOYEE.SERVICE_EMPLOYEE_NOT_FOUND"));
		if (employee.getTeam() != null || employee.getManager() != null) {
			throw new EmployeeException("EMPLOYEE.SERVICE_UNASSIGN");
		}
		employee.setDepartment(null);
		employeeRepo.save(employee);
	}

	@Override
	public void unAassignManager(Integer employeeId) throws EmployeeException {
		// TODO Auto-generated method stub
		Employee employee = employeeRepo.findById(employeeId)
				.orElseThrow(() -> new EmployeeException("EMPLOYEE.SERVICE_EMPLOYEE_NOT_FOUND"));
		if (employee.getTeam() != null) {
			throw new EmployeeException("EMPLOYEE.SERVICE_UNASSIGN_TEAM");
		}
		employee.setManager(null);
		employeeRepo.save(employee);
	}

}
