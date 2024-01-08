package com.stockcontrolathome.authentication.pattern.ConfirmRegistrationToken;


import com.stockcontrolathome.authentication.enums.EConfirmRegistrationTokenState;
import com.stockcontrolathome.authentication.exception.ConfirmRegistrationTokenExpiratedException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ConfirmRegistrationExpiratedState extends ConfirmRegistrationTokenState{


    @Override
    public void confirm() {
        throw new ConfirmRegistrationTokenExpiratedException("Este token expiró para este usuario, pida otro nuevo");
    }

    @Override
    public EConfirmRegistrationTokenState getState() {
        return EConfirmRegistrationTokenState.ELIMINADO_POR_EXPIRACION;
    }
}
