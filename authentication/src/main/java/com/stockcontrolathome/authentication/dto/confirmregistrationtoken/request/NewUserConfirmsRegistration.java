package com.stockcontrolathome.authentication.dto.confirmregistrationtoken.request;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class NewUserConfirmsRegistration {

    @Pattern(regexp = "^.+@.+$", message = "El correo electrónico ingresado no es válido. Asegúrate de incluir el símbolo '@' en el medio")
    @NotNull(message = "El email no puede ser nulo")
    private String email;

    @NotBlank(message = "La password no puede ser nulo ni vacío")
    private String password;

    @NotBlank(message = "El codigo no puede ser nulo ni vacío")
    private String token;

}
