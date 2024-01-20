package com.stockcontrolathome.authentication.repository.PasswordRecoveryToken.custom;

import com.stockcontrolathome.authentication.entity.PasswordRecoveryToken;
import com.stockcontrolathome.authentication.enums.EPasswordRecoveryTokenState;

import java.util.Optional;

public interface PasswordRecoveryTokenRepositoryCustom {

    Optional<PasswordRecoveryToken> getPasswordRecoveryTokenByTokenAndEmailAndState(String token, String email, EPasswordRecoveryTokenState state);
    Optional<PasswordRecoveryToken> getPasswordRecoveryTokenByEmail(String email);

    PasswordRecoveryToken createPasswordRecoveryToken(PasswordRecoveryToken passwordRecoveryToken);

    void deleteByTokenAndEmailAndState(String token, String email, EPasswordRecoveryTokenState state);

}
