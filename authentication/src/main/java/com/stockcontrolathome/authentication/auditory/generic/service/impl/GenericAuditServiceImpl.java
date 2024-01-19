package com.stockcontrolathome.authentication.auditory.generic.service.impl;

import com.stockcontrolathome.authentication.auditory.generic.entity.GenericAudit;
import com.stockcontrolathome.authentication.auditory.generic.enums.GenericAuditEnum;
import com.stockcontrolathome.authentication.auditory.generic.mapper.GenericMapper;
import com.stockcontrolathome.authentication.auditory.generic.repository.custom.GenericAuditCustomRepository;
import com.stockcontrolathome.authentication.auditory.generic.service.GenericAuditService;
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
        implements GenericAuditService<DTO, STATE_TYPE>
{

    @Autowired
    private  GenericAuditCustomRepository<ENTITY, STATE, STATE_TYPE> genericAuditCustomRepository;

    private GenericMapper<ENTITY, DTO> genericMapper;



    @Override
    public void saveGenericAudit(DTO genericAuditDTO, GenericAuditEnum<STATE_TYPE> genericAuditEnum) {

        ENTITY entity = this.genericMapper.toEntity(genericAuditDTO);
        entity.setSentencedDate(LocalDateTime.now());
        entity.setState(genericAuditEnum.getValue());

        this.genericAuditCustomRepository.saveGenericAudit(entity);

    }

    public void setGenericMapper(GenericMapper<ENTITY, DTO> genericMapper){
        this.genericMapper = genericMapper;
    }
}
