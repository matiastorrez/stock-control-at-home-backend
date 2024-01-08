package com.stockcontrolathome.authentication.service.impl;

import com.stockcontrolathome.authentication.dto.confirmregistrationtoken.response.ConfirmRegistrationTokenResponse;
import com.stockcontrolathome.authentication.entity.ConfirmRegistrationToken;
import com.stockcontrolathome.authentication.enums.EConfirmRegistrationTokenState;
import com.stockcontrolathome.authentication.exception.ConfirmRegistrationTokenNotFoundException;
import com.stockcontrolathome.authentication.mapper.ConfirmRegistrationTokenMapper;
import com.stockcontrolathome.authentication.repository.ConfirmRegistrationToken.custom.ConfirmRegistrationTokenRepositoryCustom;
import com.stockcontrolathome.authentication.service.AuditoryConfirmRegistrationTokenService;
import com.stockcontrolathome.authentication.service.ConfirmRegistrationTokenService;
import com.stockcontrolathome.authentication.service.NotificationService;
import com.stockcontrolathome.authentication.utils.codegenerator.CodeGenerator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class ConfirmRegistrationTokenServiceImpl implements ConfirmRegistrationTokenService {

    private final ConfirmRegistrationTokenRepositoryCustom confirmRegistrationTokenRepositoryCustom;

    private final AuditoryConfirmRegistrationTokenService auditoryConfirmRegistrationTokenService;
    private final ConfirmRegistrationTokenMapper confirmRegistrationTokenMapper;
    private final NotificationService notificationService;


    private final CodeGenerator codeGenerator;


    @Override
    public ConfirmRegistrationTokenResponse getConfirmRegistrationTokenByTokenAndEmailAndState(String token, String email, EConfirmRegistrationTokenState state) {
        ConfirmRegistrationToken confirmRegistrationToken = this.confirmRegistrationTokenRepositoryCustom.getConfirmRegistrationTokenByTokenAndEmailAndState(token, email, state)
                .orElseThrow(() -> new ConfirmRegistrationTokenNotFoundException("No se encontró el codigo: " + token + " para el usuario con email: " + email));

        return this.confirmRegistrationTokenMapper.confirmRegistrationTokenEntityToConfirmRegistrationTokenResponse(confirmRegistrationToken);
    }

    @Override
    public ConfirmRegistrationTokenResponse getConfirmRegistrationTokenByEmail(String email) {
        ConfirmRegistrationToken confirmRegistrationToken = this.confirmRegistrationTokenRepositoryCustom.getConfirmRegistrationTokenByEmail(email)
                .orElseThrow(() -> new ConfirmRegistrationTokenNotFoundException("No se encontró un código para el usuario con email: " + email));

        return this.confirmRegistrationTokenMapper.confirmRegistrationTokenEntityToConfirmRegistrationTokenResponse(confirmRegistrationToken);
    }

    @Override
    public ConfirmRegistrationTokenResponse createConfirmRegistrationToken(String email) {

        String token = this.codeGenerator.generateRandomCode();

        LocalDateTime now = LocalDateTime.now();
        ConfirmRegistrationToken confirmRegistrationToken = ConfirmRegistrationToken.builder()
                .token(token)
                .createdDate(now)
                .expiredDate(now.plusDays(1))
                .state(EConfirmRegistrationTokenState.FALTA_CONFIRMAR)
                .email(email)
                .build();

        ConfirmRegistrationTokenResponse confirmRegistrationTokenResponse = this.confirmRegistrationTokenMapper.confirmRegistrationTokenEntityToConfirmRegistrationTokenResponse(this.confirmRegistrationTokenRepositoryCustom.createConfirmRegistrationToken(confirmRegistrationToken));

        notificationService.send("Registro", confirmRegistrationTokenResponse.getEmail(), this.notificationService.buildRegistrationMessage(confirmRegistrationTokenResponse.getEmail(), confirmRegistrationTokenResponse.getToken()));

        return confirmRegistrationTokenResponse;

    }

    @Override
    @Transactional
    public ConfirmRegistrationTokenResponse renewConfirmRegistrationToken(String email, String oldToken, EConfirmRegistrationTokenState oldTokenNewState) {
        this.deleteUsedRegistrationConfirmationToken(oldToken, email, oldTokenNewState);
        ConfirmRegistrationTokenResponse confirmRegistrationTokenResponse = this.createConfirmRegistrationToken(email);
        this.notificationService.send("Registro", confirmRegistrationTokenResponse.getEmail(), this.notificationService.buildRegistrationMessage(confirmRegistrationTokenResponse.getToken(), confirmRegistrationTokenResponse.getEmail()));
        return confirmRegistrationTokenResponse;
    }

    @Override
    @Transactional
    public void deleteUsedRegistrationConfirmationToken(String token, String email, EConfirmRegistrationTokenState newState) {

        ConfirmRegistrationToken confirmRegistrationToken = this.confirmRegistrationTokenRepositoryCustom.getConfirmRegistrationTokenByEmail(email)
                .orElseThrow(() -> new ConfirmRegistrationTokenNotFoundException("No se encontró un código para el usuario con email: " + email));

        this.auditoryConfirmRegistrationTokenService.saveAuditoryConfirmRegistrationToken(this.confirmRegistrationTokenMapper.confirmRegistrationTokenEntityToAuditoryConfirmRegistrationTokenRequest(confirmRegistrationToken), newState);

        this.confirmRegistrationTokenRepositoryCustom.deleteByTokenAndEmailAndState(token, email, EConfirmRegistrationTokenState.FALTA_CONFIRMAR);

    }
}
