package com.stockcontrolathome.authentication.pattern.ConfirmRegistrationToken;


import com.stockcontrolathome.authentication.enums.EConfirmRegistrationTokenState;
import com.stockcontrolathome.authentication.exception.ConfirmRegistrationTokenExpiratedException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ConfirmRegistrationNeedToConfirmState extends ConfirmRegistrationTokenState{


    @Override
    public void confirm() {
        throw new ConfirmRegistrationTokenExpiratedException("Este token ya fue utilizado, no puede utilizarlo");
    }

    @Override
    public EConfirmRegistrationTokenState getState() {
        return EConfirmRegistrationTokenState.FALTA_CONFIRMAR;
    }
}
