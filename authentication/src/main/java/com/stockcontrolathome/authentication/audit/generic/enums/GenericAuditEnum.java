package com.stockcontrolathome.authentication.audit.generic.enums;

public class GenericAuditEnum<T> {


    protected final T value;

    protected GenericAuditEnum(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }
}
