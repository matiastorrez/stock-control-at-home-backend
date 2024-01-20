package com.stockcontrolathome.authentication.service.impl;

import com.stockcontrolathome.authentication.audit.generic.service.impl.GenericAuditServiceImpl;
import com.stockcontrolathome.authentication.audit.passwordrecoverytoken.dto.request.AuditPasswordRecoveryTokenRequest;
import com.stockcontrolathome.authentication.audit.passwordrecoverytoken.entity.AuditPasswordRecoveryToken;
import com.stockcontrolathome.authentication.audit.passwordrecoverytoken.enums.EAuditPasswordRecoveryToken;
import com.stockcontrolathome.authentication.dto.passwordrecoverytoken.request.ConfirmRecoverPasswordRequest;
import com.stockcontrolathome.authentication.dto.passwordrecoverytoken.response.PasswordRecoveryTokenResponse;
import com.stockcontrolathome.authentication.entity.PasswordRecoveryToken;
import com.stockcontrolathome.authentication.enums.EPasswordRecoveryTokenState;
import com.stockcontrolathome.authentication.exception.PasswordRecoveryTokenNotFoundException;
import com.stockcontrolathome.authentication.mapper.PasswordRecoveryTokenMapper;
import com.stockcontrolathome.authentication.repository.PasswordRecoveryToken.custom.PasswordRecoveryTokenRepositoryCustom;
import com.stockcontrolathome.authentication.service.NotificationService;
import com.stockcontrolathome.authentication.service.PasswordRecoveryTokenService;
import com.stockcontrolathome.authentication.utils.codegenerator.CodeGenerator;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class PasswordRecoveryTokenServiceImpl implements PasswordRecoveryTokenService {

    private final PasswordRecoveryTokenRepositoryCustom passwordRecoveryTokenRepositoryCustom;

    private final PasswordRecoveryTokenMapper passwordRecoveryTokenMapper;

    private final NotificationService notificationService;

    private final CodeGenerator codeGenerator;

    private final PasswordEncoder passwordEncoder;
    private final GenericAuditServiceImpl<AuditPasswordRecoveryTokenRequest, AuditPasswordRecoveryToken, EAuditPasswordRecoveryToken> auditPasswordRecoveryToken;



    @Override
    public PasswordRecoveryTokenResponse getPasswordRecoveryTokenByTokenAndEmailAndState(String token, String email, EPasswordRecoveryTokenState state) {

        PasswordRecoveryToken passwordRecoveryToken = this.passwordRecoveryTokenRepositoryCustom.getPasswordRecoveryTokenByTokenAndEmailAndState(token,email,state)
                .orElseThrow(() -> new PasswordRecoveryTokenNotFoundException("No se encontró el codigo: "+ token + " para el usuario con email: "+ email));

        return this.passwordRecoveryTokenMapper.passwordRecoveryTokenEntityToPasswordRecoveryTokenResponse(passwordRecoveryToken);
    }

    @Override
    public PasswordRecoveryTokenResponse getPasswordRecoveryTokenByEmail(String email) {
        PasswordRecoveryToken passwordRecoveryToken = this.passwordRecoveryTokenRepositoryCustom.getPasswordRecoveryTokenByEmail(email)
                .orElseThrow(() -> new PasswordRecoveryTokenNotFoundException("No se encontró un codigo para el usuario con email: " + email));
        return this.passwordRecoveryTokenMapper.passwordRecoveryTokenEntityToPasswordRecoveryTokenResponse(passwordRecoveryToken);
    }

    @Override
    @Transactional
    public PasswordRecoveryTokenResponse createPasswordRecoveryToken(String email) {
        this.passwordRecoveryTokenRepositoryCustom.getPasswordRecoveryTokenByEmail(email).ifPresent((pr) -> {
            if (LocalDateTime.now().isAfter(pr.getExpiredDate())) {
                this.deleteUsedPasswordRecoveryToken(pr.getToken(),pr.getEmail(),EAuditPasswordRecoveryToken.ELIMINADO_POR_EXPIRACION_Y_RENOVACION);
            }else{
                this.deleteUsedPasswordRecoveryToken(pr.getToken(),pr.getEmail(),EAuditPasswordRecoveryToken.ELIMINADO_POR_RENOVACION);
            }
        });

        String token = this.codeGenerator.generateRandomCode();

        LocalDateTime now = LocalDateTime.now();

        PasswordRecoveryToken passwordRecoveryToken = PasswordRecoveryToken.builder()
                .token(token)
                .createdDate(now)
                .expiredDate(now.plusMinutes(10))
                .state(EPasswordRecoveryTokenState.FALTA_CONFIRMAR)
                .email(email)
                .build();

        PasswordRecoveryTokenResponse passwordRecoveryTokenResponse = this.passwordRecoveryTokenMapper.passwordRecoveryTokenEntityToPasswordRecoveryTokenResponse((this.passwordRecoveryTokenRepositoryCustom.createPasswordRecoveryToken(passwordRecoveryToken)));

        notificationService.send("Recuperar contraseña", passwordRecoveryTokenResponse.getEmail(), this.notificationService.buildRecoverPasswordMessage(passwordRecoveryTokenResponse.getEmail(), passwordRecoveryTokenResponse.getToken()));

        return passwordRecoveryTokenResponse;
    }

    @Override
    @Transactional
    public void deleteUsedPasswordRecoveryToken(String token, String email, EAuditPasswordRecoveryToken newStateForUsedToken) {

        PasswordRecoveryToken passwordRecoveryToken = this.passwordRecoveryTokenRepositoryCustom.getPasswordRecoveryTokenByEmail(email)
                .orElseThrow(() -> new PasswordRecoveryTokenNotFoundException("No se encontró un codigo para el usuario con email: "+ email));

        AuditPasswordRecoveryTokenRequest request = this.passwordRecoveryTokenMapper.passwordRecoveryTokenEntityToAuditPasswordRecoveryTokenRequest(passwordRecoveryToken, newStateForUsedToken);

        this.auditPasswordRecoveryToken.saveGenericAudit(request);

        this.passwordRecoveryTokenRepositoryCustom.deleteByTokenAndEmailAndState(token, email, EPasswordRecoveryTokenState.FALTA_CONFIRMAR);

    }

    @Transactional
    public void confirmPasswordRecoveryToken(ConfirmRecoverPasswordRequest confirmRecoverPasswordRequest){

        PasswordRecoveryToken passwordRecoveryToken = this.passwordRecoveryTokenRepositoryCustom.getPasswordRecoveryTokenByTokenAndEmailAndState(confirmRecoverPasswordRequest.getToken(),confirmRecoverPasswordRequest.getEmail(),EPasswordRecoveryTokenState.FALTA_CONFIRMAR)
                .orElseThrow(() -> new PasswordRecoveryTokenNotFoundException("No se encontró un codigo para el usuario con email: " + confirmRecoverPasswordRequest.getEmail()));

        if (LocalDateTime.now().isAfter(passwordRecoveryToken.getExpiredDate())) {
            throw new PasswordRecoveryTokenNotFoundException("El token " + passwordRecoveryToken.getToken() + " vinculado a este email: " + passwordRecoveryToken.getEmail() + " expiró, para obtener un nuevo codigo debe pedir el cambio de contraseña nuevamente");
        }
        AuditPasswordRecoveryTokenRequest request = this.passwordRecoveryTokenMapper.passwordRecoveryTokenEntityToAuditPasswordRecoveryTokenRequest(passwordRecoveryToken, EAuditPasswordRecoveryToken.CONFIRMADO);
        this.auditPasswordRecoveryToken.saveGenericAudit(request);
        this.passwordRecoveryTokenRepositoryCustom.deleteByTokenAndEmailAndState(passwordRecoveryToken.getToken(), passwordRecoveryToken.getEmail(), EPasswordRecoveryTokenState.FALTA_CONFIRMAR);

    }
}
