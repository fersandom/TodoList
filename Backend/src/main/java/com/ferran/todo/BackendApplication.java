package com.ferran.todo;

import java.util.HashSet;
import java.util.Set;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.ferran.todo.entity.ApplicationUser;
import com.ferran.todo.entity.Role;
import com.ferran.todo.repository.ApplicationUserRepository;
import com.ferran.todo.repository.RoleRepository;

@SpringBootApplication
public class BackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}
	@Bean
	CommandLineRunner run(RoleRepository roleRepository, ApplicationUserRepository applicationUserRepository, PasswordEncoder passwordEncoder) {
		return args ->{
			
			if(roleRepository.findByAuthority("ADMIN").isPresent()) return;
			
			Role adminRole= roleRepository.save(new Role("ADMIN"));
			roleRepository.save(new Role("USER"));
			
			Set<Role> roles = new HashSet<>();
			roles.add(adminRole);
			
			ApplicationUser admin = new ApplicationUser("admin", passwordEncoder.encode("password"), roles);
			applicationUserRepository.save(admin);
		};
	}

}
