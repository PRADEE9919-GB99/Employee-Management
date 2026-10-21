package com.pradeep.Employee_Management.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pradeep.Employee_Management.dto.EmployeeDTO;
import com.pradeep.Employee_Management.entity.Department;
import com.pradeep.Employee_Management.entity.Employee;
import com.pradeep.Employee_Management.entity.Manager;
import com.pradeep.Employee_Management.exception.EmployeeException;
import com.pradeep.Employee_Management.repository.DepartmentRepository;
import com.pradeep.Employee_Management.repository.EmployeeRepository;
import com.pradeep.Employee_Management.repository.ManagerRepository;
import com.pradeep.Employee_Management.util.EmployeeUtil;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

	private EmployeeRepository employeeRepo;
	private DepartmentRepository departmentRepo;
	private ManagerRepository managerRepo;
	private EmployeeUtil employeeUtil;

	public EmployeeServiceImpl(EmployeeRepository employeeRepo, DepartmentRepository departmentRepo,
			ManagerRepository managerRepo, EmployeeUtil employeeUtil) {
		super();
		this.employeeRepo = employeeRepo;
		this.departmentRepo = departmentRepo;
		this.managerRepo = managerRepo;
		this.employeeUtil = employeeUtil;
	}

	@Override
	public Integer createEmployee(EmployeeDTO dto) throws EmployeeException {
		validateEmployee(dto);

		if (employeeRepo.existsByEmail(dto.getEmail().trim())) {
			throw new EmployeeException("EMPLOYEE.SERVICE_EMPLOYEE_EMAIL_EXISTS");
		}

		Employee employee = employeeUtil.toEntity(dto);

		if (dto.getDepartment() != null) {
			Long departmentId = dto.getDepartment().getDepartmentId();

			if (departmentId == null || departmentId <= 0) {
				throw new EmployeeException("DEPARTMENT.SERVICE_INVALID_DEPARTMENT_ID");
			}

			Department department = departmentRepo.findById(departmentId)
					.orElseThrow(() -> new EmployeeException("DEPARTMENT.SERVICE_DEPARTMENT_NOT_FOUND"));

			employee.setDepartment(department);
		}
//
//		if (dto.getManager() != null) {
//			Integer managerId = dto.getManager().getManagerId();
//
//			if (managerId == null || managerId <= 0) {
//				throw new EmployeeException("MANAGER.SERVICE_INVALID_MANAGER_ID");
//			}
//
//			Manager manager = managerRepo.findById(managerId)
//					.orElseThrow(() -> new EmployeeException("MANAGER.SERVICE_MANAGER_NOT_FOUND"));
//
//			employee.setManager(manager);
//		}

		employee.setEmployeeId(null);

		Employee savedEmployee = employeeRepo.save(employee);

		return savedEmployee.getEmployeeId();
	}

	@Override
	public EmployeeDTO getEmployeeById(Integer id) throws EmployeeException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<EmployeeDTO> getAllEmployees() throws EmployeeException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EmployeeDTO updateEmployee(Integer id, EmployeeDTO dto) throws EmployeeException {
		// TODO Auto-generated method stub
		return null;
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

		if (dto.getGender() == null || dto.getTeam() == null) {
			throw new EmployeeException("EMPLOYEE.SERVICE_EMPLOYEE_DETAILS_INVALID");
		}
	}

}
