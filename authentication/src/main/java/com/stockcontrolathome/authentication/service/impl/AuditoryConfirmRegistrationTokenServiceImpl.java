package com.stockcontrolathome.authentication.service.impl;

import com.stockcontrolathome.authentication.dto.auditoryconfirmregistrationtoken.request.AuditoryConfirmRegistrationTokenRequest;
import com.stockcontrolathome.authentication.entity.AuditoryConfirmRegistrationToken;
import com.stockcontrolathome.authentication.enums.ConfirmRegistrationTokenState;
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
    public void saveAuditoryConfirmRegistrationToken(AuditoryConfirmRegistrationTokenRequest auditoryConfirmRegistrationTokenRequest) {
        AuditoryConfirmRegistrationToken auditoryConfirmRegistrationToken = this.auditoryConfirmRegistrationTokenMapper.auditoryConfirmRegistrationTokenRequestToAuditoryConfirmRegistrationTokenEntity(auditoryConfirmRegistrationTokenRequest);
        auditoryConfirmRegistrationToken.setConfirmationDate(LocalDateTime.now());
        auditoryConfirmRegistrationToken.setState(ConfirmRegistrationTokenState.CONFIRMADO);
        this.auditoryConfirmRegistrationTokenRepositoryCustom.saveAuditoryConfirmRegistrationToken(auditoryConfirmRegistrationToken);
    }
}
