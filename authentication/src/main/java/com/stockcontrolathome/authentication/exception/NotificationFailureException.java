package com.stockcontrolathome.authentication.exception;


public class NotificationFailureException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    public NotificationFailureException(String message) {
        super(message);
    }

}
