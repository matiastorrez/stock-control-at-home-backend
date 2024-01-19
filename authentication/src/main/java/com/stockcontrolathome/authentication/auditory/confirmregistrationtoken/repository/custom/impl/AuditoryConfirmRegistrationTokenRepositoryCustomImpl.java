package com.stockcontrolathome.authentication.auditory.confirmregistrationtoken.repository.custom.impl;

import com.stockcontrolathome.authentication.auditory.confirmregistrationtoken.entity.AuditoryConfirmRegistrationToken;
import com.stockcontrolathome.authentication.auditory.confirmregistrationtoken.repository.AuditoryConfirmRegistrationTokenRepository;
import com.stockcontrolathome.authentication.auditory.confirmregistrationtoken.repository.custom.AuditoryConfirmRegistrationTokenRepositoryCustom;
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
