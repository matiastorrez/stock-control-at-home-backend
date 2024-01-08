package com.stockcontrolathome.authentication.dto.user.response;

import com.stockcontrolathome.authentication.dto.role.response.RoleResponse;
import com.stockcontrolathome.authentication.enums.UserState;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@AllArgsConstructor
public class UserInformationResponse {

    private Long idUser;

    private String name;

    private String lastname;

    private String password;

    private String email;

    private Set<RoleResponse> roles;

    private UserState state;

    private LocalDateTime created;

    private LocalDateTime updated;



}
