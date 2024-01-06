package com.stockcontrolathome.authentication.service.impl;

import com.stockcontrolathome.authentication.dto.role.response.RoleResponse;
import com.stockcontrolathome.authentication.entity.Role;
import com.stockcontrolathome.authentication.enums.ERole;
import com.stockcontrolathome.authentication.exception.NonExistentRoleException;
import com.stockcontrolathome.authentication.mapper.RoleMapper;
import com.stockcontrolathome.authentication.repository.role.custom.RoleRepositoryCustom;
import com.stockcontrolathome.authentication.service.RoleService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepositoryCustom roleRepositoryCustom;

    private final RoleMapper roleMapper;

    @Override
    public RoleResponse getRoleByERole(ERole role) {
        Role roleObtained = this.roleRepositoryCustom.getRoleByERole(role)
                .orElseThrow(() -> new NonExistentRoleException("No se encontro el role con nombre: "+ role.toString()));

        return this.roleMapper.roleEntityToRoleResponse(roleObtained);
    }
}
