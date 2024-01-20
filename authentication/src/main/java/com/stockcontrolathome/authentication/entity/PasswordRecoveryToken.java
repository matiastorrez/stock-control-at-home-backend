package com.stockcontrolathome.authentication.entity;

import com.stockcontrolathome.authentication.enums.EConfirmRegistrationTokenState;
import com.stockcontrolathome.authentication.enums.EPasswordRecoveryTokenState;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "PasswordRecoveryToken")
public class PasswordRecoveryToken {

    @Id
    @Column(name = "id_password_recovery_token")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPasswordRecoveryToken;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String token;

    @Column(name = "created_date")
    private LocalDateTime createdDate;

    @Column(name = "expired_date", nullable = false)
    private LocalDateTime expiredDate;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "state", nullable = false)
    private EPasswordRecoveryTokenState state;

    @Column(name = "email", nullable = false)
    private String email;

}
