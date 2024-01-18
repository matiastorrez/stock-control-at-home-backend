package com.stockcontrolathome.authentication.service.impl;

import com.stockcontrolathome.authentication.config.authentication.resendconfirmregister.authentication.ResendConfirmRegisterAuthenticationToken;
import com.stockcontrolathome.authentication.dto.confirmregistrationtoken.request.NewUserConfirmsRegistration;
import com.stockcontrolathome.authentication.dto.confirmregistrationtoken.response.ConfirmRegistrationTokenResponse;
import com.stockcontrolathome.authentication.dto.user.request.LoginUserRequest;
import com.stockcontrolathome.authentication.dto.user.request.RegisterUserRequest;
import com.stockcontrolathome.authentication.dto.user.request.ResendTokenForUserRequest;
import com.stockcontrolathome.authentication.enums.EAuditoryConfirmRegistrationTokenState;
import com.stockcontrolathome.authentication.enums.EConfirmRegistrationTokenState;
import com.stockcontrolathome.authentication.exception.ConfirmRegistrationTokenNotFoundException;
import com.stockcontrolathome.authentication.jwt.dto.JwtResponse;
import com.stockcontrolathome.authentication.jwt.service.JwtService;
import com.stockcontrolathome.authentication.mapper.ConfirmRegistrationTokenMapper;
import com.stockcontrolathome.authentication.service.AuthService;
import com.stockcontrolathome.authentication.service.ConfirmRegistrationTokenService;
import com.stockcontrolathome.authentication.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {

    private final UserService userService;
    private final ConfirmRegistrationTokenService confirmRegistrationTokenService;
    private final AuthenticationManager authenticationManager;

    private final JwtService jwtService;

    public static final String BAD_CREDENTIALS = "Algunas de sus credenciales son incorrectas";


    private final ConfirmRegistrationTokenMapper confirmRegistrationTokenMapper;

    @Override
    @Transactional
    public void registerUser(RegisterUserRequest registerUserRequest) {
        this.userService.registerUser(registerUserRequest);
        this.confirmRegistrationTokenService.createConfirmRegistrationToken(registerUserRequest.getEmail());
    }

    @Override
    @Transactional
    public JwtResponse confirmRegistration(NewUserConfirmsRegistration newUserConfirmsRegistration) {

        //verificamos si existe la confirmacion con el token y el email que se mando
        ConfirmRegistrationTokenResponse confirmRegistrationTokenResponse = this.confirmRegistrationTokenService.getConfirmRegistrationTokenByTokenAndEmailAndState(newUserConfirmsRegistration.getToken(), newUserConfirmsRegistration.getEmail(), EConfirmRegistrationTokenState.FALTA_CONFIRMAR);

        //Verificamos si el token expiró
        if (LocalDateTime.now().isAfter(confirmRegistrationTokenResponse.getExpiredDate())) {
            throw new ConfirmRegistrationTokenNotFoundException("El token " + newUserConfirmsRegistration.getToken() + " vinculado a este email: " + newUserConfirmsRegistration.getEmail() + " expiró, para obtener un nuevo codigo debe iniciar sesion");
        }

        //cambiamos el estado del usuario a REGISTRADO
        this.userService.modifyUserToConfirmRegister(newUserConfirmsRegistration.getEmail());

        //eliminamos la confirmacion y lo guardamos en otra tabla para saber que existio
        this.confirmRegistrationTokenService.deleteUsedRegistrationConfirmationToken(newUserConfirmsRegistration.getToken(), newUserConfirmsRegistration.getEmail(), EAuditoryConfirmRegistrationTokenState.CONFIRMADO);

        LoginUserRequest loginUserRequest = new LoginUserRequest(newUserConfirmsRegistration.getEmail(), newUserConfirmsRegistration.getPassword());//logueamos al usuario y devolvemos un JWT

        return this.loginUser(loginUserRequest);

    }

    @Override
    public JwtResponse loginUser(LoginUserRequest user) {
        try {
            Authentication authentication = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            return new JwtResponse(jwtService.generateToken(authentication));
        } catch (InternalAuthenticationServiceException ie) {
            log.info("Estoy en AuthServiceImpl, en el metodo loginUser y con la excepcion InternalAuthenticationServiceException: " + ie.getMessage());
            throw new RuntimeException(ie.getMessage());
        } catch (AuthenticationException ae) {
            log.info("Estoy en AuthServiceImpl, en el metodo loginUser y con la excepcion AuthenticationException: " + ae.getMessage());
            throw new RuntimeException(BAD_CREDENTIALS);
        }

    }

    @Override
    public void resendRegistrationConfirmation(ResendTokenForUserRequest resendTokenForUserRequest) {
        this.authenticationManager
                .authenticate(new ResendConfirmRegisterAuthenticationToken(resendTokenForUserRequest.getEmail(), resendTokenForUserRequest.getPassword()));
    }


}
