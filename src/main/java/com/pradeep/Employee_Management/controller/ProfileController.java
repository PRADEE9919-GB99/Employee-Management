package com.pradeep.Employee_Management.controller;

import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
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
}
