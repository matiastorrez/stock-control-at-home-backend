package com.stockcontrolathome.authentication.dto.auditoryconfirmregistrationtoken.request;

import com.stockcontrolathome.authentication.enums.EAuditoryConfirmRegistrationTokenState;
import com.stockcontrolathome.authentication.enums.EConfirmRegistrationTokenState;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class AuditoryConfirmRegistrationTokenRequest {

    private Long idConfirmRegistrationToken;

    private String token;

    private LocalDateTime createdDate;

    private LocalDateTime expiredDate;

    private LocalDateTime sentencedDate;

    private EAuditoryConfirmRegistrationTokenState state;

    private String email;




}
