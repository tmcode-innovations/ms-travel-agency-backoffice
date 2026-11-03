package com.saas.tourismagency.admin.backoffice.usecase.crm.impl;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.crm.programtype.ProgramTypeConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.defaultfield.crm.SetFieldDefaultProgramType;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.crm.programtype.PersistProgramTypeNumberCallbackImpl;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.crm.programtype.ProgramTypeRepo;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.crm.programtype.*;
import com.saas.tourismagency.admin.backoffice.core.context.UserResolver;
import com.saas.tourismagency.admin.backoffice.core.exceptions.MessagesValidation;
import com.saas.tourismagency.admin.backoffice.domain.crm.ProgramTypeDomain;
import com.saas.tourismagency.admin.backoffice.usecase.crm.ProgramTypeUseCase;
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
public class ProgramTypeUseCaseImpl implements ProgramTypeUseCase {

    @Autowired
    private ProgramTypeRepo repo;

    @Autowired
    private ProgramTypeConverter converter;

    @Autowired
    private SetFieldDefaultProgramType setFieldDefault;

    @Autowired
    PersistProgramTypeNumberCallbackImpl persistProgramTypeNumberCallbackImpl;

    @Override
    @Transactional
    public ProgramTypeResponse create(ProgramTypeCreateRequest request) {
        ProgramTypeDomain programTypeDomainExist = repo.findByName(request.getName());
        if (programTypeDomainExist != null){
            throw new BadRequestException(MessagesValidation.VALIDATION_ENTITY_EXIST_BY_NAME);
        }
        ProgramTypeDomain newDomain = request.to();
        newDomain.setIdentify(persistProgramTypeNumberCallbackImpl.generateNumber());
        newDomain.setTenant(UserResolver.resolveUser().getTenant());
        ProgramTypeDomain createdDomain = repo.create(newDomain);
        return ProgramTypeResponse.from(createdDomain);
    }

    @Override
    @Transactional
    public ProgramTypeResponse edit(String id, ProgramTypeUpdateRequest request) {
        if (id == null) {
            return null;
        }
        ProgramTypeDomain old = repo.findById(id);
        if (old == null) {
            throw new NotFoundException("No program type found with id '%s'.".formatted(id));
        }
        ProgramTypeDomain programTypeDomainExist = repo.findByName(request.getName());
        if (programTypeDomainExist != null && !programTypeDomainExist.getId().equals(old.getId())){
            throw new BadRequestException(MessagesValidation.VALIDATION_ENTITY_EXIST_BY_NAME);
        }
        ProgramTypeDomain programTypeDomain = request.to();
        programTypeDomain.setIdentify(old.getIdentify());
        ProgramTypeDomain editedDomain = repo.edit(setFieldDefault.setDefaultFieldBaseUpdated(programTypeDomain, old));
        return ProgramTypeResponse.from(editedDomain);
    }

    @Override
    @Transactional
    public void deleteById(String id) {
        ProgramTypeResponse old = findById(id);
        repo.deleteById(old.getId());
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProgramTypeResponse> findAll() {
        return repo.findAll(false).stream().map(programDomain -> ProgramTypeResponse.from(programDomain)).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public ProgramTypeResponse findById(String id) {
        if (id == null) {
            return null;
        }
        ProgramTypeDomain old = repo.findById(id);
        if (old == null) {
            throw new NotFoundException("No program type found with id '%s'.".formatted(id));
        }
        return ProgramTypeResponse.from(old);
    }

    @Override
    @Transactional(readOnly = true)
    public ProgramTypeSearchResponse searchAll(ProgramTypeSearchRequest programSearchRequest) {
       PageDTO<ProgramTypeDomain> pageDTO = repo.searchAll(programSearchRequest);
       ProgramTypeSearchResponse programTypeSearchRespose = new ProgramTypeSearchResponse();
       programTypeSearchRespose.setProgramTypes(pageDTO.getContent().stream().map(programTypeDomain ->  ProgramTypeResponse.from(programTypeDomain)).toList());
       programTypeSearchRespose.setTotalElements(pageDTO.getTotalElements());
       programTypeSearchRespose.setTotalPages(pageDTO.getTotalPages());
       return programTypeSearchRespose;
    }

    @Override
    @Transactional(readOnly = true)
    public ProgramTypeSearchResponse searchFilterAll(ProgramTypeSearchFilterRequest programSearchFilterRequest) {
        PageDTO<ProgramTypeDomain> pageDTO = repo.searchAllFilter(programSearchFilterRequest);
        ProgramTypeSearchResponse programTypeSearchRespose = new ProgramTypeSearchResponse();
        programTypeSearchRespose.setProgramTypes(pageDTO.getContent().stream().map(programTypeDomain ->  ProgramTypeResponse.from(programTypeDomain)).toList());
        programTypeSearchRespose.setTotalElements(pageDTO.getTotalElements());
        programTypeSearchRespose.setTotalPages(pageDTO.getTotalPages());
        return programTypeSearchRespose;
    }

    @Override
    @Transactional(readOnly = true)
    public ProgramTypeResponse findByName(String name) {
        if (name == null) {
            return null;
        }
        ProgramTypeDomain old = repo.findByName(name);
        if (old == null) {
            throw new NotFoundException("No program type found with name '%s'.".formatted(name));
        }
        return ProgramTypeResponse.from(old);
    }

    @Override
    @Transactional(readOnly = true)
    public ProgramTypeResponse findByIdentify(String identify) {
        if (identify == null) {
            return null;
        }
        ProgramTypeDomain old = repo.findByIdentify(identify);
        if (old == null) {
            throw new NotFoundException("No program type found with identify '%s'.".formatted(identify));
        }
        return ProgramTypeResponse.from(old);
    }
}
