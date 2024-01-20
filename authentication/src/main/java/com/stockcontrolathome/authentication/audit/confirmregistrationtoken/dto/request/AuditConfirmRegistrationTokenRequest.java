package com.stockcontrolathome.authentication.audit.confirmregistrationtoken.dto.request;

import com.stockcontrolathome.authentication.audit.confirmregistrationtoken.enums.EAuditConfirmRegistrationToken;
import com.stockcontrolathome.authentication.audit.generic.dto.request.GenericAuditDTORequest;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class AuditConfirmRegistrationTokenRequest extends GenericAuditDTORequest<EAuditConfirmRegistrationToken> {

    private String token;
    private LocalDateTime createdDate;
    private LocalDateTime expiredDate;
    private String email;

    public AuditConfirmRegistrationTokenRequest(EAuditConfirmRegistrationToken state, String token, LocalDateTime createdDate, LocalDateTime expiredDate, String email) {
        super(state);
        this.token = token;
        this.createdDate = createdDate;
        this.expiredDate = expiredDate;
        this.email = email;
    }
}
