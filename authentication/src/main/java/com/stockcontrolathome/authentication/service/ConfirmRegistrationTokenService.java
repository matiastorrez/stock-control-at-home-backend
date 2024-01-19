package com.stockcontrolathome.authentication.service;

import com.stockcontrolathome.authentication.audit.confirmregistrationtoken.enums.EAuditConfirmRegistrationToken;
import com.stockcontrolathome.authentication.dto.confirmregistrationtoken.response.ConfirmRegistrationTokenResponse;
import com.stockcontrolathome.authentication.enums.EConfirmRegistrationTokenState;

public interface ConfirmRegistrationTokenService {


    ConfirmRegistrationTokenResponse getConfirmRegistrationTokenByTokenAndEmailAndState(String token, String email, EConfirmRegistrationTokenState state);

    ConfirmRegistrationTokenResponse getConfirmRegistrationTokenByEmail(String email);


    ConfirmRegistrationTokenResponse createConfirmRegistrationToken(String email);
    ConfirmRegistrationTokenResponse renewConfirmRegistrationToken(String email, String oldToken, EAuditConfirmRegistrationToken newStateForUsedToken);

    void deleteUsedRegistrationConfirmationToken(String token, String email, EAuditConfirmRegistrationToken newStateForUsedToken);

}
