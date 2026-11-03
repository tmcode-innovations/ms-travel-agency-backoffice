package com.saas.tourismagency.admin.backoffice.usecase.account.impl;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.account.servicepqttourist.ServicePqtTouristConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.defaultfield.account.SetFieldDefaultServicePqtTourist;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.account.grouppqttourist.GroupPqtTouristRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.account.servicepqttourist.PersistServicePqtTouristNumberCallbackImpl;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.account.servicepqttourist.ServicePqtTouristRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.maintenance.bank.PersistBankNumberCallbackImpl;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.maintenance.documentaccounttype.DocumentAccountTypeRepo;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.servicepqttourist.*;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.bank.BankResponse;
import com.saas.tourismagency.admin.backoffice.core.context.UserResolver;
import com.saas.tourismagency.admin.backoffice.core.exceptions.MessagesValidation;
import com.saas.tourismagency.admin.backoffice.domain.account.GroupPqtTouristDomain;
import com.saas.tourismagency.admin.backoffice.domain.account.ServicePqtTouristDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.BankDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.DocumentAccountTypeDomain;
import com.saas.tourismagency.admin.backoffice.usecase.account.ServicePqtTouristUseCase;
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
public class ServicePqtTouristUseCaseImpl implements ServicePqtTouristUseCase {

    @Autowired
    private ServicePqtTouristRepo repo;

    @Autowired
    private GroupPqtTouristRepo groupPqtTouristRepo;

    @Autowired
    private DocumentAccountTypeRepo documentAccountTypeRepo;

    @Autowired
    private ServicePqtTouristConverter converter;

    @Autowired
    private SetFieldDefaultServicePqtTourist setFieldDefault;

    @Autowired
    PersistServicePqtTouristNumberCallbackImpl persistServicePqtTouristNumberCallbackImpl;

    @Override
    @Transactional
    public ServicePqtTouristResponse create(ServicePqtTouristCreateRequest request) {
        ServicePqtTouristDomain servicePqtTouristDomainExist = repo.findByName(request.getName());
        if (servicePqtTouristDomainExist != null){
            throw new BadRequestException(MessagesValidation.VALIDATION_ENTITY_EXIST_BY_NAME);
        }
        GroupPqtTouristDomain groupPqtTouristDomain = null;
        if (request.getGroupPqtTourist() != null && !request.getGroupPqtTourist().isEmpty()){
            groupPqtTouristDomain = groupPqtTouristRepo.findById(request.getGroupPqtTourist());
        }
        DocumentAccountTypeDomain documentAccountTypeDomain = null;
        if (request.getDocumentAccountType() != null && !request.getDocumentAccountType().isEmpty()){
            documentAccountTypeDomain = documentAccountTypeRepo.findById(request.getDocumentAccountType());
        }
        ServicePqtTouristDomain newDomain = request.to(documentAccountTypeDomain, groupPqtTouristDomain);
        newDomain.setTenant(UserResolver.resolveUser().getTenant());
        newDomain.setIdentify(persistServicePqtTouristNumberCallbackImpl.generateNumber());
        ServicePqtTouristDomain createdDomain = repo.create(newDomain);
        return ServicePqtTouristResponse.from(createdDomain);
    }

