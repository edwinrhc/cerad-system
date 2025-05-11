package com.cerad.authservice.serviceImpl;


import com.cerad.authservice.constants.AuthConstants;
import com.cerad.authservice.dto.user.CreateUserDTO;
import com.cerad.authservice.dto.user.LoginDTO;
import com.cerad.authservice.dto.user.UserListDTO;
import com.cerad.authservice.entity.User;
import com.cerad.authservice.jwt.CustomerUsersDetailsService;
import com.cerad.authservice.jwt.JwtFilter;
import com.cerad.authservice.jwt.JwtUtil;
import com.cerad.authservice.repository.PasswordResetTokenRepository;
import com.cerad.authservice.repository.UserRepository;
import com.cerad.authservice.service.AuthService;
import com.cerad.authservice.utils.AuthUtils;
import com.common.dto.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Slf4j
@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    CustomerUsersDetailsService customerUsersDetailsService;

    @Autowired
    PasswordResetTokenRepository passwordResetTokenRepository;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private JwtFilter jwtFilter;


    @Override
    public ResponseEntity<ApiResponse> signUp(CreateUserDTO createUserDTO) {
        log.info("createUser : {}", createUserDTO);
        try{
            User validEmail = userRepository.findByEmail(createUserDTO.getEmail());
            if(Objects.isNull(validEmail)){
                User user = modelMapper.map(createUserDTO, User.class);
                user.setRole("user");
                user.setStatus("false");
                user.setPassword(passwordEncoder.encode(createUserDTO.getPassword()));
                User savedUser = userRepository.save(user);

                CreateUserDTO result = modelMapper.map(savedUser, CreateUserDTO.class);
                return AuthUtils.apiResponseEntity(AuthConstants.SUCCESS, result,HttpStatus.CREATED);
            }else{
                return AuthUtils.getApiResponse(AuthConstants.EMAIL_ALREADY_EXISTS, HttpStatus.CONFLICT);
            }
        } catch (Exception e){
            log.error("Error al crear el usuario",e);
            e.printStackTrace();
            return  AuthUtils.getApiResponse(AuthConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @Override
    public ResponseEntity<ApiResponse> login(LoginDTO loginDTO) {
        return null;
    }

    @Override
    public ResponseEntity<ApiResponse> logout(String token) {
        return null;
    }

    @Override
    public ResponseEntity<List<UserListDTO>> listUsers() {
        return null;
    }
}
