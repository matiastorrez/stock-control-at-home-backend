package com.stockcontrolathome.authentication.dto.user.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RegisterUserRequest {

    private String name;

    private String lastname;

    private String email;

    private String password;

}
