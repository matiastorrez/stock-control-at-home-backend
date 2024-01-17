package com.stockcontrolathome.authentication.service.impl;

import com.stockcontrolathome.authentication.dto.auditoryconfirmregistrationtoken.request.AuditoryConfirmRegistrationTokenRequest;
import com.stockcontrolathome.authentication.entity.AuditoryConfirmRegistrationToken;
import com.stockcontrolathome.authentication.enums.EAuditoryConfirmRegistrationTokenState;
import com.stockcontrolathome.authentication.mapper.AuditoryConfirmRegistrationTokenMapper;
import com.stockcontrolathome.authentication.repository.AuditoryConfirmRegistrationToken.custom.AuditoryConfirmRegistrationTokenRepositoryCustom;
import com.stockcontrolathome.authentication.service.AuditoryConfirmRegistrationTokenService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class AuditoryConfirmRegistrationTokenServiceImpl implements AuditoryConfirmRegistrationTokenService {

    private final AuditoryConfirmRegistrationTokenRepositoryCustom auditoryConfirmRegistrationTokenRepositoryCustom;

    private final AuditoryConfirmRegistrationTokenMapper auditoryConfirmRegistrationTokenMapper;

    @Override
    public void saveAuditoryConfirmRegistrationToken(AuditoryConfirmRegistrationTokenRequest auditoryConfirmRegistrationTokenRequest, EAuditoryConfirmRegistrationTokenState newState) {
        AuditoryConfirmRegistrationToken auditoryConfirmRegistrationToken = this.auditoryConfirmRegistrationTokenMapper.auditoryConfirmRegistrationTokenRequestToAuditoryConfirmRegistrationTokenEntity(auditoryConfirmRegistrationTokenRequest);
        auditoryConfirmRegistrationToken.setSentencedDate(LocalDateTime.now());
        auditoryConfirmRegistrationToken.setState(newState);
        this.auditoryConfirmRegistrationTokenRepositoryCustom.saveAuditoryConfirmRegistrationToken(auditoryConfirmRegistrationToken);
    }
}
