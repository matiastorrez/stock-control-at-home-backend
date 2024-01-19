package com.stockcontrolathome.authentication.auditory.generic.mapper;


public abstract class GenericMapper<ENTITY, DTO> {

    public abstract ENTITY toEntity(DTO dto);

}
