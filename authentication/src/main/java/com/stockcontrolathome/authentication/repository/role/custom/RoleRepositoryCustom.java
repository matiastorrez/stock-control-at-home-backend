package com.stockcontrolathome.authentication.repository.role.custom;

import com.stockcontrolathome.authentication.entity.Role;
import com.stockcontrolathome.authentication.enums.ERole;

import java.util.Optional;

public interface RoleRepositoryCustom {

    Optional<Role> getRoleByERole(ERole role);
    
}
