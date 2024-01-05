package com.stockcontrolathome.authentication.service;

import com.stockcontrolathome.authentication.dto.confirmregistrationtoken.request.ConfirmRegistrationTokenRequest;
import com.stockcontrolathome.authentication.dto.confirmregistrationtoken.response.ConfirmRegistrationTokenResponse;
import com.stockcontrolathome.authentication.enums.ConfirmRegistrationTokenState;

public interface ConfirmRegistrationTokenService {


    ConfirmRegistrationTokenResponse getConfirmRegistrationTokenByTokenAndEmailAndState(String token, String email, ConfirmRegistrationTokenState state);


    void createConfirmRegistrationToken(ConfirmRegistrationTokenRequest confirmRegistrationTokenRequest);

    void deleteUsedRegistrationConfirmationToken(String token, String email);

}
