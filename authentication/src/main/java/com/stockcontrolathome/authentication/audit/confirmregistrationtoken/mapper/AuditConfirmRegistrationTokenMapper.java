package com.stockcontrolathome.authentication.audit.confirmregistrationtoken.mapper;


import com.stockcontrolathome.authentication.audit.confirmregistrationtoken.dto.request.AuditConfirmRegistrationTokenRequest;
import com.stockcontrolathome.authentication.audit.confirmregistrationtoken.entity.AuditConfirmRegistrationToken;
import com.stockcontrolathome.authentication.audit.generic.mapper.GenericMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.beans.factory.annotation.Qualifier;

@Mapper(componentModel = "spring")
@Qualifier("auditConfirmRegistrationMapper")
public abstract class AuditConfirmRegistrationTokenMapper extends GenericMapper<AuditConfirmRegistrationToken, AuditConfirmRegistrationTokenRequest> {

    @Mappings({
            @Mapping(source = "auditConfirmRegistrationTokenRequest.idAuditoryConfirmRegistrationToken", target = "id"),
            @Mapping(source = "auditConfirmRegistrationTokenRequest.token", target = "token"),
            @Mapping(source = "auditConfirmRegistrationTokenRequest.createdDate", target = "createdDate"),
            @Mapping(source = "auditConfirmRegistrationTokenRequest.expiredDate", target = "expiredDate"),
            @Mapping(source = "auditConfirmRegistrationTokenRequest.email", target = "email"),
            @Mapping(target = "sentencedDate", ignore = true),
            @Mapping(target = "state", ignore = true),

    })
    public abstract AuditConfirmRegistrationToken toEntity(AuditConfirmRegistrationTokenRequest auditConfirmRegistrationTokenRequest);
}
