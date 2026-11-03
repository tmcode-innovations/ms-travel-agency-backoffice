package com.saas.tourismagency.admin.backoffice.usecase.booking.impl;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.booking.registerbookingbase.RegisterBookingBaseConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.defaultfield.booking.SetFieldDefaultRegisterBookingBase;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.booking.registerbookingbase.RegisterBookingBaseRepo;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.registerbookingbase.*;
import com.saas.tourismagency.admin.backoffice.core.context.UserResolver;
import com.saas.tourismagency.admin.backoffice.domain.booking.RegisterBookingBaseDomain;
import com.saas.tourismagency.admin.backoffice.usecase.booking.RegisterBookingBaseUseCase;
import dev.mm.javaee.core.dto.PageDTO;
import dev.mm.javaee.core.exceptions.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
public class RegisterBookingBaseUseCaseImpl implements RegisterBookingBaseUseCase {

    @Autowired
    private RegisterBookingBaseRepo repo;

    @Autowired
    private RegisterBookingBaseConverter converter;

    @Autowired
    private SetFieldDefaultRegisterBookingBase setFieldDefault;

    @Override
    @Transactional
    public RegisterBookingBaseResponse create(RegisterBookingBaseCreateRequest request) {
        RegisterBookingBaseDomain newDomain = request.to();
        newDomain.setTenant(UserResolver.resolveUser().getTenant());
        RegisterBookingBaseDomain createdDomain = repo.create(newDomain);
        return RegisterBookingBaseResponse.from(createdDomain);
    }

    @Override
    @Transactional
    public RegisterBookingBaseResponse edit(String id, RegisterBookingBaseUpdateRequest request) {
        if (id == null) {
            return null;
        }
        RegisterBookingBaseDomain old = repo.findById(id);
        if (old == null) {
            throw new NotFoundException("No entity found with id '%s'.".formatted(id));
        }
        RegisterBookingBaseDomain bankDomain = request.to();
        RegisterBookingBaseDomain editedDomain = repo.edit(setFieldDefault.setDefaultFieldBaseUpdated(bankDomain, old));
        return RegisterBookingBaseResponse.from(editedDomain);
    }

    @Override
    @Transactional
    public void deleteById(String id) {
        RegisterBookingBaseResponse old = findById(id);
        repo.deleteById(old.getId());
    }

    @Override
    @Transactional(readOnly = true)
    public List<RegisterBookingBaseResponse> findAll() {
        return repo.findAll().stream().map(bankDomain -> RegisterBookingBaseResponse.from(bankDomain)).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public RegisterBookingBaseResponse findById(String id) {
        if (id == null) {
            return null;
        }
        RegisterBookingBaseDomain old = repo.findById(id);
        if (old == null) {
            throw new NotFoundException("No entity found with id '%s'.".formatted(id));
        }
        return RegisterBookingBaseResponse.from(old);
    }

    @Override
    @Transactional(readOnly = true)
    public RegisterBookingBaseSearchResponse searchAll(RegisterBookingBaseSearchRequest bankSearchRequest) {
       PageDTO<RegisterBookingBaseDomain> pageDTO = repo.searchAll(bankSearchRequest);
       RegisterBookingBaseSearchResponse bankSearchRespose = new RegisterBookingBaseSearchResponse();
       bankSearchRespose.setItems(pageDTO.getContent().stream().map(bankDomain ->  RegisterBookingBaseResponse.from(bankDomain)).toList());
       bankSearchRespose.setTotalElements(pageDTO.getTotalElements());
       bankSearchRespose.setTotalPages(pageDTO.getTotalPages());
       return bankSearchRespose;
    }

    @Override
    @Transactional(readOnly = true)
    public RegisterBookingBaseSearchResponse searchFilterAll(RegisterBookingBaseSearchFilterRequest bankSearchFilterRequest) {
        PageDTO<RegisterBookingBaseDomain> pageDTO = repo.searchAllFilter(bankSearchFilterRequest);
        RegisterBookingBaseSearchResponse bankSearchRespose = new RegisterBookingBaseSearchResponse();
        bankSearchRespose.setItems(pageDTO.getContent().stream().map(bankDomain ->  RegisterBookingBaseResponse.from(bankDomain)).toList());
        bankSearchRespose.setTotalElements(pageDTO.getTotalElements());
        bankSearchRespose.setTotalPages(pageDTO.getTotalPages());
        return bankSearchRespose;
    }
}
