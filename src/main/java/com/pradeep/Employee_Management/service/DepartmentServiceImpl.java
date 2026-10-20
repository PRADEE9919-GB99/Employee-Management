package com.pradeep.Employee_Management.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pradeep.Employee_Management.dto.DepartmentDTO;
import com.pradeep.Employee_Management.entity.Department;
import com.pradeep.Employee_Management.exception.EmployeeException;
import com.pradeep.Employee_Management.repository.DepartmentRepository;
import com.pradeep.Employee_Management.util.DepartmentUtil;

@Service
@Transactional
public class DepartmentServiceImpl implements DepartmentService {
	@Autowired
	private DepartmentUtil departmentUtil;

	@Autowired
	private DepartmentRepository departmentRepo;

	@Override
	public Long createDepartment(DepartmentDTO dto) throws EmployeeException {
		Department department = departmentUtil.toEntity(dto);
		departmentRepo.save(department);

		return department.getDepartmentId();
	}

	@Override
	public DepartmentDTO getDepartmentById(Long id) throws EmployeeException {
		if (id == null || id <= 0) {
			throw new EmployeeException("GENERAL.ITEM_NOT_NULL");
		}

		Department department = departmentRepo.findById(id)
				.orElseThrow(() -> new EmployeeException("DEPARTMENT.SERVICE_DEPARTMENT_NOT_FOUND"));

		return departmentUtil.toDTO(department);
	}

	@Override
	public List<DepartmentDTO> getAllDepartments() throws EmployeeException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DepartmentDTO updateDepartment(Long id, DepartmentDTO dto) throws EmployeeException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteDepartment(Long id) throws EmployeeException {
		// TODO Auto-generated method stub

	}

}
