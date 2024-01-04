package com.stockcontrolathome.authentication.dto.user.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RegisterUserRequest {

    @NotBlank(message = "El nombre no puede ser nulo ni vacío")
    private String name;

    @NotBlank(message = "El apellido no puede ser nulo ni vacío")
    private String lastname;

    @Pattern(regexp = "^.+@.+$", message = "El correo electrónico ingresado no es válido. Asegúrate de incluir el símbolo '@' en el medio")
    @NotNull(message = "El email no puede ser nulo")
    private String email;

    @Pattern(regexp = "^(?=.*\\d)(?=.*[A-Z]).{8,}$", message = " La password debe tener un mínimo de 8 caracteres, de los cuales debe haber al menos una mayúscula y un número")
    @NotNull(message = "La contraseña no puede ser nulo")
    private String password;

}

