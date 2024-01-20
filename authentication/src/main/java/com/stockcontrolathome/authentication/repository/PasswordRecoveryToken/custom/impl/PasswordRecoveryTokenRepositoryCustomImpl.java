package com.stockcontrolathome.authentication.repository.PasswordRecoveryToken.custom.impl;

import com.stockcontrolathome.authentication.entity.PasswordRecoveryToken;
import com.stockcontrolathome.authentication.enums.EPasswordRecoveryTokenState;
import com.stockcontrolathome.authentication.repository.PasswordRecoveryToken.PasswordRecoveryTokenRepository;
import com.stockcontrolathome.authentication.repository.PasswordRecoveryToken.custom.PasswordRecoveryTokenRepositoryCustom;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@AllArgsConstructor
public class PasswordRecoveryTokenRepositoryCustomImpl implements PasswordRecoveryTokenRepositoryCustom {

    private final PasswordRecoveryTokenRepository passwordRecoveryTokenRepository;
    @Override
    public Optional<PasswordRecoveryToken> getPasswordRecoveryTokenByTokenAndEmailAndState(String token, String email, EPasswordRecoveryTokenState state) {
        return this.passwordRecoveryTokenRepository.findFirstByTokenAndEmailAndState(token,email,state);
    }

    @Override
    public Optional<PasswordRecoveryToken> getPasswordRecoveryTokenByEmail(String email) {
        return this.passwordRecoveryTokenRepository.findFirstByEmail(email);
    }

    @Override
    public PasswordRecoveryToken createPasswordRecoveryToken(PasswordRecoveryToken passwordRecoveryToken) {
        return this.passwordRecoveryTokenRepository.save(passwordRecoveryToken);
    }

    @Override
    public void deleteByTokenAndEmailAndState(String token, String email, EPasswordRecoveryTokenState state) {
        this.passwordRecoveryTokenRepository.deleteByTokenAndEmailAndState(token,email, state);
    }
}
