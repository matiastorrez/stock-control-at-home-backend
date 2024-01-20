package com.stockcontrolathome.authentication.mapper;

import com.stockcontrolathome.authentication.audit.passwordrecoverytoken.dto.request.AuditPasswordRecoveryTokenRequest;
import com.stockcontrolathome.authentication.audit.passwordrecoverytoken.enums.EAuditPasswordRecoveryToken;
import com.stockcontrolathome.authentication.dto.passwordrecoverytoken.request.PasswordRecoveryTokenRequest;
import com.stockcontrolathome.authentication.dto.passwordrecoverytoken.response.PasswordRecoveryTokenResponse;
import com.stockcontrolathome.authentication.entity.PasswordRecoveryToken;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public abstract class PasswordRecoveryTokenMapper {

    @Mappings({
            @Mapping(source = "passwordRecoveryTokenRequest.token", target = "token"),
            @Mapping(source = "passwordRecoveryTokenRequest.createdDate", target = "createdDate"),
            @Mapping(source = "passwordRecoveryTokenRequest.expiredDate", target = "expiredDate"),
            @Mapping(source = "passwordRecoveryTokenRequest.state", target = "state"),
            @Mapping(source = "passwordRecoveryTokenRequest.email", target = "email"),
    })
    public abstract PasswordRecoveryToken passwordRecoveryTokenRequestToPasswordRecoveryTokenEntity(PasswordRecoveryTokenRequest passwordRecoveryTokenRequest);

    @Mappings({
            @Mapping(source = "passwordRecoveryToken.idPasswordRecoveryToken", target = "idPasswordRecoveryToken"),
            @Mapping(source = "passwordRecoveryToken.token", target = "token"),
            @Mapping(source = "passwordRecoveryToken.createdDate", target = "createdDate"),
            @Mapping(source = "passwordRecoveryToken.expiredDate", target = "expiredDate"),
            @Mapping(source = "passwordRecoveryToken.state", target = "state"),
            @Mapping(source = "passwordRecoveryToken.email", target = "email"),
    })
    public abstract PasswordRecoveryTokenResponse passwordRecoveryTokenEntityToPasswordRecoveryTokenResponse(PasswordRecoveryToken passwordRecoveryToken);


    @Mappings({
            @Mapping(source = "passwordRecoveryToken.token", target = "token"),
            @Mapping(source = "passwordRecoveryToken.createdDate", target = "createdDate"),
            @Mapping(source = "passwordRecoveryToken.expiredDate", target = "expiredDate"),
            @Mapping(source = "passwordRecoveryToken.email", target = "email"),
            @Mapping(source = "eAuditPasswordRecoveryToken", target = "state"),

    })
    public abstract AuditPasswordRecoveryTokenRequest passwordRecoveryTokenEntityToAuditPasswordRecoveryTokenRequest(PasswordRecoveryToken passwordRecoveryToken, EAuditPasswordRecoveryToken eAuditPasswordRecoveryToken);





}
