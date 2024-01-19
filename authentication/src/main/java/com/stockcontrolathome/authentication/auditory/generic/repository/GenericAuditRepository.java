package com.stockcontrolathome.authentication.auditory.generic.repository;

import com.stockcontrolathome.authentication.auditory.generic.entity.GenericAudit;
import com.stockcontrolathome.authentication.auditory.generic.enums.GenericAuditEnum;
import org.springframework.data.jpa.repository.JpaRepository;
//S es la entidad que exitenede de GenericAudit, T es el Enum que se guardara en la entidad, Id es el tipo de Id de la entidad que exitende
//de GenericAudit
public interface GenericAuditRepository
        <
            ENTITY extends GenericAudit<STATE, STATE_TYPE>,
            STATE extends GenericAuditEnum<STATE_TYPE>,
            STATE_TYPE,
            ID
        >
        extends JpaRepository<ENTITY, ID> {
}
