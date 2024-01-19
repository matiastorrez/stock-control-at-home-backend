package com.stockcontrolathome.authentication.auditory;

import com.stockcontrolathome.authentication.auditory.generic.audit.dto.AuditConfirmRequest;
import com.stockcontrolathome.authentication.auditory.generic.audit.entity.AuditConfirm;
import com.stockcontrolathome.authentication.auditory.generic.enums.ConfirmRegistrationTokenEnum;
import com.stockcontrolathome.authentication.auditory.generic.mapper.AuditConfirmRegistrationMapperImpl;
import com.stockcontrolathome.authentication.auditory.generic.service.impl.GenericAuditServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigAudit {

    @Bean
    public GenericAuditServiceImpl<AuditConfirmRequest, AuditConfirm, ConfirmRegistrationTokenEnum, String> auditConfirmRegistrationToken(){
        GenericAuditServiceImpl<AuditConfirmRequest, AuditConfirm, ConfirmRegistrationTokenEnum, String> genericAuditServiceImpl =  new GenericAuditServiceImpl<>();
        genericAuditServiceImpl.setGenericMapper(new AuditConfirmRegistrationMapperImpl());
        return genericAuditServiceImpl;
    }

}
