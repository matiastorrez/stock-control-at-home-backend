package com.stockcontrolathome.authentication.service.impl;

import com.stockcontrolathome.authentication.config.authentication.resendconfirmregister.authentication.ResendConfirmRegisterAuthenticationToken;
import com.stockcontrolathome.authentication.dto.confirmregistrationtoken.request.NewUserConfirmsRegistration;
import com.stockcontrolathome.authentication.dto.passwordrecoverytoken.request.ConfirmRecoverPasswordRequest;
import com.stockcontrolathome.authentication.dto.user.request.*;
import com.stockcontrolathome.authentication.jwt.dto.JwtResponse;
import com.stockcontrolathome.authentication.jwt.service.JwtService;
import com.stockcontrolathome.authentication.mapper.ConfirmRegistrationTokenMapper;
import com.stockcontrolathome.authentication.service.AuthService;
import com.stockcontrolathome.authentication.service.ConfirmRegistrationTokenService;
import com.stockcontrolathome.authentication.service.PasswordRecoveryTokenService;
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


@Service
@AllArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {

    private final UserService userService;
    private final ConfirmRegistrationTokenService confirmRegistrationTokenService;
    private final PasswordRecoveryTokenService passwordRecoveryTokenService;
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

        this.confirmRegistrationTokenService.confirmRegistration(newUserConfirmsRegistration.getEmail(),newUserConfirmsRegistration.getToken());

        //cambiamos el estado del usuario a REGISTRADO
        this.userService.modifyUserToConfirmRegister(newUserConfirmsRegistration.getEmail());

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

    @Override
    public void recoverPassword(RecoverPasswordRequest recoverPasswordRequest) {
        //verificamos que exista el email que se envio para recuperar la contraseña en nuestra base de datos
        this.userService.getUserByEmail(recoverPasswordRequest.getEmail());
        //creamos el registro que contiene el token y el email del que pidio la recuperacion de contraseña
        this.passwordRecoveryTokenService.createPasswordRecoveryToken(recoverPasswordRequest.getEmail());
    }

    @Override
    @Transactional
    public void confirmRecoverPassword(ConfirmRecoverPasswordRequest confirmRecoverPasswordRequest) {
        this.passwordRecoveryTokenService.confirmPasswordRecoveryToken(confirmRecoverPasswordRequest);
        ModifyPasswordRequest modifyPasswordRequest = new ModifyPasswordRequest(confirmRecoverPasswordRequest.getEmail(),confirmRecoverPasswordRequest.getPassword());
        this.userService.modifyPassword(modifyPasswordRequest);
    }


}
