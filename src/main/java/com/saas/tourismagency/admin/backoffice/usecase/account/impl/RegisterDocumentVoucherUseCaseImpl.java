package com.saas.tourismagency.admin.backoffice.usecase.account.impl;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.account.registerdocumentvoucher.RegisterDocumentVoucherConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.defaultfield.account.SetFieldDefaultRegisterDocumentVoucher;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.account.registerdocumentvoucher.RegisterDocumentVoucherRepo;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.registerdocumentvoucher.*;
import com.saas.tourismagency.admin.backoffice.domain.account.RegisterDocumentVoucherDomain;
import com.saas.tourismagency.admin.backoffice.usecase.account.RegisterDocumentVoucherUseCase;
import dev.mm.javaee.core.dto.PageDTO;
import dev.mm.javaee.core.exceptions.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
public class RegisterDocumentVoucherUseCaseImpl implements RegisterDocumentVoucherUseCase {

    @Autowired
    private RegisterDocumentVoucherRepo repo;

    @Autowired
    private RegisterDocumentVoucherConverter converter;

    @Autowired
    private SetFieldDefaultRegisterDocumentVoucher setFieldDefault;

    @Override
    @Transactional
    public RegisterDocumentVoucherResponse create(RegisterDocumentVoucherCreateRequest request) {
        RegisterDocumentVoucherDomain createdDomain = repo.create(request.to());
        return RegisterDocumentVoucherResponse.from(createdDomain);
    }

    @Override
    @Transactional
    public RegisterDocumentVoucherResponse edit(String id, RegisterDocumentVoucherUpdateRequest request) {
        if (id == null) {
            return null;
        }
        RegisterDocumentVoucherDomain old = repo.findById(id);
        if (old == null) {
            throw new NotFoundException("No entity found with id '%s'.".formatted(id));
        }
        RegisterDocumentVoucherDomain bankDomain = request.to();
        RegisterDocumentVoucherDomain editedDomain = repo.edit(setFieldDefault.setDefaultFieldBaseUpdated(bankDomain, old));
        return RegisterDocumentVoucherResponse.from(editedDomain);
    }

    @Override
    @Transactional
    public void deleteById(String id) {
        RegisterDocumentVoucherResponse old = findById(id);
        repo.deleteById(old.getId());
    }

    @Override
    @Transactional(readOnly = true)
    public List<RegisterDocumentVoucherResponse> findAll() {
        return repo.findAll().stream().map(bankDomain -> RegisterDocumentVoucherResponse.from(bankDomain)).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public RegisterDocumentVoucherResponse findById(String id) {
        if (id == null) {
            return null;
        }
        RegisterDocumentVoucherDomain old = repo.findById(id);
        if (old == null) {
            throw new NotFoundException("No entity found with id '%s'.".formatted(id));
        }
        return RegisterDocumentVoucherResponse.from(old);
    }

    @Override
    @Transactional(readOnly = true)
    public RegisterDocumentVoucherSearchResponse searchAll(RegisterDocumentVoucherSearchRequest bankSearchRequest) {
       PageDTO<RegisterDocumentVoucherDomain> pageDTO = repo.searchAll(bankSearchRequest);
       RegisterDocumentVoucherSearchResponse bankSearchRespose = new RegisterDocumentVoucherSearchResponse();
       bankSearchRespose.setItems(pageDTO.getContent().stream().map(bankDomain ->  RegisterDocumentVoucherResponse.from(bankDomain)).toList());
       bankSearchRespose.setTotalElements(pageDTO.getTotalElements());
       bankSearchRespose.setTotalPages(pageDTO.getTotalPages());
       return bankSearchRespose;
    }

    @Override
    @Transactional(readOnly = true)
    public RegisterDocumentVoucherSearchResponse searchFilterAll(RegisterDocumentVoucherSearchFilterRequest bankSearchFilterRequest) {
        PageDTO<RegisterDocumentVoucherDomain> pageDTO = repo.searchAllFilter(bankSearchFilterRequest);
        RegisterDocumentVoucherSearchResponse bankSearchRespose = new RegisterDocumentVoucherSearchResponse();
        bankSearchRespose.setItems(pageDTO.getContent().stream().map(bankDomain ->  RegisterDocumentVoucherResponse.from(bankDomain)).toList());
        bankSearchRespose.setTotalElements(pageDTO.getTotalElements());
        bankSearchRespose.setTotalPages(pageDTO.getTotalPages());
        return bankSearchRespose;
    }
}
