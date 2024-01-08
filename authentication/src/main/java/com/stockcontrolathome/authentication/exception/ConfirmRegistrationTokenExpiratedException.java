package com.stockcontrolathome.authentication.exception;


public class ConfirmRegistrationTokenExpiratedException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    public ConfirmRegistrationTokenExpiratedException(String message) {
        super(message);
    }

}
