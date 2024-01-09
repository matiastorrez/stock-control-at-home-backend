package com.stockcontrolathome.authentication.controller;

import com.stockcontrolathome.authentication.dto.confirmregistrationtoken.request.NewUserConfirmsRegistration;
import com.stockcontrolathome.authentication.dto.user.request.LoginUserRequest;
import com.stockcontrolathome.authentication.dto.user.request.RegisterUserRequest;
import com.stockcontrolathome.authentication.jwt.dto.JwtResponse;
import com.stockcontrolathome.authentication.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(AuthController.GENERAL_AUTH_PATH)
@CrossOrigin(origins = "*")
public class AuthController {

    public static final String GENERAL_AUTH_PATH = "/auth";
    public static final String REGISTER_PATH = "/register";
    public static final String CONFIRM_REGISTER_PATH = "/confirm-register";
    public static final String LOGIN_USER_PATH = "/login";

    @Autowired
    private AuthService authService;

    @PostMapping(value = AuthController.REGISTER_PATH, consumes = { MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Void> registerUser(@RequestBody @Valid  RegisterUserRequest registerUserRequest) {
        authService.registerUser(registerUserRequest);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping(value = AuthController.CONFIRM_REGISTER_PATH, consumes = { MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<JwtResponse> confirmRegister(@RequestBody @Valid NewUserConfirmsRegistration newUserConfirmsRegistration) {

        return new ResponseEntity<>(this.authService.confirmRegistration(newUserConfirmsRegistration),HttpStatus.OK);
    }

    @PostMapping(value = AuthController.LOGIN_USER_PATH, consumes = { MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<JwtResponse> loginUser(@RequestBody @Valid LoginUserRequest loginUser) {
        return new ResponseEntity<>(this.authService.loginUser(loginUser), HttpStatus.OK);
    }


}
