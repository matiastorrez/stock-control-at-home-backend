package com.stockcontrolathome.authentication.dto.confirmregistrationtoken.request;

import com.stockcontrolathome.authentication.enums.ConfirmRegistrationTokenState;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@Builder
public class ConfirmRegistrationTokenRequest {

    private Long confirmRegistrationTokenId;

    private String token;

    private LocalDateTime createdDate;

    private LocalDateTime expiredDate;

    private LocalDateTime confirmationDate;

    private ConfirmRegistrationTokenState state;

    private String email;

}
