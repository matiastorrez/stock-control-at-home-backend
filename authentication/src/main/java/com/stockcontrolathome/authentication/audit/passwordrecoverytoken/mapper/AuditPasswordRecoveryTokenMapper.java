package com.stockcontrolathome.authentication.audit.passwordrecoverytoken.mapper;

import com.stockcontrolathome.authentication.audit.generic.mapper.GenericMapper;
import com.stockcontrolathome.authentication.audit.passwordrecoverytoken.dto.request.AuditPasswordRecoveryTokenRequest;
import com.stockcontrolathome.authentication.audit.passwordrecoverytoken.entity.AuditPasswordRecoveryToken;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public abstract class AuditPasswordRecoveryTokenMapper extends GenericMapper<AuditPasswordRecoveryToken, AuditPasswordRecoveryTokenRequest> {

    @Override
    @Mappings({
            @Mapping(source = "auditPasswordRecoveryTokenRequest.token", target = "token"),
            @Mapping(source = "auditPasswordRecoveryTokenRequest.createdDate", target = "createdDate"),
            @Mapping(source = "auditPasswordRecoveryTokenRequest.expiredDate", target = "expiredDate"),
            @Mapping(source = "auditPasswordRecoveryTokenRequest.email", target = "email"),
            @Mapping(source = "auditPasswordRecoveryTokenRequest.state", target = "state"),
            @Mapping(target = "sentencedDate", ignore = true),
            @Mapping(target = "id", ignore = true),
    })
    public abstract AuditPasswordRecoveryToken toEntity(AuditPasswordRecoveryTokenRequest auditPasswordRecoveryTokenRequest);
}
