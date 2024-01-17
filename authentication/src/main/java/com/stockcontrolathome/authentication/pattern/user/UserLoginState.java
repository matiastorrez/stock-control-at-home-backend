package com.stockcontrolathome.authentication.pattern.user;

import com.stockcontrolathome.authentication.entity.User;
import com.stockcontrolathome.authentication.enums.UserState;

public abstract class UserLoginState {

    public abstract void checkState(User user);

    public abstract UserState getState();

    public abstract void resendToken(User user);

}
