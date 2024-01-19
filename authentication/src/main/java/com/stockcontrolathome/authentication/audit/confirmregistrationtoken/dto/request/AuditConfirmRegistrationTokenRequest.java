package com.stockcontrolathome.authentication.audit.confirmregistrationtoken.dto.request;

import com.stockcontrolathome.authentication.audit.confirmregistrationtoken.enums.EAuditConfirmRegistrationToken;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class AuditConfirmRegistrationTokenRequest {

    private EAuditConfirmRegistrationToken state;

    private Long idAuditoryConfirmRegistrationToken;

    private String token;

    private LocalDateTime createdDate;

    private LocalDateTime expiredDate;

    private String email;

}
