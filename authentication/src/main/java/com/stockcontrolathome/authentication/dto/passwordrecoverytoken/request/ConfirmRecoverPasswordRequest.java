package com.stockcontrolathome.authentication.dto.passwordrecoverytoken.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ConfirmRecoverPasswordRequest {

    @NotBlank(message = "El email no puede ser nulo ni vacio")
    private String email;

    @NotBlank(message = "El token no puede ser nulo ni vacio")
    private String token;

    @Pattern(regexp = "^(?=.*\\d)(?=.*[A-Z]).{8,}$", message = " La password debe tener un mínimo de 8 caracteres, de los cuales debe haber al menos una mayúscula y un número")
    @NotNull(message = "La contraseña no puede ser nulo")
    private String password;
}
