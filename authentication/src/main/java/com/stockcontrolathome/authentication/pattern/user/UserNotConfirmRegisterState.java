package com.stockcontrolathome.authentication.pattern.user;

import com.stockcontrolathome.authentication.dto.confirmregistrationtoken.response.ConfirmRegistrationTokenResponse;
import com.stockcontrolathome.authentication.entity.User;
import com.stockcontrolathome.authentication.enums.EConfirmRegistrationTokenState;
import com.stockcontrolathome.authentication.enums.UserState;
import com.stockcontrolathome.authentication.exception.NeedToConfirmBeforeLoginException;
import com.stockcontrolathome.authentication.service.ConfirmRegistrationTokenService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Slf4j
@AllArgsConstructor
public class UserNotConfirmRegisterState extends UserLoginState{

    private final ConfirmRegistrationTokenService confirmRegistrationTokenService;


    @Override
    public void checkState(User user) {

        ConfirmRegistrationTokenResponse confirmRegistrationTokenResponse = confirmRegistrationTokenService.getConfirmRegistrationTokenByEmail(user.getEmail());
        LocalDateTime now = LocalDateTime.now();
        if(confirmRegistrationTokenResponse.getExpiredDate().isAfter(now)){
            throw new NeedToConfirmBeforeLoginException("Su cuenta no esta habilitada todavìa, colocá el token que se le mando al email");
        }else{
            this.confirmRegistrationTokenService.renewConfirmRegistrationToken(user.getEmail(), confirmRegistrationTokenResponse.getToken(), EConfirmRegistrationTokenState.ELIMINADO_POR_RENOVACION );
            throw new NeedToConfirmBeforeLoginException("Su cuenta no esta habilitada todavìa, ademas su token se venció, le volvimos a mandar el token a su email");
        }

    }

    @Override
    public UserState getState() {
        return UserState.FALTA_CONFIRMAR_REGISTRO;
    }

}
