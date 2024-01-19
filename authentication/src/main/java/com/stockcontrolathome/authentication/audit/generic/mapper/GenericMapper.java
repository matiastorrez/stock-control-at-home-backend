package com.stockcontrolathome.authentication.audit.generic.mapper;


public abstract class GenericMapper<ENTITY, DTO> {

    public abstract ENTITY toEntity(DTO dto);

}
