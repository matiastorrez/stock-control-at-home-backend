package com.stockcontrolathome.authentication.service;

public interface NotificationService {

    void send(String subject, String to, String message);

    String buildRegistrationMessage(String name, String token);

}
