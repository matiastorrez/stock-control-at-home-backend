package com.stockcontrolathome.authentication.mapper;

import com.stockcontrolathome.authentication.dto.user.request.RegisterUserRequest;
import com.stockcontrolathome.authentication.dto.user.response.AllUserInformationResponse;
import com.stockcontrolathome.authentication.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
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




}
