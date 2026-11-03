package com.saas.tourismagency.admin.backoffice.usecase.maintenance.impl;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.maintenance.sector.SectorConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.defaultfield.maintenance.SetFieldDefaultSector;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.maintenance.sector.PersistSectorNumberCallbackImpl;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.maintenance.sector.SectorRepo;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.sector.*;
import com.saas.tourismagency.admin.backoffice.core.context.UserResolver;
import com.saas.tourismagency.admin.backoffice.core.exceptions.MessagesValidation;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.SectorDomain;
import com.saas.tourismagency.admin.backoffice.usecase.maintenance.SectorUseCase;
import dev.mm.javaee.core.dto.PageDTO;
import dev.mm.javaee.core.exceptions.BadRequestException;
import dev.mm.javaee.core.exceptions.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
public class SectorUseCaseImpl implements SectorUseCase {

    @Autowired
    private SectorRepo repo;

    @Autowired
    private SectorConverter converter;

    @Autowired
    private SetFieldDefaultSector setFieldDefault;

    @Autowired
    PersistSectorNumberCallbackImpl persistSectorNumberCallbackImpl;

    @Override
    @Transactional
    public SectorResponse create(SectorCreateRequest request) {
        SectorDomain sectorDomainExist = repo.findByName(request.getName());
        if (sectorDomainExist != null){
            throw new BadRequestException(MessagesValidation.VALIDATION_ENTITY_EXIST_BY_NAME);
        }
        SectorDomain newDomain = request.to();
        newDomain.setIdentify(persistSectorNumberCallbackImpl.generateNumber());
        newDomain.setTenant(UserResolver.resolveUser().getTenant());
        SectorDomain createdDomain = repo.create(newDomain);
        return SectorResponse.from(createdDomain);
    }

    @Override
    @Transactional
    public SectorResponse edit(String id, SectorUpdateRequest request) {
        if (id == null) {
            return null;
        }
        SectorDomain old = repo.findById(id);
        if (old == null) {
            throw new NotFoundException("No sector found with id '%s'.".formatted(id));
        }
        SectorDomain sectorDomainExist = repo.findByName(request.getName());
        if (sectorDomainExist != null && !sectorDomainExist.getId().equals(old.getId())){
            throw new BadRequestException(MessagesValidation.VALIDATION_ENTITY_EXIST_BY_NAME);
        }
        SectorDomain sectorDomain = request.to();
        sectorDomain.setIdentify(old.getIdentify());
        SectorDomain editedDomain = repo.edit(setFieldDefault.setDefaultFieldBaseUpdated(sectorDomain, old));
        return SectorResponse.from(editedDomain);
    }

    @Override
    @Transactional
    public void deleteById(String id) {
        SectorResponse old = findById(id);
        repo.deleteById(old.getId());
    }

    @Override
    @Transactional(readOnly = true)
    public List<SectorResponse> findAll() {
        return repo.findAll(false).stream().map(sectorDomain -> SectorResponse.from(sectorDomain)).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public SectorResponse findById(String id) {
        if (id == null) {
            return null;
        }
        SectorDomain old = repo.findById(id);
        if (old == null) {
            throw new NotFoundException("No sector found with id '%s'.".formatted(id));
        }
        return SectorResponse.from(old);
    }

    @Override
    @Transactional(readOnly = true)
    public SectorSearchResponse searchAll(SectorSearchRequest sectorSearchRequest) {
       PageDTO<SectorDomain> pageDTO = repo.searchAll(sectorSearchRequest);
       SectorSearchResponse sectorSearchRespose = new SectorSearchResponse();
       sectorSearchRespose.setSectors(pageDTO.getContent().stream().map(sectorDomain ->  SectorResponse.from(sectorDomain)).toList());
       sectorSearchRespose.setTotalElements(pageDTO.getTotalElements());
       sectorSearchRespose.setTotalPages(pageDTO.getTotalPages());
       return sectorSearchRespose;
    }

    @Override
    @Transactional(readOnly = true)
    public SectorSearchResponse searchFilterAll(SectorSearchFilterRequest sectorSearchFilterRequest) {
        PageDTO<SectorDomain> pageDTO = repo.searchAllFilter(sectorSearchFilterRequest);
        SectorSearchResponse sectorSearchRespose = new SectorSearchResponse();
        sectorSearchRespose.setSectors(pageDTO.getContent().stream().map(sectorDomain ->  SectorResponse.from(sectorDomain)).toList());
        sectorSearchRespose.setTotalElements(pageDTO.getTotalElements());
        sectorSearchRespose.setTotalPages(pageDTO.getTotalPages());
        return sectorSearchRespose;
    }

    @Override
    @Transactional(readOnly = true)
    public SectorResponse findByName(String name) {
        if (name == null) {
            return null;
        }
        SectorDomain old = repo.findByName(name);
        if (old == null) {
            throw new NotFoundException("No sector found with name '%s'.".formatted(name));
        }
        return SectorResponse.from(old);
    }

    @Override
    @Transactional(readOnly = true)
    public SectorResponse findByIdentify(String identify) {
        if (identify == null) {
            return null;
        }
        SectorDomain old = repo.findByIdentify(identify);
        if (old == null) {
            throw new NotFoundException("No bank found with identify '%s'.".formatted(identify));
        }
        return SectorResponse.from(old);
    }
}
