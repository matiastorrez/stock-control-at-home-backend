package com.stockcontrolathome.authentication.config.authentication.resendconfirmregister.service;

import com.stockcontrolathome.authentication.config.authentication.resendconfirmregister.authentication.ResendConfirmRegisterAuthenticationToken;
import com.stockcontrolathome.authentication.config.authentication.resendconfirmregister.userdetails.ResendConfirmRegisterUser;
import com.stockcontrolathome.authentication.entity.User;
import com.stockcontrolathome.authentication.enums.UserState;
import com.stockcontrolathome.authentication.exception.NonUserWithThisEmailException;
import com.stockcontrolathome.authentication.pattern.user.UserLoginState;
import com.stockcontrolathome.authentication.pattern.user.UserLoginStateFactory;
import com.stockcontrolathome.authentication.repository.user.custom.UserRepositoryCustom;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
@Slf4j
public class ResendConfirmRegisterTokenService  {

    private final UserRepositoryCustom userRepositoryCustom;

    private final UserLoginStateFactory userLoginStateFactory;

    public static final String BAD_CREDENTIALS = "Algunas de sus credenciales son incorrectas";

    public ResendConfirmRegisterAuthenticationToken authenticate(ResendConfirmRegisterAuthenticationToken authentication, PasswordEncoder passwordEncoder) {
        User user = userRepositoryCustom.getUserByEmail(authentication.getEmail()).orElseThrow(() -> {
            throw new NonUserWithThisEmailException(BAD_CREDENTIALS);
        });

        if(!passwordEncoder.matches(authentication.getPassword(),user.getPassword())){
            throw new NonUserWithThisEmailException(BAD_CREDENTIALS);
        }

        UserState state = user.getState();
        UserLoginState userLoginState = this.userLoginStateFactory.getState(state);
        userLoginState.resendToken(user);

        ResendConfirmRegisterUser ru = ResendConfirmRegisterUser.build(user);

        return new ResendConfirmRegisterAuthenticationToken(ru);

    }

}
