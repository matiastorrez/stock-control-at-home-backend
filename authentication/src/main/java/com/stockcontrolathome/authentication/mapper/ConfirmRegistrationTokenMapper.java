package com.stockcontrolathome.authentication.mapper;

import com.stockcontrolathome.authentication.dto.auditoryconfirmregistrationtoken.request.AuditoryConfirmRegistrationTokenRequest;
import com.stockcontrolathome.authentication.dto.confirmregistrationtoken.request.ConfirmRegistrationTokenRequest;
import com.stockcontrolathome.authentication.dto.confirmregistrationtoken.response.ConfirmRegistrationTokenResponse;
import com.stockcontrolathome.authentication.entity.ConfirmRegistrationToken;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public abstract class ConfirmRegistrationTokenMapper {

    @Mappings({
            @Mapping(source = "confirmRegistrationTokenRequest.confirmRegistrationTokenId", target = "idConfirmRegistrationToken"),
            @Mapping(source = "confirmRegistrationTokenRequest.token", target = "token"),
            @Mapping(source = "confirmRegistrationTokenRequest.createdDate", target = "createdDate"),
            @Mapping(source = "confirmRegistrationTokenRequest.expiredDate", target = "expiredDate"),
            @Mapping(source = "confirmRegistrationTokenRequest.state", target = "state"),
            @Mapping(source = "confirmRegistrationTokenRequest.email", target = "email"),
    })
    public abstract ConfirmRegistrationToken confirmRegistrationTokenRequestToConfirmRegistrationTokenEntity(ConfirmRegistrationTokenRequest confirmRegistrationTokenRequest);

    @Mappings({
            @Mapping(source = "confirmRegistrationToken.idConfirmRegistrationToken", target = "confirmRegistrationTokenId"),
            @Mapping(source = "confirmRegistrationToken.token", target = "token"),
            @Mapping(source = "confirmRegistrationToken.createdDate", target = "createdDate"),
            @Mapping(source = "confirmRegistrationToken.expiredDate", target = "expiredDate"),
            @Mapping(source = "confirmRegistrationToken.state", target = "state"),
            @Mapping(source = "confirmRegistrationToken.email", target = "email"),
    })
    public abstract ConfirmRegistrationTokenResponse confirmRegistrationTokenEntityToConfirmRegistrationTokenResponse(ConfirmRegistrationToken confirmRegistrationToken);


    @Mappings({
            @Mapping(source = "confirmRegistrationToken.idConfirmRegistrationToken", target = "idConfirmRegistrationToken"),
            @Mapping(source = "confirmRegistrationToken.token", target = "token"),
            @Mapping(source = "confirmRegistrationToken.createdDate", target = "createdDate"),
            @Mapping(source = "confirmRegistrationToken.expiredDate", target = "expiredDate"),
            @Mapping(source = "confirmRegistrationToken.email", target = "email"),
            @Mapping(target = "sentencedDate", ignore = true),
            @Mapping(target = "state", ignore = true),

    })
    public abstract AuditoryConfirmRegistrationTokenRequest confirmRegistrationTokenEntityToAuditoryConfirmRegistrationTokenRequest(ConfirmRegistrationToken confirmRegistrationToken);



}
