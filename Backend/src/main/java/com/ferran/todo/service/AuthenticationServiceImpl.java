package com.ferran.todo.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ferran.todo.entity.ApplicationUser;
import com.ferran.todo.entity.LoginResponseDTO;
import com.ferran.todo.entity.Role;
import com.ferran.todo.repository.ApplicationUserRepository;
import com.ferran.todo.repository.RoleRepository;

@Service
public class AuthenticationServiceImpl implements AuthenticationService{
	
	private final PasswordEncoder encoder;
	private final ApplicationUserRepository applicationUserRepository;
	private final RoleRepository roleRepository;
	private final AuthenticationManager authenticationManager;
	private final TokenService tokenService;
	
	public AuthenticationServiceImpl(PasswordEncoder encoder, ApplicationUserRepository applicationUserRepository, RoleRepository roleRepository, AuthenticationManager authenticationManager, TokenService tokenService) {
		this.encoder = encoder;
		this.applicationUserRepository = applicationUserRepository;
		this.roleRepository = roleRepository;
		this.authenticationManager = authenticationManager;
		this.tokenService = tokenService;
	}
	

	
	@Override
	public ApplicationUser registerUser(String username, String password) {
		String encodedPassword= encoder.encode(password);
		Role userRole = roleRepository.findByAuthority("USER").get();
		
		Set<Role> authorities = new HashSet<>();
		authorities.add(userRole);
		
		return applicationUserRepository.save(new ApplicationUser(username, encodedPassword, authorities));
	}
	
	@Override
	public LoginResponseDTO loginUser(String username, String password) {
		Authentication auth = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		String token = tokenService.generateJwt(auth);
		return new LoginResponseDTO(applicationUserRepository.findByUsername(username).get(), token);
	}
}
