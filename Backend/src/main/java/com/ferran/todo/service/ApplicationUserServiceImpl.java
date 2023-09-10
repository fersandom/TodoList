package com.ferran.todo.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ferran.todo.repository.ApplicationUserRepository;


@Service
public class ApplicationUserServiceImpl implements UserDetailsService, ApplicationUserService{

	private final ApplicationUserRepository applicationUserRepository;

	public ApplicationUserServiceImpl(ApplicationUserRepository applicationUserRepository) {
		this.applicationUserRepository = applicationUserRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) {
		return applicationUserRepository.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("User was not found"));
	}
}
