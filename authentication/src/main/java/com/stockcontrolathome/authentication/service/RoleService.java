package com.stockcontrolathome.authentication.service;

import com.stockcontrolathome.authentication.dto.role.response.RoleResponse;
import com.stockcontrolathome.authentication.enums.ERole;

public interface RoleService {


    RoleResponse getRoleByERole(ERole role);


}
