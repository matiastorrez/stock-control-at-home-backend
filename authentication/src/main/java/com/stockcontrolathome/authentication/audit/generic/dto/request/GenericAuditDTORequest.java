package com.stockcontrolathome.authentication.audit.generic.dto.request;

import com.stockcontrolathome.authentication.audit.generic.enums.GenericAuditEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

//La idea del generico STATE_TYPE es que el estado de ese dto sea el mismo tipo que la entidad que extienda de GenericAudit
@Getter
@AllArgsConstructor
public class GenericAuditDTORequest<STATE_TYPE extends GenericAuditEnum> {

    private STATE_TYPE state;
}
