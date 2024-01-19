package com.stockcontrolathome.authentication.auditory.confirmregistrationtoken.entity;

import com.stockcontrolathome.authentication.auditory.confirmregistrationtoken.enums.EAuditoryConfirmRegistrationTokenState;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "AuditoryConfirmRegistrationToken")
public class AuditoryConfirmRegistrationToken {


    @Id
    @Column(name = "id_auditory_confirm_registration_token")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAuditoryConfirmRegistrationToken;

    @Column(name = "id_confirm_registration_token")
    private Long idConfirmRegistrationToken;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String token;

    @Column(name = "created_date")
    private LocalDateTime createdDate;

    @Column(name = "expired_date", nullable = false)
    private LocalDateTime expiredDate;

    @Column(name = "sentenced_date",nullable = false)
    private LocalDateTime sentencedDate;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "state", nullable = false)
    private EAuditoryConfirmRegistrationTokenState state;

    @Column(name = "email", nullable = false)
    private String email;


}
