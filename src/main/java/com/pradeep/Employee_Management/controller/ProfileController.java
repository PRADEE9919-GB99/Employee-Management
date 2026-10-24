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

import com.pradeep.Employee_Management.dto.ProfileDTO;
import com.pradeep.Employee_Management.exception.EmployeeException;
import com.pradeep.Employee_Management.service.ProfileService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;

@RestController
@RequestMapping("/profile")
@Validated
public class ProfileController {

    private final ProfileService profileService;
    private final Environment environment;

    public ProfileController(ProfileService profileService, Environment environment) {
        this.profileService = profileService;
        this.environment = environment;
    }

    @PostMapping
    public ResponseEntity<String> createProfile(
            @Valid @RequestBody ProfileDTO profileDTO) throws EmployeeException {

        Integer profileId = profileService.createProfile(profileDTO);
        String message = environment.getProperty("PROFILE.API.SUCCESS");

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(message + profileId);
    }

    @GetMapping("/{profileId}")
    public ResponseEntity<ProfileDTO> getProfileById(
            @PathVariable @Positive(message = "{profile.id.positive}") Integer profileId)
            throws EmployeeException {

        ProfileDTO profile = profileService.getProfileById(profileId);
        return ResponseEntity.ok(profile);
    }

    @GetMapping
    public ResponseEntity<List<ProfileDTO>> getAllProfiles() throws EmployeeException {

        List<ProfileDTO> profiles = profileService.getAllProfiles();
        return ResponseEntity.ok(profiles);
    }

    @PutMapping("/{profileId}")
    public ResponseEntity<String> updateProfile(
            @PathVariable @Positive(message = "{profile.id.positive}") Integer profileId,
            @Valid @RequestBody ProfileDTO dto) throws EmployeeException {

        profileService.updateProfile(profileId, dto);
        String message = environment.getProperty("PROFILE.API.UPDATE_SUCCESS");

        return ResponseEntity.ok(message);
    }

    @DeleteMapping("/{profileId}")
    public ResponseEntity<String> deleteProfile(
            @PathVariable @Positive(message = "{profile.id.positive}") Integer profileId)
            throws EmployeeException {

        profileService.deleteProfile(profileId);
        String message = environment.getProperty("PROFILE.API.DELETE_SUCCESS");

        return ResponseEntity.ok(message);
    }

    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<ProfileDTO> getProfileByEmployeeId(
            @PathVariable @Positive(message = "{employee.id.positive}") Integer employeeId)
            throws EmployeeException {

        ProfileDTO profile = profileService.getProfileByEmployeeId(employeeId);
        return ResponseEntity.ok(profile);
    }
}