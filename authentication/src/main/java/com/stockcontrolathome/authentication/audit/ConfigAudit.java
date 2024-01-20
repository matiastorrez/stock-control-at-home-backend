package com.stockcontrolathome.authentication.audit;

import com.stockcontrolathome.authentication.audit.confirmregistrationtoken.dto.request.AuditConfirmRegistrationTokenRequest;
import com.stockcontrolathome.authentication.audit.confirmregistrationtoken.entity.AuditConfirmRegistrationToken;
import com.stockcontrolathome.authentication.audit.confirmregistrationtoken.enums.EAuditConfirmRegistrationToken;
import com.stockcontrolathome.authentication.audit.confirmregistrationtoken.mapper.AuditConfirmRegistrationTokenMapperImpl;
import com.stockcontrolathome.authentication.audit.generic.service.impl.GenericAuditServiceImpl;
import com.stockcontrolathome.authentication.audit.passwordrecoverytoken.dto.request.AuditPasswordRecoveryTokenRequest;
import com.stockcontrolathome.authentication.audit.passwordrecoverytoken.entity.AuditPasswordRecoveryToken;
import com.stockcontrolathome.authentication.audit.passwordrecoverytoken.enums.EAuditPasswordRecoveryToken;
import com.stockcontrolathome.authentication.audit.passwordrecoverytoken.mapper.AuditPasswordRecoveryTokenMapperImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigAudit {

    @Bean
    public GenericAuditServiceImpl<AuditConfirmRegistrationTokenRequest, AuditConfirmRegistrationToken, EAuditConfirmRegistrationToken> auditConfirmRegistrationToken(){
        GenericAuditServiceImpl<AuditConfirmRegistrationTokenRequest, AuditConfirmRegistrationToken, EAuditConfirmRegistrationToken> genericAuditServiceImpl =  new GenericAuditServiceImpl<>();
        genericAuditServiceImpl.setGenericMapper(new AuditConfirmRegistrationTokenMapperImpl());
        return genericAuditServiceImpl;
    }

    @Bean
    public GenericAuditServiceImpl<AuditPasswordRecoveryTokenRequest, AuditPasswordRecoveryToken, EAuditPasswordRecoveryToken> auditPasswordRecoveryToken(){
        GenericAuditServiceImpl<AuditPasswordRecoveryTokenRequest, AuditPasswordRecoveryToken, EAuditPasswordRecoveryToken> genericAuditServiceImpl =  new GenericAuditServiceImpl<>();
        genericAuditServiceImpl.setGenericMapper(new AuditPasswordRecoveryTokenMapperImpl());
        return genericAuditServiceImpl;
    }

}
