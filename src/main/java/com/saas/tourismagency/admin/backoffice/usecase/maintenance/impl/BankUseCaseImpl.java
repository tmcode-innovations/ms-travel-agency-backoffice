package com.saas.tourismagency.admin.backoffice.usecase.maintenance.impl;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.core.ReferenceGuard;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.maintenance.bank.BankConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.defaultfield.maintenance.SetFieldDefaultBank;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.maintenance.bank.BankRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.maintenance.bank.PersistBankNumberCallbackImpl;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.base.SimpleBaseResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.bank.*;
import com.saas.tourismagency.admin.backoffice.core.context.UserResolver;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.BankDomain;
import com.saas.tourismagency.admin.backoffice.usecase.maintenance.BankUseCase;
import dev.mm.javaee.core.dto.PageDTO;
import dev.mm.javaee.core.exceptions.BadRequestException;
import com.saas.tourismagency.admin.backoffice.core.exceptions.MessagesValidation;
import dev.mm.javaee.core.exceptions.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
public class BankUseCaseImpl implements BankUseCase {
    @Autowired
    private ReferenceGuard referenceGuard;


    @Autowired
    private BankRepo repo;

    @Autowired
    private BankConverter converter;

    @Autowired
    private SetFieldDefaultBank setFieldDefault;

    @Autowired
    PersistBankNumberCallbackImpl persistBankNumberCallbackImpl;

    @Override
    @Transactional
    public BankResponse create(BankCreateRequest request) {
        BankDomain bankDomainExist = repo.findByName(request.getName());
        if (bankDomainExist != null){
            throw new BadRequestException(MessagesValidation.VALIDATION_ENTITY_EXIST_BY_NAME);
        }
        BankDomain newDomain = request.to();
        newDomain.setTenant(UserResolver.resolveUser().getTenant());
        newDomain.setIdentify(persistBankNumberCallbackImpl.generateNumber());
        BankDomain createdDomain = repo.create(newDomain);
        return BankResponse.from(createdDomain);
    }

    @Override
    @Transactional
    public BankResponse edit(String id, BankUpdateRequest request) {
        if (id == null) {
            return null;
        }
        BankDomain old = repo.findById(id);
        if (old == null) {
            throw new NotFoundException("No bank found with id '%s'.".formatted(id));
        }
        BankDomain bankDomainExist = repo.findByName(request.getName());
        if (bankDomainExist != null && !bankDomainExist.getId().equals(old.getId())){
            throw new BadRequestException(MessagesValidation.VALIDATION_ENTITY_EXIST_BY_NAME);
        }
        BankDomain bankDomain = request.to();
        bankDomain.setIdentify(old.getIdentify());
        BankDomain editedDomain = repo.edit(setFieldDefault.setDefaultFieldBaseUpdated(bankDomain, old));
        return BankResponse.from(editedDomain);
    }

    @Override
    @Transactional
    public void deleteById(String id) {
        BankResponse old = findById(id);
        if (referenceGuard.isReferenced("Bank", old.getId())) {
            throw new BadRequestException(MessagesValidation.VALIDATION_ENTITY_REFERENCED);
        }
        repo.deleteById(old.getId());
    }

    @Override
    @Transactional(readOnly = true)
    public List<BankResponse> findAll() {
        return repo.findAll(false).stream().map(bankDomain -> BankResponse.from(bankDomain)).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public BankResponse findById(String id) {
        if (id == null) {
            return null;
        }
        BankDomain old = repo.findById(id);
        if (old == null) {
            throw new NotFoundException("No bank found with id '%s'.".formatted(id));
        }
        return BankResponse.from(old);
    }

    @Override
    @Transactional(readOnly = true)
    public BankSearchResponse searchAll(BankSearchRequest bankSearchRequest) {
       PageDTO<BankDomain> pageDTO = repo.searchAll(bankSearchRequest);
       BankSearchResponse bankSearchRespose = new BankSearchResponse();
       bankSearchRespose.setBanks(pageDTO.getContent().stream().map(bankDomain ->  BankResponse.from(bankDomain)).toList());
       bankSearchRespose.setTotalElements(pageDTO.getTotalElements());
       bankSearchRespose.setTotalPages(pageDTO.getTotalPages());
       return bankSearchRespose;
    }

    @Override
    @Transactional(readOnly = true)
    public BankSearchResponse searchFilterAll(BankSearchFilterRequest bankSearchFilterRequest) {
        PageDTO<BankDomain> pageDTO = repo.searchAllFilter(bankSearchFilterRequest);
        BankSearchResponse bankSearchRespose = new BankSearchResponse();
        bankSearchRespose.setBanks(pageDTO.getContent().stream().map(bankDomain ->  BankResponse.from(bankDomain)).toList());
        bankSearchRespose.setTotalElements(pageDTO.getTotalElements());
        bankSearchRespose.setTotalPages(pageDTO.getTotalPages());
        return bankSearchRespose;
    }

    @Override
    @Transactional(readOnly = true)
    public BankResponse findByName(String name) {
        if (name == null) {
            return null;
        }
        BankDomain old = repo.findByName(name);
        if (old == null) {
            throw new NotFoundException("No bank found with name '%s'.".formatted(name));
        }
        return BankResponse.from(old);
    }

    @Override
    @Transactional(readOnly = true)
    public BankResponse findByIdentify(String identify) {
        if (identify == null) {
            return null;
        }
        BankDomain old = repo.findByIdentify(identify);
        if (old == null) {
            throw new NotFoundException("No bank found with identify '%s'.".formatted(identify));
        }
        return BankResponse.from(old);
    }

    @Override
    @Transactional(readOnly = true)
    public List<SimpleBaseResponse> findAllSimple() {
        return repo.findAllSimple().stream().map(simpleBaseDomain -> SimpleBaseResponse.from(simpleBaseDomain)).toList();
    }
}