    @Override
    @Transactional
    public ServicePqtTouristResponse edit(String id, ServicePqtTouristUpdateRequest request) {
        if (id == null) {
            return null;
        }
        ServicePqtTouristDomain old = repo.findById(id);
        if (old == null) {
            throw new NotFoundException("No entity found with id '%s'.".formatted(id));
        }
        ServicePqtTouristDomain servicePqtTouristDomainExist = repo.findByName(request.getName());
        if (servicePqtTouristDomainExist != null && !servicePqtTouristDomainExist.getId().equals(old.getId())){
            throw new BadRequestException(MessagesValidation.VALIDATION_ENTITY_EXIST_BY_NAME);
        }
        GroupPqtTouristDomain groupPqtTouristDomain = null;
        if (request.getGroupPqtTourist() != null && !request.getGroupPqtTourist().isEmpty()){
            groupPqtTouristDomain = groupPqtTouristRepo.findById(request.getGroupPqtTourist());
        }
        DocumentAccountTypeDomain documentAccountTypeDomain = null;
        if (request.getDocumentAccountType() != null && !request.getDocumentAccountType().isEmpty()){
            documentAccountTypeDomain = documentAccountTypeRepo.findById(request.getDocumentAccountType());
        }
        ServicePqtTouristDomain servicePqtTouristDomain = request.to(documentAccountTypeDomain, groupPqtTouristDomain);
        servicePqtTouristDomain.setIdentify(old.getIdentify());
        ServicePqtTouristDomain editedDomain = repo.edit(setFieldDefault.setDefaultFieldBaseUpdated(servicePqtTouristDomain, old));
        return ServicePqtTouristResponse.from(editedDomain);
    }

    @Override
    @Transactional
    public void deleteById(String id) {
        ServicePqtTouristResponse old = findById(id);
        repo.deleteById(old.getId());
    }

    @Override
    @Transactional(readOnly = true)
    public List<ServicePqtTouristResponse> findAll() {
        return repo.findAll(false).stream().map(servicePqtTouristDomain -> ServicePqtTouristResponse.from(servicePqtTouristDomain)).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public ServicePqtTouristResponse findById(String id) {
        if (id == null) {
            return null;
        }
        ServicePqtTouristDomain old = repo.findById(id);
        if (old == null) {
            throw new NotFoundException("No entity found with id '%s'.".formatted(id));
        }
        return ServicePqtTouristResponse.from(old);
    }

    @Override
    @Transactional(readOnly = true)
    public ServicePqtTouristSearchResponse searchAll(ServicePqtTouristSearchRequest bankSearchRequest) {
       PageDTO<ServicePqtTouristDomain> pageDTO = repo.searchAll(bankSearchRequest);
       ServicePqtTouristSearchResponse bankSearchRespose = new ServicePqtTouristSearchResponse();
       bankSearchRespose.setItems(pageDTO.getContent().stream().map(bankDomain ->  ServicePqtTouristResponse.from(bankDomain)).toList());
       bankSearchRespose.setTotalElements(pageDTO.getTotalElements());
       bankSearchRespose.setTotalPages(pageDTO.getTotalPages());
       return bankSearchRespose;
    }

    @Override
    @Transactional(readOnly = true)
    public ServicePqtTouristSearchResponse searchFilterAll(ServicePqtTouristSearchFilterRequest bankSearchFilterRequest) {
        PageDTO<ServicePqtTouristDomain> pageDTO = repo.searchAllFilter(bankSearchFilterRequest);
        ServicePqtTouristSearchResponse bankSearchRespose = new ServicePqtTouristSearchResponse();
        bankSearchRespose.setItems(pageDTO.getContent().stream().map(bankDomain ->  ServicePqtTouristResponse.from(bankDomain)).toList());
        bankSearchRespose.setTotalElements(pageDTO.getTotalElements());
        bankSearchRespose.setTotalPages(pageDTO.getTotalPages());
        return bankSearchRespose;
    }

    @Override
    @Transactional(readOnly = true)
    public ServicePqtTouristResponse findByName(String name) {
        if (name == null) {
            return null;
        }
        ServicePqtTouristDomain old = repo.findByName(name);
        if (old == null) {
            throw new NotFoundException("No service package tourist found with name '%s'.".formatted(name));
        }
        return ServicePqtTouristResponse.from(old);
    }

    @Override
    @Transactional(readOnly = true)
    public ServicePqtTouristResponse findByIdentify(String identify) {
        if (identify == null) {
            return null;
        }
        ServicePqtTouristDomain old = repo.findByIdentify(identify);
        if (old == null) {
            throw new NotFoundException("No service package tourist found with identify '%s'.".formatted(identify));
        }
        return ServicePqtTouristResponse.from(old);
    }
}
