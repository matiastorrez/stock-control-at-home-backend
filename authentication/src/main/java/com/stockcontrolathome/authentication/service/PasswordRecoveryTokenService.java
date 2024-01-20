package com.stockcontrolathome.authentication.service;

import com.stockcontrolathome.authentication.audit.passwordrecoverytoken.enums.EAuditPasswordRecoveryToken;
import com.stockcontrolathome.authentication.dto.passwordrecoverytoken.request.ConfirmRecoverPasswordRequest;
import com.stockcontrolathome.authentication.dto.passwordrecoverytoken.response.PasswordRecoveryTokenResponse;
import com.stockcontrolathome.authentication.enums.EPasswordRecoveryTokenState;

public interface PasswordRecoveryTokenService {


    PasswordRecoveryTokenResponse getPasswordRecoveryTokenByTokenAndEmailAndState(String token, String email, EPasswordRecoveryTokenState state);

    PasswordRecoveryTokenResponse getPasswordRecoveryTokenByEmail(String email);

    PasswordRecoveryTokenResponse createPasswordRecoveryToken(String email);

    void deleteUsedPasswordRecoveryToken(String token, String email, EAuditPasswordRecoveryToken newStateForUsedToken);

    void confirmPasswordRecoveryToken(ConfirmRecoverPasswordRequest confirmRecoverPasswordRequest);

}
