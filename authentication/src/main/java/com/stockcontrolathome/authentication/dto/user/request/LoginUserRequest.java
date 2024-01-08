package com.stockcontrolathome.authentication.dto.user.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;


@Getter
@AllArgsConstructor
public class LoginUserRequest {

    @NotBlank(message = "El email no puede ser nulo ni vacio")
    private String email;

    @NotBlank(message = "El password no puede ser nulo ni vacio")
    private String password;

}
