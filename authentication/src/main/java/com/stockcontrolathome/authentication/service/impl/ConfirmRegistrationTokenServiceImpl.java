package com.stockcontrolathome.authentication.service.impl;

import com.stockcontrolathome.authentication.audit.confirmregistrationtoken.dto.request.AuditConfirmRegistrationTokenRequest;
import com.stockcontrolathome.authentication.audit.confirmregistrationtoken.entity.AuditConfirmRegistrationToken;
import com.stockcontrolathome.authentication.audit.confirmregistrationtoken.enums.EAuditConfirmRegistrationToken;
import com.stockcontrolathome.authentication.audit.generic.service.impl.GenericAuditServiceImpl;
import com.stockcontrolathome.authentication.dto.confirmregistrationtoken.response.ConfirmRegistrationTokenResponse;
import com.stockcontrolathome.authentication.entity.ConfirmRegistrationToken;
import com.stockcontrolathome.authentication.enums.EConfirmRegistrationTokenState;
import com.stockcontrolathome.authentication.exception.ConfirmRegistrationTokenNotFoundException;
import com.stockcontrolathome.authentication.mapper.ConfirmRegistrationTokenMapper;
import com.stockcontrolathome.authentication.repository.ConfirmRegistrationToken.custom.ConfirmRegistrationTokenRepositoryCustom;
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
    private final ConfirmRegistrationTokenMapper confirmRegistrationTokenMapper;
    private final NotificationService notificationService;
    private final CodeGenerator codeGenerator;
    private final GenericAuditServiceImpl<AuditConfirmRegistrationTokenRequest, AuditConfirmRegistrationToken, EAuditConfirmRegistrationToken> auditConfirmRegistrationToken;


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
    public ConfirmRegistrationTokenResponse renewConfirmRegistrationToken(String email, String oldToken, EAuditConfirmRegistrationToken newStateForUsedToken) {
        this.deleteUsedRegistrationConfirmationToken(oldToken, email, newStateForUsedToken);
        return this.createConfirmRegistrationToken(email);
    }

    @Override
    @Transactional
    public void deleteUsedRegistrationConfirmationToken(String token, String email, EAuditConfirmRegistrationToken newStateForUsedToken) {

        ConfirmRegistrationToken confirmRegistrationToken = this.confirmRegistrationTokenRepositoryCustom.getConfirmRegistrationTokenByEmail(email)
                .orElseThrow(() -> new ConfirmRegistrationTokenNotFoundException("No se encontró un código para el usuario con email: " + email));

        AuditConfirmRegistrationTokenRequest request = this.confirmRegistrationTokenMapper.confirmRegistrationTokenEntityToAuditConfirmRegistrationTokenRequest(confirmRegistrationToken,newStateForUsedToken);

        this.auditConfirmRegistrationToken.saveGenericAudit(request);

        this.confirmRegistrationTokenRepositoryCustom.deleteByTokenAndEmailAndState(token, email, EConfirmRegistrationTokenState.FALTA_CONFIRMAR);

    }

    @Override
    @Transactional
    public void confirmRegistration(String email, String token) {
        //verificamos si existe la confirmacion con el token y el email que se mando
        ConfirmRegistrationToken confirmRegistrationToken = this.confirmRegistrationTokenRepositoryCustom.getConfirmRegistrationTokenByTokenAndEmailAndState(token, email, EConfirmRegistrationTokenState.FALTA_CONFIRMAR)
                .orElseThrow(() -> new ConfirmRegistrationTokenNotFoundException("No se encontró el codigo: " + token + " para el usuario con email: " + email));

        //Verificamos si el token expiró
        if (LocalDateTime.now().isAfter(confirmRegistrationToken.getExpiredDate())) {
            throw new ConfirmRegistrationTokenNotFoundException("El token " + confirmRegistrationToken.getToken() + " vinculado a este email: " + confirmRegistrationToken.getEmail() + " expiró, para obtener un nuevo codigo debe iniciar sesion");
        }

        //eliminamos la confirmacion y lo guardamos en otra tabla para saber que existio
        AuditConfirmRegistrationTokenRequest request = this.confirmRegistrationTokenMapper.confirmRegistrationTokenEntityToAuditConfirmRegistrationTokenRequest(confirmRegistrationToken,EAuditConfirmRegistrationToken.CONFIRMADO);

        this.auditConfirmRegistrationToken.saveGenericAudit(request);

        this.confirmRegistrationTokenRepositoryCustom.deleteByTokenAndEmailAndState(token, email, EConfirmRegistrationTokenState.FALTA_CONFIRMAR);


    }
}
