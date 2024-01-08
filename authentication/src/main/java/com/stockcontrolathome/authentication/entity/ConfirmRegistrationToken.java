package com.stockcontrolathome.authentication.entity;

import com.stockcontrolathome.authentication.enums.EConfirmRegistrationTokenState;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
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
    private LocalDateTime createdDate;

    @Column(name = "expired_date", nullable = false)
    private LocalDateTime expiredDate;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "state", nullable = false)
    private EConfirmRegistrationTokenState state;

    @Column(name = "email", nullable = false)
    private String email;


}
