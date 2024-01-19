package com.stockcontrolathome.authentication.auditory.confirmregistrationtoken.mapper;

import com.stockcontrolathome.authentication.auditory.confirmregistrationtoken.dto.auditoryconfirmregistrationtoken.request.AuditoryConfirmRegistrationTokenRequest;
import com.stockcontrolathome.authentication.auditory.confirmregistrationtoken.entity.AuditoryConfirmRegistrationToken;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public abstract class AuditoryConfirmRegistrationTokenMapper {

    @Mappings({
            @Mapping(source = "auditoryConfirmRegistrationTokenRequest.idConfirmRegistrationToken", target = "idConfirmRegistrationToken"),
            @Mapping(source = "auditoryConfirmRegistrationTokenRequest.token", target = "token"),
            @Mapping(source = "auditoryConfirmRegistrationTokenRequest.createdDate", target = "createdDate"),
            @Mapping(source = "auditoryConfirmRegistrationTokenRequest.expiredDate", target = "expiredDate"),
            @Mapping(source = "auditoryConfirmRegistrationTokenRequest.sentencedDate", target = "sentencedDate"),
            @Mapping(source = "auditoryConfirmRegistrationTokenRequest.state", target = "state"),
            @Mapping(source = "auditoryConfirmRegistrationTokenRequest.email", target = "email"),
            @Mapping(target = "auditoryConfirmRegistrationTokenRequest.idAuditoryConfirmRegistrationToken", ignore = true)
    })
    public abstract AuditoryConfirmRegistrationToken auditoryConfirmRegistrationTokenRequestToAuditoryConfirmRegistrationTokenEntity(AuditoryConfirmRegistrationTokenRequest auditoryConfirmRegistrationTokenRequest);


}
