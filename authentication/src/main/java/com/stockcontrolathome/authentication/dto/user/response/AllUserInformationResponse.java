package com.stockcontrolathome.authentication.dto.user.response;

import com.stockcontrolathome.authentication.entity.Role;
import com.stockcontrolathome.authentication.enums.UserState;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Set;

@Getter
@AllArgsConstructor
public class AllUserInformationResponse {

    private Long idUser;

    private String name;

    private String lastname;

    private String email;

    private Set<Role> roles;

    private UserState state;


}
