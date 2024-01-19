package com.stockcontrolathome.authentication.auditory.generic.service;


import com.stockcontrolathome.authentication.auditory.generic.enums.GenericAuditEnum;

public interface GenericAuditService<DTO, STATE_TYPE> {

    void saveGenericAudit(DTO genericAuditDTO, GenericAuditEnum<STATE_TYPE> genericAuditEnum);
}
