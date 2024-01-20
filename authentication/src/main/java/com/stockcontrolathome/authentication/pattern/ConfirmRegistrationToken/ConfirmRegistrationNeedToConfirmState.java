package com.stockcontrolathome.authentication.pattern.ConfirmRegistrationToken;


import com.stockcontrolathome.authentication.enums.EConfirmRegistrationTokenState;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ConfirmRegistrationNeedToConfirmState extends ConfirmRegistrationTokenState{


    @Override
    public void checkState() {
        log.info("Este token no fue utilizado todavía");
    }

    @Override
    public EConfirmRegistrationTokenState getState() {
        return EConfirmRegistrationTokenState.FALTA_CONFIRMAR;
    }
}
