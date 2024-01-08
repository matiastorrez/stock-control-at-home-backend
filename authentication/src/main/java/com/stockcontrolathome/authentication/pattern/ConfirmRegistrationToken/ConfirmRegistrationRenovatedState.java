package com.stockcontrolathome.authentication.pattern.ConfirmRegistrationToken;


import com.stockcontrolathome.authentication.enums.EConfirmRegistrationTokenState;
import com.stockcontrolathome.authentication.exception.ConfirmRegistrationTokenExpiratedException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ConfirmRegistrationRenovatedState extends ConfirmRegistrationTokenState{


    @Override
    public void confirm() {
        throw new ConfirmRegistrationTokenExpiratedException("Este token se renovó para este usuario y ya no es utilizable");
    }

    @Override
    public EConfirmRegistrationTokenState getState() {
        return EConfirmRegistrationTokenState.ELIMINADO_POR_RENOVACION;
    }
}
