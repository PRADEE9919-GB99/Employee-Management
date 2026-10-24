
package com.pradeep.Employee_Management.controller;

import java.util.List;

import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pradeep.Employee_Management.dto.DepartmentDTO;
import com.pradeep.Employee_Management.exception.EmployeeException;
import com.pradeep.Employee_Management.service.DepartmentService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;

@RestController
@RequestMapping("/department")
@Validated
public class DepartmentController {

    private final DepartmentService departmentService;
    private final Environment environment;

    public DepartmentController(DepartmentService departmentService, Environment environment) {
        this.departmentService = departmentService;
        this.environment = environment;
    }

    @PostMapping
    public ResponseEntity<String> createDepartment(
            @Valid @RequestBody DepartmentDTO dto) throws EmployeeException {

        Long departmentId = departmentService.createDepartment(dto);
        String message = environment.getProperty("DEPARTMENT.API.SUCCESS");

        return ResponseEntity.status(HttpStatus.CREATED).body(message + departmentId);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DepartmentDTO> getDepartmentById(
            @PathVariable
            @Positive(message = "{department.id.positive}")
            Long id) throws EmployeeException {

        DepartmentDTO department = departmentService.getDepartmentById(id);
        return ResponseEntity.ok(department);
    }

    @GetMapping
    public ResponseEntity<List<DepartmentDTO>> getAllDepartments() throws EmployeeException {

        List<DepartmentDTO> departments = departmentService.getAllDepartments();
        return ResponseEntity.ok(departments);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateDepartment(
            @PathVariable
            @Positive(message = "{department.id.positive}")
            Long id,
            @Valid @RequestBody DepartmentDTO dto) throws EmployeeException {

        departmentService.updateDepartment(id, dto);
        String message = environment.getProperty("DEPARTMENT.API.UPDATE_SUCCESS");

        return ResponseEntity.ok(message);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDepartment(
            @PathVariable
            @Positive(message = "{department.id.positive}")
            Long id) throws EmployeeException {

        departmentService.deleteDepartment(id);
        String message = environment.getProperty("DEPARTMENT.API.DELETE_SUCCESS");

        return ResponseEntity.ok(message);
    }

    @PutMapping("/{id}/teams/{teamsId}")
    public ResponseEntity<String> assignTeamToDepartment(
            @PathVariable
            @Positive(message = "{department.id.positive}")
            Long id,
            @PathVariable
            @Positive(message = "{teams.id.positive}")
            Integer teamsId) throws EmployeeException {

        departmentService.assignTeamToDepartment(id, teamsId);
        String message = environment.getProperty("DEPARTMENT.API.ASSIGN_TEAMS_SUCCESS");

        return ResponseEntity.ok(message);
    }
}
