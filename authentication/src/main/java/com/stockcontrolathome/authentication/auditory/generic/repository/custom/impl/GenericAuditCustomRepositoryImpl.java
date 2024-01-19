package com.stockcontrolathome.authentication.auditory.generic.repository.custom.impl;

import com.stockcontrolathome.authentication.auditory.generic.entity.GenericAudit;
import com.stockcontrolathome.authentication.auditory.generic.enums.GenericAuditEnum;
import com.stockcontrolathome.authentication.auditory.generic.repository.GenericAuditRepository;
import com.stockcontrolathome.authentication.auditory.generic.repository.custom.GenericAuditCustomRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class GenericAuditCustomRepositoryImpl
        <
            ENTITY extends GenericAudit<STATE, STATE_TYPE>,
            STATE extends GenericAuditEnum<STATE_TYPE>,
            STATE_TYPE,
            ID
        >
        implements GenericAuditCustomRepository<ENTITY, STATE, STATE_TYPE> {


    private GenericAuditRepository<ENTITY,STATE,STATE_TYPE,ID> genericAuditRepository;

    @Override
    public void saveGenericAudit(ENTITY genericAudit) {
        this.genericAuditRepository.save(genericAudit);

    }


}
