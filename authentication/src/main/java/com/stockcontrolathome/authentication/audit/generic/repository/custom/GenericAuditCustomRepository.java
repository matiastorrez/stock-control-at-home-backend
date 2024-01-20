package com.stockcontrolathome.authentication.audit.generic.repository.custom;

import com.stockcontrolathome.authentication.audit.generic.entity.GenericAudit;
import com.stockcontrolathome.authentication.audit.generic.enums.GenericAuditEnum;

public interface GenericAuditCustomRepository
        <
            ENTITY extends GenericAudit<STATE_TYPE>,
            STATE_TYPE extends GenericAuditEnum
        >
{


    void saveGenericAudit(ENTITY genericAudit);

}
