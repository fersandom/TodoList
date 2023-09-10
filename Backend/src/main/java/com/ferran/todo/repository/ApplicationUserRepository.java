package com.ferran.todo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ferran.todo.entity.ApplicationUser;

@Repository
public interface ApplicationUserRepository extends JpaRepository<ApplicationUser,Long> {
	Optional<ApplicationUser> findByUsername(String username);
}
