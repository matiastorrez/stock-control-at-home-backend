package com.stockcontrolathome.authentication.audit.generic.entity;

import com.stockcontrolathome.authentication.audit.generic.enums.GenericAuditEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

//La idea del generico STATE_TYPE es que se coloque el tipo de dato que sera el atrbiuto state
//ademas ese atributo debe ser de un sub tipo de interface GenericAuditEnum
@Getter
@Setter
@NoArgsConstructor
@MappedSuperclass
public class GenericAudit <STATE_TYPE extends GenericAuditEnum> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "state", nullable = false)
    @Enumerated(EnumType.STRING)
    private STATE_TYPE state;

    @Column(name = "sentenced_date",nullable = false)
    private LocalDateTime sentencedDate;

}
