package com.stockcontrolathome.authentication.repository.role.custom.impl;

import com.stockcontrolathome.authentication.entity.Role;
import com.stockcontrolathome.authentication.enums.ERole;
import com.stockcontrolathome.authentication.repository.role.RoleRepository;
import com.stockcontrolathome.authentication.repository.role.custom.RoleRepositoryCustom;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@AllArgsConstructor
public class RoleRepositoryCustomImpl implements RoleRepositoryCustom {

    private final RoleRepository roleRepository;

    @Override
    public Optional<Role> getRoleByERole(ERole role) {
        return roleRepository.findByeRole(role);
    }

}
