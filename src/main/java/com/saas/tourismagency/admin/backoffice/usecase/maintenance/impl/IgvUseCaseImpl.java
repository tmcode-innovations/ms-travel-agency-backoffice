package com.saas.tourismagency.admin.backoffice.usecase.maintenance.impl;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.core.ReferenceGuard;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.maintenance.igv.IgvConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.defaultfield.maintenance.SetFieldDefaultIgv;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.maintenance.igv.IgvRepo;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.igv.*;
import com.saas.tourismagency.admin.backoffice.core.context.UserResolver;
import com.saas.tourismagency.admin.backoffice.core.exceptions.MessagesValidation;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.IgvDomain;
import com.saas.tourismagency.admin.backoffice.usecase.maintenance.IgvUseCase;
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
public class IgvUseCaseImpl implements IgvUseCase {
    @Autowired
    private ReferenceGuard referenceGuard;


    @Autowired
    private IgvRepo repo;

    @Autowired
    private IgvConverter converter;

    @Autowired
    private SetFieldDefaultIgv setFieldDefault;

    @Override
    @Transactional
    public IgvResponse create(IgvCreateRequest request) {
        IgvDomain igvDomainExist = repo.findByName(request.getName());
        if (igvDomainExist != null){
            throw new BadRequestException(MessagesValidation.VALIDATION_ENTITY_EXIST_BY_NAME);
        }
        IgvDomain newDomain = request.to(false);
        newDomain.setTenant(UserResolver.resolveUser().getTenant());
        IgvDomain createdDomain = repo.create(newDomain);
        return IgvResponse.from(createdDomain);
    }

    @Override
    @Transactional
    public IgvResponse edit(String id, IgvUpdateRequest request) {
        if (id == null) {
            return null;
        }
        IgvDomain old = repo.findById(id);
        if (old == null) {
            throw new NotFoundException("No igv found with id '%s'.".formatted(id));
        }
        IgvDomain igvDomainExist = repo.findByName(request.getName());
        if (igvDomainExist != null && !igvDomainExist.getId().equals(old.getId())){
            throw new BadRequestException(MessagesValidation.VALIDATION_ENTITY_EXIST_BY_NAME);
        }
        IgvDomain igvDomain = request.to();
        igvDomain.setAvailable(old.getAvailable());
        IgvDomain editedDomain = repo.edit(setFieldDefault.setDefaultFieldBaseUpdated(igvDomain, old));
        return IgvResponse.from(editedDomain);
    }

    @Override
    @Transactional
    public void deleteById(String id) {
        IgvResponse old = findById(id);
        if (referenceGuard.isReferenced("Igv", old.getId())) {
            throw new BadRequestException(MessagesValidation.VALIDATION_ENTITY_REFERENCED);
        }
        repo.deleteById(old.getId());
    }

    @Override
    @Transactional(readOnly = true)
    public List<IgvResponse> findAll() {
        return repo.findAll(false).stream().map(igvDomain -> IgvResponse.from(igvDomain)).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public IgvResponse findById(String id) {
        if (id == null) {
            return null;
        }
        IgvDomain old = repo.findById(id);
        if (old == null) {
            throw new NotFoundException("No igv found with id '%s'.".formatted(id));
        }
        return IgvResponse.from(old);
    }

    @Override
    @Transactional(readOnly = true)
    public IgvSearchResponse searchAll(IgvSearchRequest igvSearchRequest) {
       PageDTO<IgvDomain> pageDTO = repo.searchAll(igvSearchRequest);
       IgvSearchResponse igvSearchRespose = new IgvSearchResponse();
       igvSearchRespose.setIgvs(pageDTO.getContent().stream().map(igvDomain ->  IgvResponse.from(igvDomain)).toList());
       igvSearchRespose.setTotalElements(pageDTO.getTotalElements());
       igvSearchRespose.setTotalPages(pageDTO.getTotalPages());
       return igvSearchRespose;
    }

    @Override
    @Transactional(readOnly = true)
    public IgvSearchResponse searchFilterAll(IgvSearchFilterRequest igvSearchFilterRequest) {
        PageDTO<IgvDomain> pageDTO = repo.searchAllFilter(igvSearchFilterRequest);
        IgvSearchResponse igvSearchRespose = new IgvSearchResponse();
        igvSearchRespose.setIgvs(pageDTO.getContent().stream().map(igvDomain ->  IgvResponse.from(igvDomain)).toList());
        igvSearchRespose.setTotalElements(pageDTO.getTotalElements());
        igvSearchRespose.setTotalPages(pageDTO.getTotalPages());
        return igvSearchRespose;
    }

    @Override
    @Transactional
    public IgvResponse changeAvailable(String id, Boolean available) {
        if (id == null) {
            return null;
        }
        IgvDomain old = repo.findById(id);
        if (old == null) {
            throw new NotFoundException("No igv found with id '%s'.".formatted(id));
        }
        if (available && old.getAvailable()){
            throw new BadRequestException("Igv is for default");
        }
        if (!available && !old.getAvailable()){
            throw new BadRequestException("Igv is not for default");
        }
        old.setAvailable(available);
        IgvDomain editedDomain = repo.edit(old);
        return IgvResponse.from(editedDomain);
    }

    @Override
    @Transactional(readOnly = true)
    public IgvResponse findByName(String name) {
        if (name == null) {
            return null;
        }
        IgvDomain old = repo.findByName(name);
        if (old == null) {
            throw new NotFoundException("No igv found with name '%s'.".formatted(name));
        }
        return IgvResponse.from(old);
    }

    @Override
    @Transactional(readOnly = true)
    public IgvResponse findAvailable() {
        IgvDomain old = repo.findAvailable();
        if (old == null) {
            throw new NotFoundException("No igv available found.");
        }
        return IgvResponse.from(old);
    }
}
