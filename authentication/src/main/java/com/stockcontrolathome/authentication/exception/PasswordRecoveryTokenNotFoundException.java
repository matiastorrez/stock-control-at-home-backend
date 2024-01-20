package com.stockcontrolathome.authentication.exception;


public class PasswordRecoveryTokenNotFoundException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    public PasswordRecoveryTokenNotFoundException(String message) {
        super(message);
    }

}
