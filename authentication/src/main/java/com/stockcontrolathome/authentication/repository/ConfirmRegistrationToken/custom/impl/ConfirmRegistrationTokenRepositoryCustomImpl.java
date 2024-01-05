package com.stockcontrolathome.authentication.repository.ConfirmRegistrationToken.custom.impl;

import com.stockcontrolathome.authentication.entity.ConfirmRegistrationToken;
import com.stockcontrolathome.authentication.enums.ConfirmRegistrationTokenState;
import com.stockcontrolathome.authentication.repository.ConfirmRegistrationToken.ConfirmRegistrationTokenRepository;
import com.stockcontrolathome.authentication.repository.ConfirmRegistrationToken.custom.ConfirmRegistrationTokenRepositoryCustom;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@AllArgsConstructor
public class ConfirmRegistrationTokenRepositoryCustomImpl implements ConfirmRegistrationTokenRepositoryCustom {

    private final ConfirmRegistrationTokenRepository confirmRegistrationTokenRepository;


    @Override
    public Optional<ConfirmRegistrationToken> getConfirmRegistrationTokenByTokenAndEmailAndState(String token, String email, ConfirmRegistrationTokenState state) {
        return this.confirmRegistrationTokenRepository.findFirstByTokenAndEmailAndState(token,email,state);
    }

    @Override
    public void createConfirmRegistrationToken(ConfirmRegistrationToken confirmRegistrationToken) {
        this.confirmRegistrationTokenRepository.save(confirmRegistrationToken);
    }

    @Override
    public void deleteByTokenAndEmailAndState(String token, String email, ConfirmRegistrationTokenState state) {
        this.confirmRegistrationTokenRepository.deleteByTokenAndEmailAndState(token,email,state);
    }
}
