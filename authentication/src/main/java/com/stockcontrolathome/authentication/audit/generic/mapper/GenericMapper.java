package com.stockcontrolathome.authentication.audit.generic.mapper;

//debido a que nuestro service necesita un mapper para transofrmar de DTO a entidad
// creamos esta clase para que la hereden los mappers, utilizando la entidad y DTO que crean necesarias.
//En esta caso estamos usando mapstruct asi que utilizaremos anotaciones
public abstract class GenericMapper<ENTITY, DTO> {

    public abstract ENTITY toEntity(DTO dto);

}
