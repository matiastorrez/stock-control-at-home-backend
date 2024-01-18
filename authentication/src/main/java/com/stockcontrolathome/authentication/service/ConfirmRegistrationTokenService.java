package com.stockcontrolathome.authentication.service;

import com.stockcontrolathome.authentication.dto.confirmregistrationtoken.response.ConfirmRegistrationTokenResponse;
import com.stockcontrolathome.authentication.enums.EAuditoryConfirmRegistrationTokenState;
import com.stockcontrolathome.authentication.enums.EConfirmRegistrationTokenState;

public interface ConfirmRegistrationTokenService {


    ConfirmRegistrationTokenResponse getConfirmRegistrationTokenByTokenAndEmailAndState(String token, String email, EConfirmRegistrationTokenState state);

    ConfirmRegistrationTokenResponse getConfirmRegistrationTokenByEmail(String email);


    ConfirmRegistrationTokenResponse createConfirmRegistrationToken(String email);
    ConfirmRegistrationTokenResponse renewConfirmRegistrationToken(String email, String oldToken, EAuditoryConfirmRegistrationTokenState newStateForUsedToken);

    void deleteUsedRegistrationConfirmationToken(String token, String email, EAuditoryConfirmRegistrationTokenState newStateForUsedToken);

}
