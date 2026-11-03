package com.saas.tourismagency.admin.backoffice.usecase.maintenance.impl;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.core.ReferenceGuard;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.maintenance.lodgingcategory.LodgingCategoryConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.defaultfield.maintenance.SetFieldDefaultLodgingCategory;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.maintenance.lodgingcategory.LodgingCategoryRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.maintenance.lodgingcategory.PersistLodgingCategoryNumberCallbackImpl;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.lodgingcategory.*;
import com.saas.tourismagency.admin.backoffice.core.context.UserResolver;
import com.saas.tourismagency.admin.backoffice.core.exceptions.MessagesValidation;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.LodgingCategoryDomain;
import com.saas.tourismagency.admin.backoffice.usecase.maintenance.LodgingCategoryUseCase;
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
public class LodgingCategoryUseCaseImpl implements LodgingCategoryUseCase {
    @Autowired
    private ReferenceGuard referenceGuard;


    @Autowired
    private LodgingCategoryRepo repo;

    @Autowired
    private LodgingCategoryConverter converter;

    @Autowired
    private SetFieldDefaultLodgingCategory setFieldDefault;

    @Autowired
    PersistLodgingCategoryNumberCallbackImpl persistLodgingCategoryNumberCallback;

    @Override
    @Transactional
    public LodgingCategoryResponse create(LodgingCategoryCreateRequest request) {
        LodgingCategoryDomain lodgingCategoryDomainExist = repo.findByName(request.getName());
        if (lodgingCategoryDomainExist != null){
            throw new BadRequestException(MessagesValidation.VALIDATION_ENTITY_EXIST_BY_NAME);
        }
        LodgingCategoryDomain newDomain = request.to();
        newDomain.setIdentify(persistLodgingCategoryNumberCallback.generateNumber());
        newDomain.setTenant(UserResolver.resolveUser().getTenant());
        LodgingCategoryDomain createdDomain = repo.create(newDomain);
        return LodgingCategoryResponse.from(createdDomain);
    }

    @Override
    @Transactional
    public LodgingCategoryResponse edit(String id, LodgingCategoryUpdateRequest request) {
        if (id == null) {
            return null;
        }
        LodgingCategoryDomain old = repo.findById(id);
        if (old == null) {
            throw new NotFoundException("No lodging category found with id '%s'.".formatted(id));
        }
        LodgingCategoryDomain lodgingCategoryDomainExist = repo.findByName(request.getName());
        if (lodgingCategoryDomainExist != null && !lodgingCategoryDomainExist.getId().equals(old.getId())){
            throw new BadRequestException(MessagesValidation.VALIDATION_ENTITY_EXIST_BY_NAME);
        }
        LodgingCategoryDomain lodgingCategoryDomain = request.to();
        lodgingCategoryDomain.setIdentify(old.getIdentify());
        LodgingCategoryDomain editedDomain = repo.edit(setFieldDefault.setDefaultFieldBaseUpdated(lodgingCategoryDomain, old));
        return LodgingCategoryResponse.from(editedDomain);
    }

    @Override
    @Transactional
    public void deleteById(String id) {
        LodgingCategoryResponse old = findById(id);
        if (referenceGuard.isReferenced("LodgingCategory", old.getId())) {
            throw new BadRequestException(MessagesValidation.VALIDATION_ENTITY_REFERENCED);
        }
        repo.deleteById(old.getId());
    }

    @Override
    @Transactional(readOnly = true)
    public List<LodgingCategoryResponse> findAll() {
        return repo.findAll(false).stream().map(lodgingCategoryDomain -> LodgingCategoryResponse.from(lodgingCategoryDomain)).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public LodgingCategoryResponse findById(String id) {
        if (id == null) {
            return null;
        }
        LodgingCategoryDomain old = repo.findById(id);
        if (old == null) {
            throw new NotFoundException("No lodging category found with id '%s'.".formatted(id));
        }
        return LodgingCategoryResponse.from(old);
    }

    @Override
    @Transactional(readOnly = true)
    public LodgingCategorySearchResponse searchAll(LodgingCategorySearchRequest lodgingCategorySearchRequest) {
       PageDTO<LodgingCategoryDomain> pageDTO = repo.searchAll(lodgingCategorySearchRequest);
       LodgingCategorySearchResponse lodgingCategorySearchRespose = new LodgingCategorySearchResponse();
       lodgingCategorySearchRespose.setLodgingCategories(pageDTO.getContent().stream().map(lodgingCategoryDomain ->  LodgingCategoryResponse.from(lodgingCategoryDomain)).toList());
       lodgingCategorySearchRespose.setTotalElements(pageDTO.getTotalElements());
       lodgingCategorySearchRespose.setTotalPages(pageDTO.getTotalPages());
       return lodgingCategorySearchRespose;
    }

    @Override
    @Transactional(readOnly = true)
    public LodgingCategorySearchResponse searchFilterAll(LodgingCategorySearchFilterRequest lodgingCategorySearchFilterRequest) {
        PageDTO<LodgingCategoryDomain> pageDTO = repo.searchAllFilter(lodgingCategorySearchFilterRequest);
        LodgingCategorySearchResponse lodgingCategorySearchRespose = new LodgingCategorySearchResponse();
        lodgingCategorySearchRespose.setLodgingCategories(pageDTO.getContent().stream().map(lodgingCategoryDomain ->  LodgingCategoryResponse.from(lodgingCategoryDomain)).toList());
        lodgingCategorySearchRespose.setTotalElements(pageDTO.getTotalElements());
        lodgingCategorySearchRespose.setTotalPages(pageDTO.getTotalPages());
        return lodgingCategorySearchRespose;
    }

    @Override
    @Transactional(readOnly = true)
    public LodgingCategoryResponse findByName(String name) {
        if (name == null) {
            return null;
        }
        LodgingCategoryDomain old = repo.findByName(name);
        if (old == null) {
            throw new NotFoundException("No lodging category found with name '%s'.".formatted(name));
        }
        return LodgingCategoryResponse.from(old);
    }

    @Override
    @Transactional(readOnly = true)
    public LodgingCategoryResponse findByIdentify(String identify) {
        if (identify == null) {
            return null;
        }
        LodgingCategoryDomain old = repo.findByIdentify(identify);
        if (old == null) {
            throw new NotFoundException("No lodging category found with identify '%s'.".formatted(identify));
        }
        return LodgingCategoryResponse.from(old);
    }
}
