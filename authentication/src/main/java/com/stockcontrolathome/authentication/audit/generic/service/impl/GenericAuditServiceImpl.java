package com.stockcontrolathome.authentication.audit.generic.service.impl;

import com.stockcontrolathome.authentication.audit.generic.dto.request.GenericAuditDTORequest;
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
                DTO extends GenericAuditDTORequest<STATE_TYPE>,
                ENTITY extends GenericAudit<STATE_TYPE>,
                STATE_TYPE extends GenericAuditEnum
                >
        implements GenericAuditService<DTO, STATE_TYPE> {

    @Autowired
    private GenericAuditCustomRepository<ENTITY, STATE_TYPE> genericAuditCustomRepository;

    private GenericMapper<ENTITY, DTO> genericMapper;


    public void setGenericMapper(GenericMapper<ENTITY, DTO> genericMapper) {
        this.genericMapper = genericMapper;
    }


    @Override
    public void saveGenericAudit(DTO genericAuditDTO) {
        ENTITY entity = this.genericMapper.toEntity(genericAuditDTO);
        entity.setSentencedDate(LocalDateTime.now());
        this.genericAuditCustomRepository.saveGenericAudit(entity);
    }
}
