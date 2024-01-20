package com.stockcontrolathome.authentication.audit.generic.service;


import com.stockcontrolathome.authentication.audit.generic.dto.request.GenericAuditDTORequest;
import com.stockcontrolathome.authentication.audit.generic.enums.GenericAuditEnum;

public interface GenericAuditService<DTO extends GenericAuditDTORequest<STATE_TYPE>, STATE_TYPE extends GenericAuditEnum>  {

    void saveGenericAudit(DTO genericAuditDTO);
}
