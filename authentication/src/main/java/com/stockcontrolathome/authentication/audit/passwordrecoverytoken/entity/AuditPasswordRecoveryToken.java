package com.stockcontrolathome.authentication.audit.passwordrecoverytoken.entity;

import com.stockcontrolathome.authentication.audit.generic.entity.GenericAudit;
import com.stockcontrolathome.authentication.audit.passwordrecoverytoken.enums.EAuditPasswordRecoveryToken;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "AuditPasswordRecoveryToken")
public class AuditPasswordRecoveryToken extends GenericAudit<EAuditPasswordRecoveryToken> {

    @Column(columnDefinition = "TEXT", nullable = false)
    private String token;

    @Column(name = "created_date")
    private LocalDateTime createdDate;

    @Column(name = "expired_date", nullable = false)
    private LocalDateTime expiredDate;

    @Column(name = "email", nullable = false)
    private String email;

}
