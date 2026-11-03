package com.saas.tourismagency.admin.backoffice.usecase.maintenance.impl;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.core.ReferenceGuard;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.maintenance.servicecategory.ServiceCategoryConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.defaultfield.maintenance.SetFieldDefaultServiceCategory;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.maintenance.servicecategory.PersistServiceCategoryNumberCallbackImpl;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.maintenance.servicecategory.ServiceCategoryRepo;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.servicecategory.*;
import com.saas.tourismagency.admin.backoffice.core.context.UserResolver;
import com.saas.tourismagency.admin.backoffice.core.exceptions.MessagesValidation;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.ServiceCategoryDomain;
import com.saas.tourismagency.admin.backoffice.usecase.maintenance.ServiceCategoryUseCase;
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
public class ServiceCategoryUseCaseImpl implements ServiceCategoryUseCase {
    @Autowired
    private ReferenceGuard referenceGuard;


    @Autowired
    private ServiceCategoryRepo repo;

    @Autowired
    private ServiceCategoryConverter converter;

    @Autowired
    private SetFieldDefaultServiceCategory setFieldDefault;

    @Autowired
    PersistServiceCategoryNumberCallbackImpl persistServiceCategoryNumberCallbackImpl;

    @Override
    @Transactional
    public ServiceCategoryResponse create(ServiceCategoryCreateRequest request) {
        ServiceCategoryDomain serviceCategoryDomainExist = repo.findByName(request.getName());
        if (serviceCategoryDomainExist != null){
            throw new BadRequestException(MessagesValidation.VALIDATION_ENTITY_EXIST_BY_NAME);
        }
        ServiceCategoryDomain newDomain = request.to();
        newDomain.setIdentify(persistServiceCategoryNumberCallbackImpl.generateNumber());
        newDomain.setTenant(UserResolver.resolveUser().getTenant());
        ServiceCategoryDomain createdDomain = repo.create(newDomain);
        return ServiceCategoryResponse.from(createdDomain);
    }

    @Override
    @Transactional
    public ServiceCategoryResponse edit(String id, ServiceCategoryUpdateRequest request) {
        if (id == null) {
            return null;
        }
        ServiceCategoryDomain old = repo.findById(id);
        if (old == null) {
            throw new NotFoundException("No serviceCategory found with id '%s'.".formatted(id));
        }
        ServiceCategoryDomain serviceCategoryDomainExist = repo.findByName(request.getName());
        if (serviceCategoryDomainExist != null && !serviceCategoryDomainExist.getId().equals(old.getId())){
            throw new BadRequestException(MessagesValidation.VALIDATION_ENTITY_EXIST_BY_NAME);
        }
        ServiceCategoryDomain serviceCategoryDomain = request.to();
        serviceCategoryDomain.setIdentify(old.getIdentify());
        ServiceCategoryDomain editedDomain = repo.edit(setFieldDefault.setDefaultFieldBaseUpdated(serviceCategoryDomain, old));
        return ServiceCategoryResponse.from(editedDomain);
    }

    @Override
    @Transactional
    public void deleteById(String id) {
        ServiceCategoryResponse old = findById(id);
        if (referenceGuard.isReferenced("ServiceCategory", old.getId())) {
            throw new BadRequestException(MessagesValidation.VALIDATION_ENTITY_REFERENCED);
        }
        repo.deleteById(old.getId());
    }

    @Override
    @Transactional(readOnly = true)
    public List<ServiceCategoryResponse> findAll() {
        return repo.findAll(false).stream().map(serviceCategoryDomain -> ServiceCategoryResponse.from(serviceCategoryDomain)).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public ServiceCategoryResponse findById(String id) {
        if (id == null) {
            return null;
        }
        ServiceCategoryDomain old = repo.findById(id);
        if (old == null) {
            throw new NotFoundException("No serviceCategory found with id '%s'.".formatted(id));
        }
        return ServiceCategoryResponse.from(old);
    }

    @Override
    @Transactional(readOnly = true)
    public ServiceCategorySearchResponse searchAll(ServiceCategorySearchRequest serviceCategorySearchRequest) {
       PageDTO<ServiceCategoryDomain> pageDTO = repo.searchAll(serviceCategorySearchRequest);
       ServiceCategorySearchResponse serviceCategorySearchRespose = new ServiceCategorySearchResponse();
       serviceCategorySearchRespose.setServiceCategories(pageDTO.getContent().stream().map(serviceCategoryDomain ->  ServiceCategoryResponse.from(serviceCategoryDomain)).toList());
       serviceCategorySearchRespose.setTotalElements(pageDTO.getTotalElements());
       serviceCategorySearchRespose.setTotalPages(pageDTO.getTotalPages());
       return serviceCategorySearchRespose;
    }

    @Override
    @Transactional(readOnly = true)
    public ServiceCategorySearchResponse searchFilterAll(ServiceCategorySearchFilterRequest serviceCategorySearchFilterRequest) {
        PageDTO<ServiceCategoryDomain> pageDTO = repo.searchAllFilter(serviceCategorySearchFilterRequest);
        ServiceCategorySearchResponse serviceCategorySearchRespose = new ServiceCategorySearchResponse();
        serviceCategorySearchRespose.setServiceCategories(pageDTO.getContent().stream().map(serviceCategoryDomain ->  ServiceCategoryResponse.from(serviceCategoryDomain)).toList());
        serviceCategorySearchRespose.setTotalElements(pageDTO.getTotalElements());
        serviceCategorySearchRespose.setTotalPages(pageDTO.getTotalPages());
        return serviceCategorySearchRespose;
    }

    @Override
    @Transactional(readOnly = true)
    public ServiceCategoryResponse findByName(String name) {
        if (name == null) {
            return null;
        }
        ServiceCategoryDomain old = repo.findByName(name);
        if (old == null) {
            throw new NotFoundException("No bank found with name '%s'.".formatted(name));
        }
        return ServiceCategoryResponse.from(old);
    }

    @Override
    @Transactional(readOnly = true)
    public ServiceCategoryResponse findByIdentify(String identify) {
        if (identify == null) {
            return null;
        }
        ServiceCategoryDomain old = repo.findByIdentify(identify);
        if (old == null) {
            throw new NotFoundException("No bank found with identify '%s'.".formatted(identify));
        }
        return ServiceCategoryResponse.from(old);
    }
}
