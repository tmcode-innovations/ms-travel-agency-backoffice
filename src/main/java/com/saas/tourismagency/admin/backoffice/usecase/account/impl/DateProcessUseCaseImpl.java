package com.saas.tourismagency.admin.backoffice.usecase.account.impl;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.account.dateprocess.DateProcessConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.defaultfield.account.SetFieldDefaultDateProcess;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.account.dateprocess.DateProcessRepo;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.dateprocess.*;
import com.saas.tourismagency.admin.backoffice.core.context.UserResolver;
import com.saas.tourismagency.admin.backoffice.domain.account.DateProcessDomain;
import com.saas.tourismagency.admin.backoffice.usecase.account.DateProcessUseCase;
import dev.mm.javaee.core.dto.PageDTO;
import dev.mm.javaee.core.exceptions.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@Service
public class DateProcessUseCaseImpl implements DateProcessUseCase {

    @Autowired
    private DateProcessRepo repo;

    @Autowired
    private DateProcessConverter converter;

    @Autowired
    private SetFieldDefaultDateProcess setFieldDefault;

    @Override
    @Transactional
    public DateProcessResponse create(DateProcessCreateRequest request) {
        DateProcessDomain createdDomain = repo.create(request.to());
        return DateProcessResponse.from(createdDomain);
    }

    @Override
    @Transactional
    public DateProcessResponse edit(String id, DateProcessUpdateRequest request) {
        if (id == null) {
            return null;
        }
        DateProcessDomain old = repo.findById(id);
        if (old == null) {
            throw new NotFoundException("No entity found with id '%s'.".formatted(id));
        }
        DateProcessDomain bankDomain = request.to();
        DateProcessDomain editedDomain = repo.edit(setFieldDefault.setDefaultFieldBaseUpdated(bankDomain, old));
        return DateProcessResponse.from(editedDomain);
    }

    @Override
    @Transactional
    public void deleteById(String id) {
        DateProcessResponse old = findById(id);
        repo.deleteById(old.getId());
    }

    @Override
    @Transactional(readOnly = true)
    public List<DateProcessResponse> findAll() {
        return repo.findAll().stream().map(bankDomain -> DateProcessResponse.from(bankDomain)).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public DateProcessResponse findById(String id) {
        if (id == null) {
            return null;
        }
        DateProcessDomain old = repo.findById(id);
        if (old == null) {
            throw new NotFoundException("No entity found with id '%s'.".formatted(id));
        }
        return DateProcessResponse.from(old);
    }

    @Override
    @Transactional(readOnly = true)
    public DateProcessSearchResponse searchAll(DateProcessSearchRequest bankSearchRequest) {
       PageDTO<DateProcessDomain> pageDTO = repo.searchAll(bankSearchRequest);
       DateProcessSearchResponse bankSearchRespose = new DateProcessSearchResponse();
       bankSearchRespose.setItems(pageDTO.getContent().stream().map(bankDomain ->  DateProcessResponse.from(bankDomain)).toList());
       bankSearchRespose.setTotalElements(pageDTO.getTotalElements());
       bankSearchRespose.setTotalPages(pageDTO.getTotalPages());
       return bankSearchRespose;
    }

    @Override
    @Transactional(readOnly = true)
    public DateProcessSearchResponse searchFilterAll(DateProcessSearchFilterRequest bankSearchFilterRequest) {
        PageDTO<DateProcessDomain> pageDTO = repo.searchAllFilter(bankSearchFilterRequest);
        DateProcessSearchResponse bankSearchRespose = new DateProcessSearchResponse();
        bankSearchRespose.setItems(pageDTO.getContent().stream().map(bankDomain ->  DateProcessResponse.from(bankDomain)).toList());
        bankSearchRespose.setTotalElements(pageDTO.getTotalElements());
        bankSearchRespose.setTotalPages(pageDTO.getTotalPages());
        return bankSearchRespose;
    }

    @Override
    @Transactional(readOnly = true)
    public DateProcessResponse findDateProcessCurrent() {
        LocalDate dateCurrent = LocalDate.now();
        DateProcessDomain dateProcessDomain = repo.findByDate(dateCurrent, UserResolver.resolveUser().getTenant());
        if (dateProcessDomain == null) {
            throw new NotFoundException("No entity current found.");
        }
        return DateProcessResponse.from(dateProcessDomain);
    }
}
