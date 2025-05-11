package com.cerad.authservice.rest;


import com.cerad.authservice.dto.user.CreateUserDTO;
import com.cerad.authservice.dto.user.LoginDTO;
import com.cerad.authservice.dto.user.UserListDTO;
import com.common.dto.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/api/user")
public interface AuthRest {

    @PostMapping(path="/signup")
    ResponseEntity<ApiResponse> createUser(@RequestBody @Valid CreateUserDTO dto);

    @PostMapping(path="/login")
    ResponseEntity<ApiResponse> login(@RequestBody @Valid LoginDTO loginDTO);

    @GetMapping("/get")
    ResponseEntity<List<UserListDTO>> getAllUser();


}
