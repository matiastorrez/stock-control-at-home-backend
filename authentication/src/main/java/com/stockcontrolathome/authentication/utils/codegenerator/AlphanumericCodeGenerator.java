package com.stockcontrolathome.authentication.utils.codegenerator;

import org.springframework.stereotype.Component;

import java.security.SecureRandom;

@Component
public class AlphanumericCodeGenerator implements  CodeGenerator{


    @Override
    public String generateRandomCode() {
        // Longitud del código
        int codeLength = 6;

        // Caracteres permitidos en el código
        String allowedChars = "ABCDEFGHJKLMNOPQRSTUVWXYZabcdefghjkmnopqrstuvwxyz0123456789";

        // Generar código aleatorio
        StringBuilder randomCode = new StringBuilder();
        SecureRandom secureRandom = new SecureRandom();

        for (int i = 0; i < codeLength; i++) {
            int randomIndex = secureRandom.nextInt(allowedChars.length());
            char randomChar = allowedChars.charAt(randomIndex);
            randomCode.append(randomChar);
        }

        return randomCode.toString();
    }
}
