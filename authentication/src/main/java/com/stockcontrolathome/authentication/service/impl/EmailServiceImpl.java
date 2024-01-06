package com.stockcontrolathome.authentication.service.impl;

import com.stockcontrolathome.authentication.exception.NotificationFailureException;
import com.stockcontrolathome.authentication.service.NotificationService;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;


@Service
public class EmailServiceImpl implements NotificationService {

    private static final Logger LOGGER = LoggerFactory
            .getLogger(EmailServiceImpl.class);

    @Autowired
    private JavaMailSender mailSender;

    @Override
    public void send(String subject, String to, String message) {
        try {

            MimeMessage emailMessage = mailSender.createMimeMessage();
            emailMessage.setRecipients(Message.RecipientType.TO, to);
            emailMessage.setSubject(subject);
            emailMessage.setContent(message,"text/html");
            mailSender.send(emailMessage);

        } catch (MessagingException e) {
            LOGGER.error("failed to send email", e);
            throw new NotificationFailureException("failed to send email");
        }
    }
}
