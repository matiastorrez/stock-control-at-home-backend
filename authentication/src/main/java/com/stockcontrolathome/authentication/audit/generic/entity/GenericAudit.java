package com.stockcontrolathome.authentication.audit.generic.entity;

import com.stockcontrolathome.authentication.audit.generic.enums.GenericAuditEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@MappedSuperclass
public class GenericAudit<STATE extends GenericAuditEnum<STATE_TYPE>, STATE_TYPE> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "state", nullable = false)
    private STATE_TYPE state;

    @Column(name = "sentenced_date",nullable = false)
    private LocalDateTime sentencedDate;

}
