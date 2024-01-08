package com.stockcontrolathome.authentication.pattern.ConfirmRegistrationToken;

import com.stockcontrolathome.authentication.enums.EConfirmRegistrationTokenState;

public abstract class ConfirmRegistrationTokenState {

    public abstract void confirm();

    public abstract EConfirmRegistrationTokenState getState();

}
