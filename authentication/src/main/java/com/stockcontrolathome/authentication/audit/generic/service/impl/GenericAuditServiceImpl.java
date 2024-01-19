package com.stockcontrolathome.authentication.audit.generic.service.impl;

import com.stockcontrolathome.authentication.audit.generic.entity.GenericAudit;
import com.stockcontrolathome.authentication.audit.generic.enums.GenericAuditEnum;
import com.stockcontrolathome.authentication.audit.generic.mapper.GenericMapper;
import com.stockcontrolathome.authentication.audit.generic.repository.custom.GenericAuditCustomRepository;
import com.stockcontrolathome.authentication.audit.generic.service.GenericAuditService;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@Setter
public class GenericAuditServiceImpl
        <
        DTO,
        ENTITY extends GenericAudit<STATE, STATE_TYPE>,
        STATE extends GenericAuditEnum<STATE_TYPE>,
        STATE_TYPE
        >
        implements GenericAuditService<DTO>
{

    @Autowired
    private  GenericAuditCustomRepository<ENTITY, STATE, STATE_TYPE> genericAuditCustomRepository;

    private GenericMapper<ENTITY, DTO> genericMapper;



    @Override
    public void saveGenericAudit(DTO genericAuditDTO) {

        ENTITY entity = this.genericMapper.toEntity(genericAuditDTO);
        entity.setSentencedDate(LocalDateTime.now());
        this.genericAuditCustomRepository.saveGenericAudit(entity);

    }

    public void setGenericMapper(GenericMapper<ENTITY, DTO> genericMapper){
        this.genericMapper = genericMapper;
    }
}
