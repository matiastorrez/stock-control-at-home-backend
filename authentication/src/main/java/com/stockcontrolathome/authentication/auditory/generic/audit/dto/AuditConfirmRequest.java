package com.stockcontrolathome.authentication.auditory.generic.audit.dto;

import com.stockcontrolathome.authentication.auditory.generic.enums.ConfirmRegistrationTokenEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class AuditConfirmRequest {

    private ConfirmRegistrationTokenEnum state;

    private Long idAuditoryConfirmRegistrationToken;

    private String token;

    private LocalDateTime createdDate;

    private LocalDateTime expiredDate;

    private String email;

}
