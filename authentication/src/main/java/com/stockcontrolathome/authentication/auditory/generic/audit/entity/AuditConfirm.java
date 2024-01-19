package com.stockcontrolathome.authentication.auditory.generic.audit.entity;

import com.stockcontrolathome.authentication.auditory.confirmregistrationtoken.enums.EAuditoryConfirmRegistrationTokenState;
import com.stockcontrolathome.authentication.auditory.generic.entity.GenericAudit;
import com.stockcontrolathome.authentication.auditory.generic.enums.ConfirmRegistrationTokenEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "AuditConfirm")
public class AuditConfirm extends GenericAudit<ConfirmRegistrationTokenEnum, String> {

    @Column(columnDefinition = "TEXT", nullable = false)
    private String token;

    @Column(name = "created_date")
    private LocalDateTime createdDate;

    @Column(name = "expired_date", nullable = false)
    private LocalDateTime expiredDate;

    @Column(name = "email", nullable = false)
    private String email;
}
