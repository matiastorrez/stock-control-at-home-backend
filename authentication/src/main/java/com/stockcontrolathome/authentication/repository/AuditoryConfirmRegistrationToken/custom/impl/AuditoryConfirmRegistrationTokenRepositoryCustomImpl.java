package com.stockcontrolathome.authentication.repository.AuditoryConfirmRegistrationToken.custom.impl;

import com.stockcontrolathome.authentication.entity.AuditoryConfirmRegistrationToken;
import com.stockcontrolathome.authentication.repository.AuditoryConfirmRegistrationToken.AuditoryConfirmRegistrationTokenRepository;
import com.stockcontrolathome.authentication.repository.AuditoryConfirmRegistrationToken.custom.AuditoryConfirmRegistrationTokenRepositoryCustom;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class AuditoryConfirmRegistrationTokenRepositoryCustomImpl implements AuditoryConfirmRegistrationTokenRepositoryCustom {

    private AuditoryConfirmRegistrationTokenRepository auditoryConfirmRegistrationTokenRepository;

    @Override
    public void saveAuditoryConfirmRegistrationToken(AuditoryConfirmRegistrationToken auditoryConfirmRegistrationToken) {
        this.auditoryConfirmRegistrationTokenRepository.save(auditoryConfirmRegistrationToken);
    }
}
