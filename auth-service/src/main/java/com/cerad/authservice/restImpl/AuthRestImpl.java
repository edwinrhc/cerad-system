package com.cerad.authservice.restImpl;

import com.cerad.authservice.constants.AuthConstants;
import com.cerad.authservice.dto.user.CreateUserDTO;
import com.cerad.authservice.dto.user.LoginDTO;
import com.cerad.authservice.dto.user.UserListDTO;
import com.cerad.authservice.rest.AuthRest;
import com.cerad.authservice.service.AuthService;
import com.cerad.authservice.utils.AuthUtils;
import com.common.dto.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class AuthRestImpl implements AuthRest {

    @Autowired
    AuthService authService;

    @Override
    public ResponseEntity<ApiResponse> createUser(CreateUserDTO dto) {
           return authService.signUp(dto);
    }

    @Override
    public ResponseEntity<ApiResponse> login(LoginDTO loginDTO) {
        return authService.login(loginDTO);
    }

    @Override
    public ResponseEntity<List<UserListDTO>> getAllUser() {
        try{
            return authService.listUsers();
        }catch(Exception ex){
            ex.printStackTrace();
            return new ResponseEntity<List<UserListDTO>>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
