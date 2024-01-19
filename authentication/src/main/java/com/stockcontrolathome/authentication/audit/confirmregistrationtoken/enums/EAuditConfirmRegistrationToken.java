package com.stockcontrolathome.authentication.audit.confirmregistrationtoken.enums;

import com.stockcontrolathome.authentication.audit.generic.enums.GenericAuditEnum;

public class EAuditConfirmRegistrationToken extends GenericAuditEnum<String> {

    public static final EAuditConfirmRegistrationToken ELIMINADO_POR_EXPIRACION = new EAuditConfirmRegistrationToken("ELIMINADO_POR_EXPIRACION");
    public static final EAuditConfirmRegistrationToken ELIMINADO_POR_RENOVACION = new EAuditConfirmRegistrationToken("ELIMINADO_POR_RENOVACION");
    public static final EAuditConfirmRegistrationToken ELIMINADO_POR_EXPIRACION_Y_RENOVACION = new EAuditConfirmRegistrationToken("ELIMINADO_POR_EXPIRACION_Y_RENOVACION");
    public static final EAuditConfirmRegistrationToken CONFIRMADO = new EAuditConfirmRegistrationToken("CONFIRMADO");


    private EAuditConfirmRegistrationToken(String value) {
        super(value);
    }

}
