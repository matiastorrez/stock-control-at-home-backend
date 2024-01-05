package com.stockcontrolathome.authentication.dto.auditoryconfirmregistrationtoken.request;

import com.stockcontrolathome.authentication.enums.ConfirmRegistrationTokenState;
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

    private LocalDateTime confirmationDate;

    private ConfirmRegistrationTokenState state;

    private String email;




}
