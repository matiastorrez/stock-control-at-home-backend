package com.stockcontrolathome.authentication.audit.confirmregistrationtoken.mapper;


import com.stockcontrolathome.authentication.audit.confirmregistrationtoken.dto.request.AuditConfirmRegistrationTokenRequest;
import com.stockcontrolathome.authentication.audit.confirmregistrationtoken.entity.AuditConfirmRegistrationToken;
import com.stockcontrolathome.authentication.audit.generic.mapper.GenericMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public abstract class AuditConfirmRegistrationTokenMapper extends GenericMapper<AuditConfirmRegistrationToken, AuditConfirmRegistrationTokenRequest> {

    @Mappings({
            @Mapping(source = "auditConfirmRegistrationTokenRequest.token", target = "token"),
            @Mapping(source = "auditConfirmRegistrationTokenRequest.createdDate", target = "createdDate"),
            @Mapping(source = "auditConfirmRegistrationTokenRequest.expiredDate", target = "expiredDate"),
            @Mapping(source = "auditConfirmRegistrationTokenRequest.email", target = "email"),
            @Mapping(source = "auditConfirmRegistrationTokenRequest.state", target = "state"),
            @Mapping(target = "sentencedDate", ignore = true),
            @Mapping(target = "id", ignore = true),
    })
    public abstract AuditConfirmRegistrationToken toEntity(AuditConfirmRegistrationTokenRequest auditConfirmRegistrationTokenRequest);
}
