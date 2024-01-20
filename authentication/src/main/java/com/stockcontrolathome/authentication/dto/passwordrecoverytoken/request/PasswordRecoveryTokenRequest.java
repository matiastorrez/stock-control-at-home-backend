package com.stockcontrolathome.authentication.dto.passwordrecoverytoken.request;

import com.stockcontrolathome.authentication.enums.EPasswordRecoveryTokenState;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class PasswordRecoveryTokenRequest {

    private String token;

    private LocalDateTime createdDate;

    private LocalDateTime expiredDate;

    private EPasswordRecoveryTokenState state;

    private String email;

}
