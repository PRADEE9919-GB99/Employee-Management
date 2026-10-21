package com.pradeep.Employee_Management.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pradeep.Employee_Management.dto.ProjectsDTO;
import com.pradeep.Employee_Management.entity.Employee;
import com.pradeep.Employee_Management.entity.Manager;
import com.pradeep.Employee_Management.entity.Projects;
import com.pradeep.Employee_Management.exception.EmployeeException;
import com.pradeep.Employee_Management.repository.EmployeeRepository;
import com.pradeep.Employee_Management.repository.ManagerRepository;
import com.pradeep.Employee_Management.repository.ProjectsRepository;
import com.pradeep.Employee_Management.util.ProjectsUtil;

@Service
@Transactional
public class ProjectsServiceImpl implements ProjectsService {
	private ProjectsRepository projectsRepo;
	private EmployeeRepository employeeRepo;
	private ManagerRepository managerRepo;

	public ProjectsServiceImpl(ProjectsRepository projectsRepo, EmployeeRepository employeeRepo,
			ManagerRepository managerRepo) {
		super();
		this.projectsRepo = projectsRepo;
		this.employeeRepo = employeeRepo;
		this.managerRepo = managerRepo;
	}

	@Override
	public Integer createProject(ProjectsDTO dto) throws EmployeeException {

		Employee employee = null;
		Manager manager = null;

		if (dto.getEmployee() != null && dto.getEmployee().getEmployeeId() != null) {

			employee = employeeRepo.findById(dto.getEmployee().getEmployeeId())
					.orElseThrow(() -> new EmployeeException("EMPLOYEE.NOT_FOUND"));
		}

		if (dto.getManager() != null && dto.getManager().getManagerId() != null) {

			manager = managerRepo.findById(dto.getManager().getManagerId())
					.orElseThrow(() -> new EmployeeException("MANAGER.NOT_FOUND"));
		}

		Projects project = ProjectsUtil.toEntity(dto, employee, manager);

		// The database generates the project ID.

		Projects savedProject = projectsRepo.save(project);

		return Math.toIntExact(savedProject.getProjectId());
	}

	@Override
	public ProjectsDTO getProjectById(Long id) throws EmployeeException {
		// TODO Auto-generated method stub
		Projects project = projectsRepo.findById(id).orElseThrow(() -> new EmployeeException("PROJECT.NOT_FOUND"));

		return ProjectsUtil.toDTO(project);
	}

	@Override
	public List<ProjectsDTO> getAllProjects() throws EmployeeException {
		// TODO Auto-generated method stub
		List<Projects> projects = projectsRepo.findAll();

		return projects.stream().map(project -> {
			try {
				return ProjectsUtil.toDTO(project);
			} catch (EmployeeException e) {
				throw new RuntimeException(e);
			}
		}).collect(Collectors.toList());
	}

	@Override
	public ProjectsDTO updateProject(Long id, ProjectsDTO dto) throws EmployeeException {
		// TODO Auto-generated method stub
		Projects existingProject = projectsRepo.findById(id)
				.orElseThrow(() -> new EmployeeException("PROJECT.NOT_FOUND"));

		existingProject.setProjectName(dto.getProjectName());
		existingProject.setStatus(dto.getStatus());

		if (dto.getEmployee() != null && dto.getEmployee().getEmployeeId() != null) {

			Employee employee = employeeRepo.findById(dto.getEmployee().getEmployeeId())
					.orElseThrow(() -> new EmployeeException("EMPLOYEE.NOT_FOUND"));

			existingProject.setEmployee(employee);
		}

		if (dto.getManager() != null && dto.getManager().getManagerId() != null) {

			Manager manager = managerRepo.findById(dto.getManager().getManagerId())
					.orElseThrow(() -> new EmployeeException("MANAGER.NOT_FOUND"));

			existingProject.setManager(manager);
		}

		Projects updatedProject = projectsRepo.save(existingProject);

		return ProjectsUtil.toDTO(updatedProject);
	}

	@Override
	public void deleteProject(Long id) throws EmployeeException {
		// TODO Auto-generated method stub
		Projects project = projectsRepo.findById(id).orElseThrow(() -> new EmployeeException("PROJECT.NOT_FOUND"));
		project.setEmployee(null);
		project.setManager(null);
		projectsRepo.deleteById(id);
		;
	}

	@Override
	public List<ProjectsDTO> getProjectsByEmployee(Integer employeeId) throws EmployeeException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ProjectsDTO> getProjectsByManager(Integer managerId) throws EmployeeException {
		// TODO Auto-generated method stub
		return null;
	}

}
