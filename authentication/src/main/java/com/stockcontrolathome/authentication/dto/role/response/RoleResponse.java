package com.stockcontrolathome.authentication.dto.role.response;

import com.stockcontrolathome.authentication.enums.ERole;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RoleResponse {

    private Long roleId;
    private ERole roleName;


}
