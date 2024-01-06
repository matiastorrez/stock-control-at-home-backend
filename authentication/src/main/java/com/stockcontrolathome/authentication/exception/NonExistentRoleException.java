package com.stockcontrolathome.authentication.exception;


public class NonExistentRoleException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    public NonExistentRoleException(String message) {
        super(message);
    }

}
