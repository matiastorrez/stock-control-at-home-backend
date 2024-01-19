package com.stockcontrolathome.authentication.audit.generic.service;


import com.stockcontrolathome.authentication.audit.generic.enums.GenericAuditEnum;

public interface GenericAuditService<DTO, STATE_TYPE> {

    void saveGenericAudit(DTO genericAuditDTO, GenericAuditEnum<STATE_TYPE> genericAuditEnum);
}
