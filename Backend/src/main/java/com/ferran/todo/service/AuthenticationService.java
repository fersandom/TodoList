package com.ferran.todo.service;

import com.ferran.todo.entity.ApplicationUser;
import com.ferran.todo.entity.LoginResponseDTO;

public interface AuthenticationService {

	ApplicationUser registerUser(String username, String password);

	LoginResponseDTO loginUser(String username, String password);

}
