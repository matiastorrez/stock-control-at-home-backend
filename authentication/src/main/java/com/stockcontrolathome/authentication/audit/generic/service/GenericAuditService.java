package com.stockcontrolathome.authentication.audit.generic.service;


import com.stockcontrolathome.authentication.audit.generic.enums.GenericAuditEnum;

public interface GenericAuditService<DTO> {

    void saveGenericAudit(DTO genericAuditDTO);
}
