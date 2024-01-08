package com.stockcontrolathome.authentication.mapper;

import com.stockcontrolathome.authentication.dto.user.request.RegisterUserRequest;
import com.stockcontrolathome.authentication.dto.user.response.UserInformationResponse;
import com.stockcontrolathome.authentication.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring", uses = {
        RoleMapper.class
})
public abstract class UserMapper {

    @Mappings({
            @Mapping(source = "registerUserRequest.name", target = "name"),
            @Mapping(source = "registerUserRequest.lastname", target = "lastname"),
            @Mapping(source = "registerUserRequest.email", target = "email"),
            @Mapping(source = "registerUserRequest.password", target = "password"),
            @Mapping(target = "userId", ignore = true ),
            @Mapping(target = "state", ignore = true ),
            @Mapping(target = "roles", ignore = true ),
            @Mapping(target = "created", ignore = true ),
            @Mapping(target = "updated", ignore = true ),
    })
    public abstract User registerUserRequestToUserEntity(RegisterUserRequest registerUserRequest);


    @Mappings({
            @Mapping(source = "user.userId", target = "idUser"),
            @Mapping(source = "user.name", target = "name"),
            @Mapping(source = "user.lastname", target = "lastname"),
            @Mapping(source = "user.password", target = "password"),
            @Mapping(source = "user.email", target = "email"),
            @Mapping(source = "user.roles", target = "roles"),
            @Mapping(source = "user.state", target = "state"),
            @Mapping(source = "user.created", target = "created"),
            @Mapping(source = "user.updated", target = "updated"),
    })
    public abstract UserInformationResponse userEntityToUserInformationResponse(User user);




}
