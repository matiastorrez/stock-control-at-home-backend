package com.stockcontrolathome.authentication.controller;

import com.stockcontrolathome.authentication.dto.user.request.RegisterUserRequest;
import com.stockcontrolathome.authentication.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(AuthController.GENERAL_AUTH_PATH)
public class AuthController {

    public static final String GENERAL_AUTH_PATH = "/auth";
    public static final String REGISTER_PATH = "/register";

    @Autowired
    private AuthService authService;

    @PostMapping(value = AuthController.REGISTER_PATH, consumes = { MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Void> registerUser(@RequestBody @Valid  RegisterUserRequest registerUserRequest) {
        authService.registerUser(registerUserRequest);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
