package com.stockcontrolathome.authentication.exception;


public class NonUserWithThisEmailException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    public NonUserWithThisEmailException(String message) {
        super(message);
    }

}
