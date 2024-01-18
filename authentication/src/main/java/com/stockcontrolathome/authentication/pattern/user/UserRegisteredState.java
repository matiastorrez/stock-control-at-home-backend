package com.stockcontrolathome.authentication.pattern.user;

import com.stockcontrolathome.authentication.entity.User;
import com.stockcontrolathome.authentication.enums.UserState;
import com.stockcontrolathome.authentication.exception.ResendConfirmRegistrationTokenException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class UserRegisteredState extends UserLoginState{


    @Override
    public void checkState(User user) {
        log.info("Usuario se puede loguear debido a que ya confirmo registro");
    }

    @Override
    public UserState getState() {
        return UserState.REGISTRADO;
    }

    @Override
    public void resendToken(User user) {
        log.info("El usuario ya esta registrado por eso no se puede reenviar el token");
        throw new ResendConfirmRegistrationTokenException("No se puede reenviar el token de registro");
    }
}
