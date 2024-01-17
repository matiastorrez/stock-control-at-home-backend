package com.stockcontrolathome.authentication.pattern.ConfirmRegistrationToken;

import com.stockcontrolathome.authentication.enums.EConfirmRegistrationTokenState;

public abstract class ConfirmRegistrationTokenState {

    public abstract void checkState();

    public abstract EConfirmRegistrationTokenState getState();

}
