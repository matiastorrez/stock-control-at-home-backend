package com.stockcontrolathome.authentication.repository.ConfirmRegistrationToken.custom;

import com.stockcontrolathome.authentication.entity.ConfirmRegistrationToken;
import com.stockcontrolathome.authentication.enums.ConfirmRegistrationTokenState;

import java.util.Optional;

public interface ConfirmRegistrationTokenRepositoryCustom {

    Optional<ConfirmRegistrationToken> getConfirmRegistrationTokenByTokenAndEmailAndState(String token, String email, ConfirmRegistrationTokenState state);

    void createConfirmRegistrationToken(ConfirmRegistrationToken confirmRegistrationToken);

    void deleteByTokenAndEmailAndState(String token, String email, ConfirmRegistrationTokenState state);

}
