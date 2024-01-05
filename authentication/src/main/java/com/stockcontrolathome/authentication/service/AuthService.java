package com.stockcontrolathome.authentication.service;

import com.stockcontrolathome.authentication.dto.confirmregistrationtoken.request.NewUserConfirmsRegistration;
import com.stockcontrolathome.authentication.dto.user.request.RegisterUserRequest;

public interface AuthService {

    void registerUser(RegisterUserRequest registerUserRequest);

    void confirmRegistration(NewUserConfirmsRegistration newUserConfirmsRegistration);
}
