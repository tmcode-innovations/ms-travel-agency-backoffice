package com.saas.tourismagency.admin.backoffice.usecase.maintenance.impl;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.core.ReferenceGuard;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.maintenance.detraction.DetractionConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.defaultfield.maintenance.SetFieldDefaultDetraction;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.maintenance.detraction.DetractionRepo;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.detraction.*;
import com.saas.tourismagency.admin.backoffice.core.context.UserResolver;
import com.saas.tourismagency.admin.backoffice.core.exceptions.MessagesValidation;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.DetractionDomain;
import com.saas.tourismagency.admin.backoffice.usecase.maintenance.DetractionUseCase;
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
public class DetractionUseCaseImpl implements DetractionUseCase {
    @Autowired
    private ReferenceGuard referenceGuard;


    @Autowired
    private DetractionRepo repo;

    @Autowired
    private DetractionConverter converter;

    @Autowired
    private SetFieldDefaultDetraction setFieldDefault;

    @Override
    @Transactional
    public DetractionResponse create(DetractionCreateRequest request) {
        DetractionDomain detractionDomainExist = repo.findByName(request.getName());
        if (detractionDomainExist != null){
            throw new BadRequestException(MessagesValidation.VALIDATION_ENTITY_EXIST_BY_NAME);
        }
        DetractionDomain newDomain = request.to(false);
        newDomain.setTenant(UserResolver.resolveUser().getTenant());
        DetractionDomain createdDomain = repo.create(newDomain);
        return DetractionResponse.from(createdDomain);
    }

    @Override
    @Transactional
    public DetractionResponse edit(String id, DetractionUpdateRequest request) {
        if (id == null) {
            return null;
        }
        DetractionDomain old = repo.findById(id);
        if (old == null) {
            throw new NotFoundException("No detraction found with id '%s'.".formatted(id));
        }
        DetractionDomain detractionDomainExist = repo.findByName(request.getName());
        if (detractionDomainExist != null && !detractionDomainExist.getId().equals(old.getId())){
            throw new BadRequestException(MessagesValidation.VALIDATION_ENTITY_EXIST_BY_NAME);
        }
        DetractionDomain detractionDomain = request.to();
        detractionDomain.setAvailable(old.getAvailable());
        DetractionDomain editedDomain = repo.edit(setFieldDefault.setDefaultFieldBaseUpdated(detractionDomain, old));
        return DetractionResponse.from(editedDomain);
    }

    @Override
    @Transactional
    public void deleteById(String id) {
        DetractionResponse old = findById(id);
        if (referenceGuard.isReferenced("Detraction", old.getId())) {
            throw new BadRequestException(MessagesValidation.VALIDATION_ENTITY_REFERENCED);
        }
        repo.deleteById(old.getId());
    }

    @Override
    @Transactional(readOnly = true)
    public List<DetractionResponse> findAll() {
        return repo.findAll(false).stream().map(detractionDomain -> DetractionResponse.from(detractionDomain)).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public DetractionResponse findById(String id) {
        if (id == null) {
            return null;
        }
        DetractionDomain old = repo.findById(id);
        if (old == null) {
            throw new NotFoundException("No detraction found with id '%s'.".formatted(id));
        }
        return DetractionResponse.from(old);
    }

    @Override
    @Transactional(readOnly = true)
    public DetractionSearchResponse searchAll(DetractionSearchRequest detractionSearchRequest) {
       PageDTO<DetractionDomain> pageDTO = repo.searchAll(detractionSearchRequest);
       DetractionSearchResponse detractionSearchRespose = new DetractionSearchResponse();
       detractionSearchRespose.setDetractions(pageDTO.getContent().stream().map(detractionDomain ->  DetractionResponse.from(detractionDomain)).toList());
       detractionSearchRespose.setTotalElements(pageDTO.getTotalElements());
       detractionSearchRespose.setTotalPages(pageDTO.getTotalPages());
       return detractionSearchRespose;
    }

    @Override
    @Transactional(readOnly = true)
    public DetractionSearchResponse searchFilterAll(DetractionSearchFilterRequest detractionSearchFilterRequest) {
        PageDTO<DetractionDomain> pageDTO = repo.searchAllFilter(detractionSearchFilterRequest);
        DetractionSearchResponse detractionSearchRespose = new DetractionSearchResponse();
        detractionSearchRespose.setDetractions(pageDTO.getContent().stream().map(detractionDomain ->  DetractionResponse.from(detractionDomain)).toList());
        detractionSearchRespose.setTotalElements(pageDTO.getTotalElements());
        detractionSearchRespose.setTotalPages(pageDTO.getTotalPages());
        return detractionSearchRespose;
    }

    @Override
    @Transactional
    public DetractionResponse changeAvailable(String id, Boolean available) {
        if (id == null) {
            return null;
        }
        DetractionDomain old = repo.findById(id);
        if (old == null) {
            throw new NotFoundException("No detraction found with id '%s'.".formatted(id));
        }
        if (available && old.getAvailable()){
            throw new BadRequestException("Already Detraction is available");
        }
        if (!available && !old.getAvailable()){
            throw new BadRequestException("Already Detraction is not available");
        }
        old.setAvailable(available);
        DetractionDomain editedDomain = repo.edit(old);
        return DetractionResponse.from(editedDomain);
    }

    @Override
    @Transactional(readOnly = true)
    public DetractionResponse findByName(String name) {
        if (name == null) {
            return null;
        }
        DetractionDomain old = repo.findByName(name);
        if (old == null) {
            throw new NotFoundException("No detraction found with name '%s'.".formatted(name));
        }
        return DetractionResponse.from(old);
    }
}
