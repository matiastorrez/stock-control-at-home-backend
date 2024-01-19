package com.stockcontrolathome.authentication.audit.generic.repository.custom;

import com.stockcontrolathome.authentication.audit.generic.entity.GenericAudit;
import com.stockcontrolathome.authentication.audit.generic.enums.GenericAuditEnum;

public interface GenericAuditCustomRepository
        <
            ENTITY extends GenericAudit<STATE, STATE_TYPE>,
            STATE extends GenericAuditEnum<STATE_TYPE>,
            STATE_TYPE
        >
{


    void saveGenericAudit(ENTITY genericAudit);

}
