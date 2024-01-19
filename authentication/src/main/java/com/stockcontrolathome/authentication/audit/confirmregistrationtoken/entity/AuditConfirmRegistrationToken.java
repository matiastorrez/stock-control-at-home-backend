package com.stockcontrolathome.authentication.audit.confirmregistrationtoken.entity;

import com.stockcontrolathome.authentication.audit.generic.entity.GenericAudit;
import com.stockcontrolathome.authentication.audit.confirmregistrationtoken.enums.EAuditConfirmRegistrationToken;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "AuditConfirm")
public class AuditConfirmRegistrationToken extends GenericAudit<EAuditConfirmRegistrationToken, String> {

    @Column(columnDefinition = "TEXT", nullable = false)
    private String token;

    @Column(name = "created_date")
    private LocalDateTime createdDate;

    @Column(name = "expired_date", nullable = false)
    private LocalDateTime expiredDate;

    @Column(name = "email", nullable = false)
    private String email;
}
