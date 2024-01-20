package com.stockcontrolathome.authentication.dto.user.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RecoverPasswordRequest {

    @NotBlank(message = "El email no puede ser nulo ni vacio")
    private String email;

}
