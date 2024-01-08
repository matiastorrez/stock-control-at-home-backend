package com.stockcontrolathome.authentication.service;

import com.stockcontrolathome.authentication.dto.confirmregistrationtoken.request.NewUserConfirmsRegistration;
import com.stockcontrolathome.authentication.dto.user.request.LoginUserRequest;
import com.stockcontrolathome.authentication.dto.user.request.RegisterUserRequest;
import com.stockcontrolathome.authentication.jwt.dto.JwtResponse;

public interface AuthService {

    void registerUser(RegisterUserRequest registerUserRequest);

    void confirmRegistration(NewUserConfirmsRegistration newUserConfirmsRegistration);

    JwtResponse loginUser(LoginUserRequest user);




}
