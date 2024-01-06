package com.stockcontrolathome.authentication.mapper;

import com.stockcontrolathome.authentication.dto.auditoryconfirmregistrationtoken.request.AuditoryConfirmRegistrationTokenRequest;
import com.stockcontrolathome.authentication.dto.confirmregistrationtoken.request.ConfirmRegistrationTokenRequest;
import com.stockcontrolathome.authentication.entity.AuditoryConfirmRegistrationToken;
import com.stockcontrolathome.authentication.entity.ConfirmRegistrationToken;
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
            @Mapping(source = "auditoryConfirmRegistrationTokenRequest.confirmationDate", target = "confirmationDate"),
            @Mapping(source = "auditoryConfirmRegistrationTokenRequest.state", target = "state"),
            @Mapping(source = "auditoryConfirmRegistrationTokenRequest.email", target = "email"),
            @Mapping(target = "auditoryConfirmRegistrationTokenRequest.idAuditoryConfirmRegistrationToken", ignore = true)
    })
    public abstract AuditoryConfirmRegistrationToken auditoryConfirmRegistrationTokenRequestToAuditoryConfirmRegistrationTokenEntity(AuditoryConfirmRegistrationTokenRequest auditoryConfirmRegistrationTokenRequest);


}
