package com.stockcontrolathome.authentication.auditory.generic.enums;

public class ConfirmRegistrationTokenEnum extends GenericAuditEnum<String>{

    public static final ConfirmRegistrationTokenEnum ELIMINADO_POR_EXPIRACION = new ConfirmRegistrationTokenEnum("ELIMINADO_POR_EXPIRACION");
    public static final ConfirmRegistrationTokenEnum ELIMINADO_POR_RENOVACION = new ConfirmRegistrationTokenEnum("ELIMINADO_POR_RENOVACION");
    public static final ConfirmRegistrationTokenEnum ELIMINADO_POR_EXPIRACION_Y_RENOVACION = new ConfirmRegistrationTokenEnum("ELIMINADO_POR_EXPIRACION_Y_RENOVACION");
    public static final ConfirmRegistrationTokenEnum CONFIRMADO = new ConfirmRegistrationTokenEnum("CONFIRMADO");


    private ConfirmRegistrationTokenEnum(String value) {
        super(value);
    }

}
