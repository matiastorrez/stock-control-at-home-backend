package com.stockcontrolathome.authentication.service.impl;

import com.stockcontrolathome.authentication.dto.confirmregistrationtoken.request.ConfirmRegistrationTokenRequest;
import com.stockcontrolathome.authentication.dto.confirmregistrationtoken.response.ConfirmRegistrationTokenResponse;
import com.stockcontrolathome.authentication.entity.ConfirmRegistrationToken;
import com.stockcontrolathome.authentication.enums.ConfirmRegistrationTokenState;
import com.stockcontrolathome.authentication.exception.ConfirmRegistrationTokenNotFoundException;
import com.stockcontrolathome.authentication.mapper.ConfirmRegistrationTokenMapper;
import com.stockcontrolathome.authentication.repository.ConfirmRegistrationToken.custom.ConfirmRegistrationTokenRepositoryCustom;
import com.stockcontrolathome.authentication.service.ConfirmRegistrationTokenService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ConfirmRegistrationTokenServiceImpl implements ConfirmRegistrationTokenService {

    private final ConfirmRegistrationTokenRepositoryCustom confirmRegistrationTokenRepositoryCustom;
    private final ConfirmRegistrationTokenMapper confirmRegistrationTokenMapper;

    @Override
    public ConfirmRegistrationTokenResponse getConfirmRegistrationTokenByTokenAndEmailAndState(String token, String email, ConfirmRegistrationTokenState state) {
        ConfirmRegistrationToken confirmRegistrationToken = this.confirmRegistrationTokenRepositoryCustom.getConfirmRegistrationTokenByTokenAndEmailAndState(token, email, state)
                .orElseThrow(() -> new ConfirmRegistrationTokenNotFoundException("No se encontró el codigo: " + token + " para el usuario con email: " + email));

        return this.confirmRegistrationTokenMapper.confirmRegistrationTokenEntityToConfirmRegistrationTokenResponse(confirmRegistrationToken);
    }

    @Override
    public void createConfirmRegistrationToken(ConfirmRegistrationTokenRequest confirmRegistrationTokenRequest) {

        ConfirmRegistrationToken confirmRegistrationToken = this.confirmRegistrationTokenMapper.confirmRegistrationTokenRequestToConfirmRegistrationTokenEntity(confirmRegistrationTokenRequest);

        this.confirmRegistrationTokenRepositoryCustom.createConfirmRegistrationToken(confirmRegistrationToken);

    }

    @Override
    public void deleteUsedRegistrationConfirmationToken(String token, String email) {

        this.confirmRegistrationTokenRepositoryCustom.deleteByTokenAndEmailAndState(token,email, ConfirmRegistrationTokenState.FALTA_CONFIRMAR);

    }
}
