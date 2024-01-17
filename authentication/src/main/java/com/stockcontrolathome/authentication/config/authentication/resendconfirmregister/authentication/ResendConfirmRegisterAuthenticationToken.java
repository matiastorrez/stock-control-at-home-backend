package com.stockcontrolathome.authentication.config.authentication.resendconfirmregister.authentication;

import com.stockcontrolathome.authentication.config.authentication.resendconfirmregister.userdetails.ResendConfirmRegisterUser;
import lombok.Getter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class ResendConfirmRegisterAuthenticationToken implements Authentication {

    private boolean isAuthenticated;

    private ResendConfirmRegisterUser resendConfirmRegisterUser;

    @Getter
    private String email;
    @Getter
    private String password;

    public ResendConfirmRegisterAuthenticationToken(ResendConfirmRegisterUser resendConfirmRegisterUser) {
        this.resendConfirmRegisterUser = resendConfirmRegisterUser;
        this.isAuthenticated = true;
    }

    public ResendConfirmRegisterAuthenticationToken(String email, String password) {
        this.email = email;
        this.password = password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.resendConfirmRegisterUser.getAuthorities();
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getDetails() {
        return this.resendConfirmRegisterUser;
    }

    @Override
    public Object getPrincipal() {
        return this.resendConfirmRegisterUser;
    }

    @Override
    public boolean isAuthenticated() {
        return this.isAuthenticated;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        throw new IllegalArgumentException("Not supported, use constructor");
    }

    @Override
    public String getName() {
        return this.resendConfirmRegisterUser.getUsername();
    }
}
