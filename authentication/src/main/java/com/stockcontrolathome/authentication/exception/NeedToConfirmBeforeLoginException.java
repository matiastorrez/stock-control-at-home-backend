package com.stockcontrolathome.authentication.exception;


public class NeedToConfirmBeforeLoginException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    public NeedToConfirmBeforeLoginException(String message) {
        super(message);
    }

}
