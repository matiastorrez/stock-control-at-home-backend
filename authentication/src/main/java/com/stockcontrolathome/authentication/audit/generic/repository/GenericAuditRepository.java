package com.stockcontrolathome.authentication.audit.generic.repository;

import com.stockcontrolathome.authentication.audit.generic.entity.GenericAudit;
import com.stockcontrolathome.authentication.audit.generic.enums.GenericAuditEnum;
import org.springframework.data.jpa.repository.JpaRepository;
//ENTITY es la entidad que extiende de GenericAudit y que recibe un generico STATE_TYPE para colocarle el tipo de atributo al estado que tiene GenericAudit,
//STATE_TYPE es el tipo del atributo que tendra el estado como mencionamos anteriormente, y es necesario que este tipo sea un subtipo de la interface GenericAuditEnum
//Id es el tipo de Id de la entidad que extiende de GenericAudit
public interface GenericAuditRepository
        <
            ENTITY extends GenericAudit<STATE_TYPE>,
            STATE_TYPE extends GenericAuditEnum,
            ID
        >
        extends JpaRepository<ENTITY, ID> {
}
