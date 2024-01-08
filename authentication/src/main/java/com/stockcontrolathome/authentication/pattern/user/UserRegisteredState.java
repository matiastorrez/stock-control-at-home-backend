package com.stockcontrolathome.authentication.pattern.user;

import com.stockcontrolathome.authentication.entity.User;
import com.stockcontrolathome.authentication.enums.UserState;
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
}
