package com.stockcontrolathome.authentication.auditory.generic.repository.custom;

import com.stockcontrolathome.authentication.auditory.generic.entity.GenericAudit;
import com.stockcontrolathome.authentication.auditory.generic.enums.GenericAuditEnum;

public interface GenericAuditCustomRepository
        <
            ENTITY extends GenericAudit<STATE, STATE_TYPE>,
            STATE extends GenericAuditEnum<STATE_TYPE>,
            STATE_TYPE
        >
{


    void saveGenericAudit(ENTITY genericAudit);

}
