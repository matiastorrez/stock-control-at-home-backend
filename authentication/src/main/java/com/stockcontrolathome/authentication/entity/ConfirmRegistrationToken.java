package com.stockcontrolathome.authentication.entity;

import com.stockcontrolathome.authentication.enums.ConfirmRegistrationTokenState;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "ConfirmRegistrationToken")
public class ConfirmRegistrationToken {


    @Id
    @Column(name = "id_confirm_registration_token")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idConfirmRegistrationToken;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String token;

    @Column(name = "created_date")
    @CreationTimestamp
    private LocalDateTime createdDate;

    @Column(name = "expired_date", nullable = false)
    private LocalDateTime expiredDate;

    @Column(name = "confirmation_date")
    private LocalDateTime confirmationDate;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "state", nullable = false)
    private ConfirmRegistrationTokenState state;

    @Column(name = "email", nullable = false)
    private String email;


}
