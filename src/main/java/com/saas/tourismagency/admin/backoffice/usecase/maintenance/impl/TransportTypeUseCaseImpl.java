package com.saas.tourismagency.admin.backoffice.usecase.maintenance.impl;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.maintenance.transporttype.TransportTypeConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.defaultfield.maintenance.SetFieldDefaultTransportType;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.maintenance.transporttype.PersistTransportTypeNumberCallbackImpl;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.maintenance.transporttype.TransportTypeRepo;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.transporttype.*;
import com.saas.tourismagency.admin.backoffice.core.context.UserResolver;
import com.saas.tourismagency.admin.backoffice.core.exceptions.MessagesValidation;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.TransportTypeDomain;
import com.saas.tourismagency.admin.backoffice.usecase.maintenance.TransportTypeUseCase;
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
public class TransportTypeUseCaseImpl implements TransportTypeUseCase {

    @Autowired
    private TransportTypeRepo repo;

    @Autowired
    private TransportTypeConverter converter;

    @Autowired
    private SetFieldDefaultTransportType setFieldDefault;

    @Autowired
    PersistTransportTypeNumberCallbackImpl persistTransportTypeNumberCallbackImpl;

    @Override
    @Transactional
    public TransportTypeResponse create(TransportTypeCreateRequest request) {
        TransportTypeDomain transportTypeDomainExist = repo.findByName(request.getName());
        if (transportTypeDomainExist != null){
            throw new BadRequestException(MessagesValidation.VALIDATION_ENTITY_EXIST_BY_NAME);
        }
        TransportTypeDomain newDomain = request.to();
        newDomain.setIdentify(persistTransportTypeNumberCallbackImpl.generateNumber());
        newDomain.setTenant(UserResolver.resolveUser().getTenant());
        TransportTypeDomain createdDomain = repo.create(newDomain);
        return TransportTypeResponse.from(createdDomain);
    }

    @Override
    @Transactional
    public TransportTypeResponse edit(String id, TransportTypeUpdateRequest request) {
        if (id == null) {
            return null;
        }
        TransportTypeDomain old = repo.findById(id);
        if (old == null) {
            throw new NotFoundException("No transport type found with id '%s'.".formatted(id));
        }
        TransportTypeDomain transportTypeDomainExist = repo.findByName(request.getName());
        if (transportTypeDomainExist != null && !transportTypeDomainExist.getId().equals(old.getId())){
            throw new BadRequestException(MessagesValidation.VALIDATION_ENTITY_EXIST_BY_NAME);
        }
        TransportTypeDomain transportTypeDomain = request.to();
        transportTypeDomain.setIdentify(old.getIdentify());
        TransportTypeDomain editedDomain = repo.edit(setFieldDefault.setDefaultFieldBaseUpdated(transportTypeDomain, old));
        return TransportTypeResponse.from(editedDomain);
    }

    @Override
    @Transactional
    public void deleteById(String id) {
        TransportTypeResponse old = findById(id);
        repo.deleteById(old.getId());
    }

    @Override
    @Transactional(readOnly = true)
    public List<TransportTypeResponse> findAll() {
        return repo.findAll(false).stream().map(transportTypeDomain -> TransportTypeResponse.from(transportTypeDomain)).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public TransportTypeResponse findById(String id) {
        if (id == null) {
            return null;
        }
        TransportTypeDomain old = repo.findById(id);
        if (old == null) {
            throw new NotFoundException("No transport type found with id '%s'.".formatted(id));
        }
        return TransportTypeResponse.from(old);
    }

    @Override
    @Transactional(readOnly = true)
    public TransportTypeSearchResponse searchAll(TransportTypeSearchRequest transportTypeSearchRequest) {
       PageDTO<TransportTypeDomain> pageDTO = repo.searchAll(transportTypeSearchRequest);
       TransportTypeSearchResponse transportTypeSearchRespose = new TransportTypeSearchResponse();
       transportTypeSearchRespose.setTransportTypes(pageDTO.getContent().stream().map(transportTypeDomain ->  TransportTypeResponse.from(transportTypeDomain)).toList());
       transportTypeSearchRespose.setTotalElements(pageDTO.getTotalElements());
       transportTypeSearchRespose.setTotalPages(pageDTO.getTotalPages());
       return transportTypeSearchRespose;
    }

    @Override
    @Transactional(readOnly = true)
    public TransportTypeSearchResponse searchFilterAll(TransportTypeSearchFilterRequest transportTypeSearchFilterRequest) {
        PageDTO<TransportTypeDomain> pageDTO = repo.searchAllFilter(transportTypeSearchFilterRequest);
        TransportTypeSearchResponse transportTypeSearchRespose = new TransportTypeSearchResponse();
        transportTypeSearchRespose.setTransportTypes(pageDTO.getContent().stream().map(transportTypeDomain ->  TransportTypeResponse.from(transportTypeDomain)).toList());
        transportTypeSearchRespose.setTotalElements(pageDTO.getTotalElements());
        transportTypeSearchRespose.setTotalPages(pageDTO.getTotalPages());
        return transportTypeSearchRespose;
    }

    @Override
    @Transactional(readOnly = true)
    public TransportTypeResponse findByName(String name) {
        if (name == null) {
            return null;
        }
        TransportTypeDomain old = repo.findByName(name);
        if (old == null) {
            throw new NotFoundException("No transport type found with name '%s'.".formatted(name));
        }
        return TransportTypeResponse.from(old);
    }

    @Override
    @Transactional(readOnly = true)
    public TransportTypeResponse findByIdentify(String identify) {
        if (identify == null) {
            return null;
        }
        TransportTypeDomain old = repo.findByIdentify(identify);
        if (old == null) {
            throw new NotFoundException("No transport type found with identify '%s'.".formatted(identify));
        }
        return TransportTypeResponse.from(old);
    }
}
