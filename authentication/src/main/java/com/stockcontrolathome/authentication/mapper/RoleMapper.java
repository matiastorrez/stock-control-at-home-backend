package com.stockcontrolathome.authentication.mapper;

import com.stockcontrolathome.authentication.dto.role.response.RoleResponse;
import com.stockcontrolathome.authentication.entity.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public abstract class RoleMapper {

    @Mappings({
            @Mapping(source = "role.roleId", target = "roleId"),
            @Mapping(source = "role.ERole", target = "roleName"),
    })
    public abstract RoleResponse roleEntityToRoleResponse(Role role);

    @Mappings({
            @Mapping(source = "roleResponse.roleId", target = "roleId"),
            @Mapping(source = "roleResponse.roleName", target = "ERole"),
            @Mapping(target = "created", ignore = true ),
            @Mapping(target = "updated", ignore = true ),
    })
    public abstract Role roleResponseToRoleEntity(RoleResponse roleResponse);




}
