package com.stockcontrolathome.authentication.service;

import com.stockcontrolathome.authentication.dto.user.request.ModifyPasswordRequest;
import com.stockcontrolathome.authentication.dto.user.request.RegisterUserRequest;
import com.stockcontrolathome.authentication.dto.user.response.UserInformationResponse;

public interface UserService {

    void registerUser(RegisterUserRequest registerUserRequest);

    void modifyUserToConfirmRegister(String email);
    UserInformationResponse getUserByEmail(String email);

    void modifyPassword(ModifyPasswordRequest modifyPasswordRequest);

}
