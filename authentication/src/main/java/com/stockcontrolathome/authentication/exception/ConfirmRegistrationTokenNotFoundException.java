package com.stockcontrolathome.authentication.exception;


public class ConfirmRegistrationTokenNotFoundException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    public ConfirmRegistrationTokenNotFoundException(String message) {
        super(message);
    }

}
