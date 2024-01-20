package com.stockcontrolathome.authentication.audit.passwordrecoverytoken.dto.request;

import com.stockcontrolathome.authentication.audit.generic.dto.request.GenericAuditDTORequest;
import com.stockcontrolathome.authentication.audit.passwordrecoverytoken.enums.EAuditPasswordRecoveryToken;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class AuditPasswordRecoveryTokenRequest extends GenericAuditDTORequest<EAuditPasswordRecoveryToken> {

    private String token;

    private LocalDateTime createdDate;

    private LocalDateTime expiredDate;

    private String email;

    public AuditPasswordRecoveryTokenRequest(EAuditPasswordRecoveryToken state, String token, LocalDateTime createdDate, LocalDateTime expiredDate, String email) {
        super(state);
        this.token = token;
        this.createdDate = createdDate;
        this.expiredDate = expiredDate;
        this.email = email;
    }
}
