package com.saas.tourismagency.admin.backoffice.usecase.account.impl;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.account.documentfinance.DocumentFinanceConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.defaultfield.account.SetFieldDefaultDocumentFinance;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.account.documentfinance.DocumentFinanceRepo;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.documentfinance.*;
import com.saas.tourismagency.admin.backoffice.domain.account.DocumentFinanceDomain;
import com.saas.tourismagency.admin.backoffice.usecase.account.DocumentFinanceUseCase;
import dev.mm.javaee.core.dto.PageDTO;
import dev.mm.javaee.core.exceptions.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
public class DocumentFinanceUseCaseImpl implements DocumentFinanceUseCase {

    @Autowired
    private DocumentFinanceRepo repo;

    @Autowired
    private DocumentFinanceConverter converter;

    @Autowired
    private SetFieldDefaultDocumentFinance setFieldDefault;

    @Override
    @Transactional
    public DocumentFinanceResponse create(DocumentFinanceCreateRequest request) {
        DocumentFinanceDomain createdDomain = repo.create(request.to());
        return DocumentFinanceResponse.from(createdDomain);
    }

    @Override
    @Transactional
    public DocumentFinanceResponse edit(String id, DocumentFinanceUpdateRequest request) {
        if (id == null) {
            return null;
        }
        DocumentFinanceDomain old = repo.findById(id);
        if (old == null) {
            throw new NotFoundException("No entity found with id '%s'.".formatted(id));
        }
        DocumentFinanceDomain bankDomain = request.to();
        DocumentFinanceDomain editedDomain = repo.edit(setFieldDefault.setDefaultFieldBaseUpdated(bankDomain, old));
        return DocumentFinanceResponse.from(editedDomain);
    }

    @Override
    @Transactional
    public void deleteById(String id) {
        DocumentFinanceResponse old = findById(id);
        repo.deleteById(old.getId());
    }

    @Override
    @Transactional(readOnly = true)
    public List<DocumentFinanceResponse> findAll() {
        return repo.findAll().stream().map(bankDomain -> DocumentFinanceResponse.from(bankDomain)).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public DocumentFinanceResponse findById(String id) {
        if (id == null) {
            return null;
        }
        DocumentFinanceDomain old = repo.findById(id);
        if (old == null) {
            throw new NotFoundException("No entity found with id '%s'.".formatted(id));
        }
        return DocumentFinanceResponse.from(old);
    }

    @Override
    @Transactional(readOnly = true)
    public DocumentFinanceSearchResponse searchAll(DocumentFinanceSearchRequest bankSearchRequest) {
       PageDTO<DocumentFinanceDomain> pageDTO = repo.searchAll(bankSearchRequest);
       DocumentFinanceSearchResponse bankSearchRespose = new DocumentFinanceSearchResponse();
       bankSearchRespose.setItems(pageDTO.getContent().stream().map(bankDomain ->  DocumentFinanceResponse.from(bankDomain)).toList());
       bankSearchRespose.setTotalElements(pageDTO.getTotalElements());
       bankSearchRespose.setTotalPages(pageDTO.getTotalPages());
       return bankSearchRespose;
    }

    @Override
    @Transactional(readOnly = true)
    public DocumentFinanceSearchResponse searchFilterAll(DocumentFinanceSearchFilterRequest bankSearchFilterRequest) {
        PageDTO<DocumentFinanceDomain> pageDTO = repo.searchAllFilter(bankSearchFilterRequest);
        DocumentFinanceSearchResponse bankSearchRespose = new DocumentFinanceSearchResponse();
        bankSearchRespose.setItems(pageDTO.getContent().stream().map(bankDomain ->  DocumentFinanceResponse.from(bankDomain)).toList());
        bankSearchRespose.setTotalElements(pageDTO.getTotalElements());
        bankSearchRespose.setTotalPages(pageDTO.getTotalPages());
        return bankSearchRespose;
    }
}
