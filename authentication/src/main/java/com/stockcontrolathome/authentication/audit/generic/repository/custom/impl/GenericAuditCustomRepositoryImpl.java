package com.stockcontrolathome.authentication.audit.generic.repository.custom.impl;

import com.stockcontrolathome.authentication.audit.generic.entity.GenericAudit;
import com.stockcontrolathome.authentication.audit.generic.enums.GenericAuditEnum;
import com.stockcontrolathome.authentication.audit.generic.repository.GenericAuditRepository;
import com.stockcontrolathome.authentication.audit.generic.repository.custom.GenericAuditCustomRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

//
@Repository
@AllArgsConstructor
public class GenericAuditCustomRepositoryImpl
        <
            ENTITY extends GenericAudit<STATE_TYPE>,
            STATE_TYPE extends GenericAuditEnum,
            ID
        >
        implements GenericAuditCustomRepository<ENTITY, STATE_TYPE> {


    private GenericAuditRepository<ENTITY,STATE_TYPE,ID> genericAuditRepository;

    @Override
    public void saveGenericAudit(ENTITY genericAudit) {
        this.genericAuditRepository.save(genericAudit);
    }


}
