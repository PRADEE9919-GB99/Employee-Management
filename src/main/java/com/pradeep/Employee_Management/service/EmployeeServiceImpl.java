package com.pradeep.Employee_Management.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pradeep.Employee_Management.dto.EmployeeDTO;
import com.pradeep.Employee_Management.entity.Employee;
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
	private EmployeeUtil employeeUtil;
	private TeamsRepository teamsRepo;

	public EmployeeServiceImpl(EmployeeRepository employeeRepo, DepartmentRepository departmentRepo,
			ManagerRepository managerRepo, EmployeeUtil employeeUtil, TeamsRepository teamsRepo) {
		super();
		this.employeeRepo = employeeRepo;
		this.departmentRepo = departmentRepo;
		this.managerRepo = managerRepo;
		this.employeeUtil = employeeUtil;
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

		return employeeUtil.toDTO(employee);
	}

	@Override
	public List<EmployeeDTO> getAllEmployees() throws EmployeeException {
		// TODO Auto-generated method stub

		List<Employee> employees = employeeRepo.findAll();
		List<EmployeeDTO> employeesDTO = new ArrayList<>();
		for (Employee employee : employees) {
			EmployeeDTO edto = new EmployeeDTO();
			edto = employeeUtil.toDTO(employee);
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

		return employeeUtil.toDTO(updatedEmployee);
	}

	@Override
	public void deleteEmployee(Integer id) throws EmployeeException {
		// TODO Auto-generated method stub

	}

	@Override
	public List<EmployeeDTO> getEmployeesByDepartment(Long departmentId) throws EmployeeException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<EmployeeDTO> getEmployeesByManager(Integer managerId) throws EmployeeException {
		// TODO Auto-generated method stub
		return null;
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

	}

	@Override
	public void assignDepartment(Integer employeeId, Long departmentId) throws EmployeeException {
		// TODO Auto-generated method stub

	}

	@Override
	public void assignManager(Integer employeeId, Integer managerId) throws EmployeeException {
		// TODO Auto-generated method stub

	}

}
