package com.stockcontrolathome.authentication.audit;

import com.stockcontrolathome.authentication.audit.confirmregistrationtoken.dto.request.AuditConfirmRegistrationTokenRequest;
import com.stockcontrolathome.authentication.audit.confirmregistrationtoken.entity.AuditConfirmRegistrationToken;
import com.stockcontrolathome.authentication.audit.confirmregistrationtoken.enums.EAuditConfirmRegistrationToken;
import com.stockcontrolathome.authentication.audit.confirmregistrationtoken.mapper.AuditConfirmRegistrationTokenMapperImpl;
import com.stockcontrolathome.authentication.audit.generic.service.impl.GenericAuditServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigAudit {

    @Bean
    public GenericAuditServiceImpl<AuditConfirmRegistrationTokenRequest, AuditConfirmRegistrationToken, EAuditConfirmRegistrationToken, String> auditConfirmRegistrationToken(){
        GenericAuditServiceImpl<AuditConfirmRegistrationTokenRequest, AuditConfirmRegistrationToken, EAuditConfirmRegistrationToken, String> genericAuditServiceImpl =  new GenericAuditServiceImpl<>();
        genericAuditServiceImpl.setGenericMapper(new AuditConfirmRegistrationTokenMapperImpl());
        return genericAuditServiceImpl;
    }

}
