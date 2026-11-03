package com.saas.tourismagency.admin.backoffice.usecase.maintenance.impl;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.core.ReferenceGuard;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.maintenance.service.ServiceConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.defaultfield.maintenance.SetFieldDefaultService;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.maintenance.service.PersistServiceNumberCallbackImpl;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.maintenance.service.ServiceRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.maintenance.servicecategory.ServiceCategoryRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.maintenance.touristdestine.TouristDestineRepo;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.base.SimpleBaseResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.service.*;
import com.saas.tourismagency.admin.backoffice.core.context.UserResolver;
import com.saas.tourismagency.admin.backoffice.core.exceptions.MessagesValidation;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.ServiceCategoryDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.ServiceDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.TouristDestineDomain;
import com.saas.tourismagency.admin.backoffice.usecase.maintenance.ServiceUseCase;
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
public class ServiceUseCaseImpl implements ServiceUseCase {
    @Autowired
    private ReferenceGuard referenceGuard;


    @Autowired
    private ServiceRepo repo;

    @Autowired
    private TouristDestineRepo touristDestineRepo;

    @Autowired
    private ServiceCategoryRepo serviceCategoryRepo;

    @Autowired
    private ServiceConverter converter;

    @Autowired
    private SetFieldDefaultService setFieldDefault;

    @Autowired
    PersistServiceNumberCallbackImpl persistServiceNumberCallbackImpl;

    @Override
    @Transactional
    public ServiceResponse create(ServiceCreateRequest request) {
        ServiceDomain serviceDomainExist = repo.findByName(request.getName());
        if (serviceDomainExist != null){
            throw new BadRequestException(MessagesValidation.VALIDATION_ENTITY_EXIST_BY_NAME);
        }
        TouristDestineDomain touristDestineDomain = null;
        if (request.getTouristDestine() != null && !request.getTouristDestine().isEmpty()){
            touristDestineDomain = touristDestineRepo.findById(request.getTouristDestine());
        }
        ServiceCategoryDomain serviceCategoryDomain = null;
        if (request.getServiceCategory() != null && !request.getServiceCategory().isEmpty()){
            serviceCategoryDomain = serviceCategoryRepo.findById(request.getServiceCategory());
        }
        ServiceDomain newDomain = request.to(touristDestineDomain, serviceCategoryDomain);
        newDomain.setIdentify(persistServiceNumberCallbackImpl.generateNumber());
        newDomain.setTenant(UserResolver.resolveUser().getTenant());
        ServiceDomain createdDomain = repo.create(newDomain);
        return ServiceResponse.from(createdDomain);
    }

    @Override
    @Transactional
    public ServiceResponse edit(String id, ServiceUpdateRequest request) {
        if (id == null) {
            return null;
        }
        ServiceDomain old = repo.findById(id);
        if (old == null) {
            throw new NotFoundException("No service found with id '%s'.".formatted(id));
        }
        ServiceDomain serviceDomainExist = repo.findByName(request.getName());
        if (serviceDomainExist != null && !serviceDomainExist.getId().equals(old.getId())){
            throw new BadRequestException(MessagesValidation.VALIDATION_ENTITY_EXIST_BY_NAME);
        }
        TouristDestineDomain touristDestineDomain = null;
        if (request.getTouristDestine() != null && !request.getTouristDestine().isEmpty()){
            touristDestineDomain = touristDestineRepo.findById(request.getTouristDestine());
        }
        ServiceCategoryDomain serviceCategoryDomain = null;
        if (request.getServiceCategory() != null && !request.getServiceCategory().isEmpty()){
            serviceCategoryDomain = serviceCategoryRepo.findById(request.getServiceCategory());
        }
        ServiceDomain serviceDomain = request.to(touristDestineDomain, serviceCategoryDomain);
        serviceDomain.setIdentify(old.getIdentify());
        ServiceDomain editedDomain = repo.edit(setFieldDefault.setDefaultFieldBaseUpdated(serviceDomain, old));
        return ServiceResponse.from(editedDomain);
    }

    @Override
    @Transactional
    public void deleteById(String id) {
        ServiceResponse old = findById(id);
        if (referenceGuard.isReferenced("Service", old.getId())) {
            throw new BadRequestException(MessagesValidation.VALIDATION_ENTITY_REFERENCED);
        }
        repo.deleteById(old.getId());
    }

    @Override
    @Transactional(readOnly = true)
    public List<ServiceResponse> findAll() {
        return repo.findAll(false).stream().map(serviceDomain -> ServiceResponse.from(serviceDomain)).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public ServiceResponse findById(String id) {
        if (id == null) {
            return null;
        }
        ServiceDomain old = repo.findById(id);
        if (old == null) {
            throw new NotFoundException("No service found with id '%s'.".formatted(id));
        }
        return ServiceResponse.from(old);
    }

    @Override
    @Transactional(readOnly = true)
    public ServiceSearchResponse searchAll(ServiceSearchRequest serviceSearchRequest) {
       PageDTO<ServiceDomain> pageDTO = repo.searchAll(serviceSearchRequest);
       ServiceSearchResponse serviceSearchRespose = new ServiceSearchResponse();
       serviceSearchRespose.setServices(pageDTO.getContent().stream().map(serviceDomain ->  ServiceResponse.from(serviceDomain)).toList());
       serviceSearchRespose.setTotalElements(pageDTO.getTotalElements());
       serviceSearchRespose.setTotalPages(pageDTO.getTotalPages());
       return serviceSearchRespose;
    }

    @Override
    @Transactional(readOnly = true)
    public ServiceSearchResponse searchFilterAll(ServiceSearchFilterRequest serviceSearchFilterRequest) {
        PageDTO<ServiceDomain> pageDTO = repo.searchAllFilter(serviceSearchFilterRequest);
        ServiceSearchResponse serviceSearchRespose = new ServiceSearchResponse();
        serviceSearchRespose.setServices(pageDTO.getContent().stream().map(serviceDomain ->  ServiceResponse.from(serviceDomain)).toList());
        serviceSearchRespose.setTotalElements(pageDTO.getTotalElements());
        serviceSearchRespose.setTotalPages(pageDTO.getTotalPages());
        return serviceSearchRespose;
    }

    @Override
    @Transactional(readOnly = true)
    public ServiceResponse findByName(String name) {
        if (name == null) {
            return null;
        }
        ServiceDomain old = repo.findByName(name);
        if (old == null) {
            throw new NotFoundException("No service found with name '%s'.".formatted(name));
        }
        return ServiceResponse.from(old);
    }

    @Override
    @Transactional(readOnly = true)
    public ServiceResponse findByIdentify(String identify) {
        if (identify == null) {
            return null;
        }
        ServiceDomain old = repo.findByIdentify(identify);
        if (old == null) {
            throw new NotFoundException("No service found with identify '%s'.".formatted(identify));
        }
        return ServiceResponse.from(old);
    }

    @Override
    @Transactional(readOnly = true)
    public List<SimpleBaseResponse> findAllSimple() {
        return repo.findAllSimple().stream().map(simpleBaseDomain -> SimpleBaseResponse.from(simpleBaseDomain)).toList();
    }
}
