package com.stockcontrolathome.authentication.config.authentication.login;

import com.stockcontrolathome.authentication.entity.User;
import com.stockcontrolathome.authentication.enums.UserState;
import com.stockcontrolathome.authentication.exception.NonUserWithThisEmailException;
import com.stockcontrolathome.authentication.pattern.user.UserLoginState;
import com.stockcontrolathome.authentication.pattern.user.UserLoginStateFactory;
import com.stockcontrolathome.authentication.repository.user.custom.UserRepositoryCustom;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
@Slf4j
public class UserDetailServiceImpl implements UserDetailsService {


    private final UserRepositoryCustom userRepositoryCustom;

    private final UserLoginStateFactory userLoginStateFactory;

    public static final String BAD_CREDENTIALS = "Algunas de sus credenciales son incorrectas";


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepositoryCustom.getUserByEmail(email).orElseThrow(() -> {
            throw new NonUserWithThisEmailException(BAD_CREDENTIALS);
        });

        UserState state = user.getState();
        UserLoginState userLoginState = this.userLoginStateFactory.getState(state);
        userLoginState.checkState(user);

        return MainUser.build(user);
    }


}
