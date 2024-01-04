package com.stockcontrolathome.authentication.service.impl;

import com.stockcontrolathome.authentication.dto.user.request.RegisterUserRequest;
import com.stockcontrolathome.authentication.service.AuthService;
import com.stockcontrolathome.authentication.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserService userService;

    @Override
    public void registerUser(RegisterUserRequest registerUserRequest) {
        this.userService.registerUser(registerUserRequest);
    }
}
