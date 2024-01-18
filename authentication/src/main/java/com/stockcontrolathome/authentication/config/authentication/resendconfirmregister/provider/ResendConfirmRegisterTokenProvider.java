package com.stockcontrolathome.authentication.config.authentication.resendconfirmregister.provider;

import com.stockcontrolathome.authentication.config.authentication.resendconfirmregister.authentication.ResendConfirmRegisterAuthenticationToken;
import com.stockcontrolathome.authentication.config.authentication.resendconfirmregister.service.ResendConfirmRegisterTokenService;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;

public class ResendConfirmRegisterTokenProvider implements AuthenticationProvider {

    private ResendConfirmRegisterTokenService resendTokenUserDetailService;

    private PasswordEncoder passwordEncoder;


    public ResendConfirmRegisterTokenProvider(ResendConfirmRegisterTokenService resendTokenUserDetailService, PasswordEncoder passwordEncoder) {
        this.resendTokenUserDetailService = resendTokenUserDetailService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
         return this.resendTokenUserDetailService.authenticate((ResendConfirmRegisterAuthenticationToken) authentication, passwordEncoder);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(ResendConfirmRegisterAuthenticationToken.class);
    }
}
