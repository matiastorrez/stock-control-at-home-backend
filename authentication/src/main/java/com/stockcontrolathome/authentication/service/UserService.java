package com.stockcontrolathome.authentication.service;

import com.stockcontrolathome.authentication.dto.user.request.RegisterUserRequest;

public interface UserService {

    void registerUser(RegisterUserRequest registerUserRequest);

    void modifyUserToConfirmRegister(String email);
}
