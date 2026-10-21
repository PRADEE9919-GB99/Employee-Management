package com.pradeep.Employee_Management.controller;

import java.util.List;

import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

@RestController
@RequestMapping("/profile")
public class ProfileController {

	private ProfileService profileService;
	private Environment environment;

	public ProfileController(ProfileService profileService, Environment environment) {
		this.profileService = profileService;
		this.environment = environment;
	}

	@PostMapping
	public ResponseEntity<String> createProfile(@RequestBody ProfileDTO profileDTO) throws EmployeeException {

		Integer profileId = profileService.createProfile(profileDTO);
		String message = environment.getProperty("PROFILE.API.SUCCESS");
		return new ResponseEntity<String>(message + profileId, HttpStatus.CREATED);
	}

	@GetMapping("/{profileId}")
	public ResponseEntity<ProfileDTO> getProfileById(@PathVariable Integer profileId) throws EmployeeException {

		ProfileDTO profile = profileService.getProfileById(profileId);

		return new ResponseEntity<>(profile, HttpStatus.OK);
	}

	@GetMapping()
	public ResponseEntity<List<ProfileDTO>> getAllProfiles() throws EmployeeException {

		List<ProfileDTO> profiles = profileService.getAllProfiles();

		return new ResponseEntity<>(profiles, HttpStatus.OK);
	}

	@PutMapping("/{profileId}")
	public ResponseEntity<String> updateProfile(@PathVariable Integer profileId, @RequestBody ProfileDTO dto)
			throws EmployeeException {

		ProfileDTO profile = profileService.updateProfile(profileId, dto);
		String message = environment.getProperty("PROFILE.API.UPDATE_SUCCESS");
		return new ResponseEntity<>(message, HttpStatus.OK);
	}

	@DeleteMapping("/{profileId}")
	public ResponseEntity<String> deleteProfile(@PathVariable Integer profileId) throws EmployeeException {

		profileService.deleteProfile(profileId);
		String message = environment.getProperty("PROFILE.API.DELETE_SUCCESS");

		return ResponseEntity.status(HttpStatus.OK).body(message);
	}

	@GetMapping("/employee/{employeeId}")
	public ResponseEntity<ProfileDTO> getProfileByEmployeeId(@PathVariable Integer employeeId)
			throws EmployeeException {

		ProfileDTO profile = profileService.getProfileByEmployeeId(employeeId);

		return new ResponseEntity<>(profile, HttpStatus.OK);
	}

}
