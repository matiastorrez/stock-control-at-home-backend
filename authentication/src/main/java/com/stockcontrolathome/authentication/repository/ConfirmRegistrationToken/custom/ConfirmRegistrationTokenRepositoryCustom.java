package com.stockcontrolathome.authentication.repository.ConfirmRegistrationToken.custom;

import com.stockcontrolathome.authentication.entity.ConfirmRegistrationToken;
import com.stockcontrolathome.authentication.enums.EConfirmRegistrationTokenState;

import java.util.Optional;

public interface ConfirmRegistrationTokenRepositoryCustom {

    Optional<ConfirmRegistrationToken> getConfirmRegistrationTokenByTokenAndEmailAndState(String token, String email, EConfirmRegistrationTokenState state);
    Optional<ConfirmRegistrationToken> getConfirmRegistrationTokenByEmail(String email);

    ConfirmRegistrationToken createConfirmRegistrationToken(ConfirmRegistrationToken confirmRegistrationToken);

    void deleteByTokenAndEmailAndState(String token, String email, EConfirmRegistrationTokenState state);

}
