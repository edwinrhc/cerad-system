package com.cerad.authservice.service;

import com.cerad.authservice.dto.user.CreateUserDTO;
import com.cerad.authservice.dto.user.LoginDTO;
import com.cerad.authservice.dto.user.UserListDTO;
import com.common.dto.ApiResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface AuthService {

    ResponseEntity<ApiResponse> signUp(CreateUserDTO createUserDTO);
    ResponseEntity<ApiResponse> login(LoginDTO loginDTO);
    ResponseEntity<ApiResponse> logout(String token);
    ResponseEntity<List<UserListDTO>> listUsers();

}
