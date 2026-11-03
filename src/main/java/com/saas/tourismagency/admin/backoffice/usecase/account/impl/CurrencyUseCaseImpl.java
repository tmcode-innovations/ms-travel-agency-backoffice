package com.saas.tourismagency.admin.backoffice.usecase.account.impl;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.core.ReferenceGuard;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.account.currency.CurrencyConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.defaultfield.account.SetFieldDefaultCurrency;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.account.currency.CurrencyRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.account.currency.PersistCurrencyNumberCallbackImpl;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.currency.*;
import com.saas.tourismagency.admin.backoffice.core.context.UserResolver;
import com.saas.tourismagency.admin.backoffice.core.exceptions.MessagesValidation;
import com.saas.tourismagency.admin.backoffice.domain.account.CurrencyDomain;
import com.saas.tourismagency.admin.backoffice.usecase.account.CurrencyUseCase;
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
public class CurrencyUseCaseImpl implements CurrencyUseCase {
    @Autowired
    private ReferenceGuard referenceGuard;


    @Autowired
    private CurrencyRepo repo;

    @Autowired
    private CurrencyConverter converter;

    @Autowired
    private SetFieldDefaultCurrency setFieldDefault;

    @Autowired
    PersistCurrencyNumberCallbackImpl persistCurrencyNumberCallbackImpl;

    @Override
    @Transactional
    public CurrencyResponse create(CurrencyCreateRequest request) {
        CurrencyDomain currencyDomainExist = repo.findByName(request.getName());
        if (currencyDomainExist != null){
            throw new BadRequestException(MessagesValidation.VALIDATION_ENTITY_EXIST_BY_NAME);
        }
        CurrencyDomain newDomain = request.to();
        newDomain.setTenant(UserResolver.resolveUser().getTenant());
        newDomain.setIdentify(persistCurrencyNumberCallbackImpl.generateNumber());
        CurrencyDomain createdDomain = repo.create(newDomain);
        return CurrencyResponse.from(createdDomain);
    }

    @Override
    @Transactional
    public CurrencyResponse edit(String id, CurrencyUpdateRequest request) {
        if (id == null) {
            return null;
        }
        CurrencyDomain old = repo.findById(id);
        if (old == null) {
            throw new NotFoundException("No currency found with id '%s'.".formatted(id));
        }
        CurrencyDomain currencyDomainExist = repo.findByName(request.getName());
        if (currencyDomainExist != null && !currencyDomainExist.getId().equals(old.getId())){
            throw new BadRequestException(MessagesValidation.VALIDATION_ENTITY_EXIST_BY_NAME);
        }
        CurrencyDomain currencyDomain = request.to();
        currencyDomain.setIdentify(old.getIdentify());
        CurrencyDomain editedDomain = repo.edit(setFieldDefault.setDefaultFieldBaseUpdated(currencyDomain, old));
        return CurrencyResponse.from(editedDomain);
    }

    @Override
    @Transactional
    public void deleteById(String id) {
        CurrencyResponse old = findById(id);
        if (referenceGuard.isReferenced("Currency", old.getId())) {
            throw new BadRequestException(MessagesValidation.VALIDATION_ENTITY_REFERENCED);
        }
        repo.deleteById(old.getId());
    }

    @Override
    @Transactional(readOnly = true)
    public List<CurrencyResponse> findAll() {
        return repo.findAll(false).stream().map(currencyDomain -> CurrencyResponse.from(currencyDomain)).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public CurrencyResponse findById(String id) {
        if (id == null) {
            return null;
        }
        CurrencyDomain old = repo.findById(id);
        if (old == null) {
            throw new NotFoundException("No entity found with id '%s'.".formatted(id));
        }
        return CurrencyResponse.from(old);
    }

    @Override
    @Transactional(readOnly = true)
    public CurrencySearchResponse searchAll(CurrencySearchRequest bankSearchRequest) {
       PageDTO<CurrencyDomain> pageDTO = repo.searchAll(bankSearchRequest);
       CurrencySearchResponse bankSearchRespose = new CurrencySearchResponse();
       bankSearchRespose.setItems(pageDTO.getContent().stream().map(bankDomain ->  CurrencyResponse.from(bankDomain)).toList());
       bankSearchRespose.setTotalElements(pageDTO.getTotalElements());
       bankSearchRespose.setTotalPages(pageDTO.getTotalPages());
       return bankSearchRespose;
    }

    @Override
    @Transactional(readOnly = true)
    public CurrencySearchResponse searchFilterAll(CurrencySearchFilterRequest bankSearchFilterRequest) {
        PageDTO<CurrencyDomain> pageDTO = repo.searchAllFilter(bankSearchFilterRequest);
        CurrencySearchResponse bankSearchRespose = new CurrencySearchResponse();
        bankSearchRespose.setItems(pageDTO.getContent().stream().map(bankDomain ->  CurrencyResponse.from(bankDomain)).toList());
        bankSearchRespose.setTotalElements(pageDTO.getTotalElements());
        bankSearchRespose.setTotalPages(pageDTO.getTotalPages());
        return bankSearchRespose;
    }

    @Override
    @Transactional(readOnly = true)
    public CurrencyResponse findByName(String name) {
        if (name == null) {
            return null;
        }
        CurrencyDomain old = repo.findByName(name);
        if (old == null) {
            throw new NotFoundException("No currency found with name '%s'.".formatted(name));
        }
        return CurrencyResponse.from(old);
    }

    @Override
    @Transactional(readOnly = true)
    public CurrencyResponse findByIdentify(String identify) {
        if (identify == null) {
            return null;
        }
        CurrencyDomain old = repo.findByIdentify(identify);
        if (old == null) {
            throw new NotFoundException("No currency found with identify '%s'.".formatted(identify));
        }
        return CurrencyResponse.from(old);
    }
}
