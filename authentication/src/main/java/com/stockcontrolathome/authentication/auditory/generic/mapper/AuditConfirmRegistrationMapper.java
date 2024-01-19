package com.stockcontrolathome.authentication.auditory.generic.mapper;


import com.stockcontrolathome.authentication.auditory.generic.audit.dto.AuditConfirmRequest;
import com.stockcontrolathome.authentication.auditory.generic.audit.entity.AuditConfirm;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.beans.factory.annotation.Qualifier;

@Mapper(componentModel = "spring")
@Qualifier("auditConfirmRegistrationMapper")
public abstract class AuditConfirmRegistrationMapper extends GenericMapper<AuditConfirm, AuditConfirmRequest>{

    @Mappings({
            @Mapping(source = "auditConfirmRequest.idAuditoryConfirmRegistrationToken", target = "id"),
            @Mapping(source = "auditConfirmRequest.token", target = "token"),
            @Mapping(source = "auditConfirmRequest.createdDate", target = "createdDate"),
            @Mapping(source = "auditConfirmRequest.expiredDate", target = "expiredDate"),
            @Mapping(source = "auditConfirmRequest.email", target = "email"),
            @Mapping(target = "sentencedDate", ignore = true),
            @Mapping(target = "state", ignore = true),

    })
    public abstract AuditConfirm toEntity(AuditConfirmRequest auditConfirmRequest);
}
