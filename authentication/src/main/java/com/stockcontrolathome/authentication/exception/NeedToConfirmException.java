package com.stockcontrolathome.authentication.exception;


public class NeedToConfirmException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    public NeedToConfirmException(String message) {
        super(message);
    }

}
