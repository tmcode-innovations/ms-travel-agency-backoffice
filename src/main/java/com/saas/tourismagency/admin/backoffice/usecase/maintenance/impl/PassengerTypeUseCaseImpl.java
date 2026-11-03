package com.saas.tourismagency.admin.backoffice.usecase.maintenance.impl;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.core.ReferenceGuard;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.maintenance.passengertype.PassengerTypeConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.defaultfield.maintenance.SetFieldDefaultPassengerType;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.maintenance.passengertype.PassengerTypeRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.maintenance.passengertype.PersistPassengerTypeNumberCallbackImpl;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.passengertype.*;
import com.saas.tourismagency.admin.backoffice.core.context.UserResolver;
import com.saas.tourismagency.admin.backoffice.core.exceptions.MessagesValidation;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.PassengerTypeDomain;
import com.saas.tourismagency.admin.backoffice.usecase.maintenance.PassengerTypeUseCase;
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
public class PassengerTypeUseCaseImpl implements PassengerTypeUseCase {
    @Autowired
    private ReferenceGuard referenceGuard;


    @Autowired
    private PassengerTypeRepo repo;

    @Autowired
    private PassengerTypeConverter converter;

    @Autowired
    private SetFieldDefaultPassengerType setFieldDefault;

    @Autowired
    PersistPassengerTypeNumberCallbackImpl persistPassengerTypeNumberCallbackImpl;

    @Override
    @Transactional
    public PassengerTypeResponse create(PassengerTypeCreateRequest request) {
        PassengerTypeDomain passengerTypeDomainExist = repo.findByName(request.getName());
        if (passengerTypeDomainExist != null){
            throw new BadRequestException(MessagesValidation.VALIDATION_ENTITY_EXIST_BY_NAME);
        }
        PassengerTypeDomain newDomain = request.to();
        newDomain.setIdentify(persistPassengerTypeNumberCallbackImpl.generateNumber());
        newDomain.setTenant(UserResolver.resolveUser().getTenant());
        PassengerTypeDomain createdDomain = repo.create(newDomain);
        return PassengerTypeResponse.from(createdDomain);
    }

    @Override
    @Transactional
    public PassengerTypeResponse edit(String id, PassengerTypeUpdateRequest request) {
        if (id == null) {
            return null;
        }
        PassengerTypeDomain old = repo.findById(id);
        if (old == null) {
            throw new NotFoundException("No passenger type found with id '%s'.".formatted(id));
        }
        PassengerTypeDomain passengerTypeDomainExist = repo.findByName(request.getName());
        if (passengerTypeDomainExist != null && !passengerTypeDomainExist.getId().equals(old.getId())){
            throw new BadRequestException(MessagesValidation.VALIDATION_ENTITY_EXIST_BY_NAME);
        }
        PassengerTypeDomain passengerTypeDomain = request.to();
        passengerTypeDomain.setIdentify(old.getIdentify());
        PassengerTypeDomain editedDomain = repo.edit(setFieldDefault.setDefaultFieldBaseUpdated(passengerTypeDomain, old));
        return PassengerTypeResponse.from(editedDomain);
    }

    @Override
    @Transactional
    public void deleteById(String id) {
        PassengerTypeResponse old = findById(id);
        if (referenceGuard.isReferenced("PassengerType", old.getId())) {
            throw new BadRequestException(MessagesValidation.VALIDATION_ENTITY_REFERENCED);
        }
        repo.deleteById(old.getId());
    }

    @Override
    @Transactional(readOnly = true)
    public List<PassengerTypeResponse> findAll() {
        return repo.findAll(false).stream().map(passengerTypeDomain -> PassengerTypeResponse.from(passengerTypeDomain)).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public PassengerTypeResponse findById(String id) {
        if (id == null) {
            return null;
        }
        PassengerTypeDomain old = repo.findById(id);
        if (old == null) {
            throw new NotFoundException("No passenger type found with id '%s'.".formatted(id));
        }
        return PassengerTypeResponse.from(old);
    }

    @Override
    @Transactional(readOnly = true)
    public PassengerTypeSearchResponse searchAll(PassengerTypeSearchRequest passengerTypeSearchRequest) {
       PageDTO<PassengerTypeDomain> pageDTO = repo.searchAll(passengerTypeSearchRequest);
       PassengerTypeSearchResponse passengerTypeSearchRespose = new PassengerTypeSearchResponse();
       passengerTypeSearchRespose.setPassengerTypes(pageDTO.getContent().stream().map(passengerTypeDomain ->  PassengerTypeResponse.from(passengerTypeDomain)).toList());
       passengerTypeSearchRespose.setTotalElements(pageDTO.getTotalElements());
       passengerTypeSearchRespose.setTotalPages(pageDTO.getTotalPages());
       return passengerTypeSearchRespose;
    }

    @Override
    @Transactional(readOnly = true)
    public PassengerTypeSearchResponse searchFilterAll(PassengerTypeSearchFilterRequest passengerTypeSearchFilterRequest) {
        PageDTO<PassengerTypeDomain> pageDTO = repo.searchAllFilter(passengerTypeSearchFilterRequest);
        PassengerTypeSearchResponse passengerTypeSearchResponse = new PassengerTypeSearchResponse();
        passengerTypeSearchResponse.setPassengerTypes(pageDTO.getContent().stream().map(passengerTypeDomain ->  PassengerTypeResponse.from(passengerTypeDomain)).toList());
        passengerTypeSearchResponse.setTotalElements(pageDTO.getTotalElements());
        passengerTypeSearchResponse.setTotalPages(pageDTO.getTotalPages());
        return passengerTypeSearchResponse;
    }
}
