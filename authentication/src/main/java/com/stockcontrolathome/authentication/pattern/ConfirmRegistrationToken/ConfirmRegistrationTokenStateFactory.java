package com.stockcontrolathome.authentication.pattern.ConfirmRegistrationToken;

import com.stockcontrolathome.authentication.enums.EConfirmRegistrationTokenState;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ConfirmRegistrationTokenStateFactory {

    private final Map<EConfirmRegistrationTokenState, ConfirmRegistrationTokenState> strategies;

    public ConfirmRegistrationTokenStateFactory(List<ConfirmRegistrationTokenState> strategyList) {
        this.strategies = new HashMap<>();
        for (ConfirmRegistrationTokenState strategy : strategyList) {
            strategies.put(strategy.getState(), strategy);
        }
    }

    public ConfirmRegistrationTokenState getState(EConfirmRegistrationTokenState state) {
        return strategies.get(state);
    }


}
