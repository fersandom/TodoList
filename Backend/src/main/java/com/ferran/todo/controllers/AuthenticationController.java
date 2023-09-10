package com.ferran.todo.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ferran.todo.entity.ApplicationUser;
import com.ferran.todo.entity.LoginResponseDTO;
import com.ferran.todo.entity.UserRegistrationDTO;
import com.ferran.todo.service.AuthenticationService;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {
	
	private final AuthenticationService authenticationService;
	
	public AuthenticationController(AuthenticationService authenticationService) {
		this.authenticationService = authenticationService;
	}

	@PostMapping("/register")
	public ApplicationUser registerUser(@RequestBody UserRegistrationDTO userRegistrationDTO) {
		return authenticationService.registerUser(userRegistrationDTO.getUsername(), userRegistrationDTO.getPassword());
	}
	
	@PostMapping("/login")
	public LoginResponseDTO loginUser(@RequestBody UserRegistrationDTO userRegistrationDTO) {
		return authenticationService.loginUser(userRegistrationDTO.getUsername(), userRegistrationDTO.getPassword());
	}
}
