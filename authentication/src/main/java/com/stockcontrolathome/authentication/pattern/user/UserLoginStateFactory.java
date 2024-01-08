package com.stockcontrolathome.authentication.pattern.user;

import com.stockcontrolathome.authentication.enums.UserState;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class UserLoginStateFactory {

    private final Map<UserState, UserLoginState> strategies;

    public UserLoginStateFactory(List<UserLoginState> strategyList) {
        this.strategies = new HashMap<>();
        for (UserLoginState strategy : strategyList) {
            strategies.put(strategy.getState(), strategy);
        }
    }

    public UserLoginState getState(UserState state) {
        return strategies.get(state);
    }

}
