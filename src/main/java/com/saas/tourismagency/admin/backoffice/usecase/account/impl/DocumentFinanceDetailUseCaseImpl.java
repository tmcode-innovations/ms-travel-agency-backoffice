package com.saas.tourismagency.admin.backoffice.usecase.account.impl;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.account.documentfinancedetail.DocumentFinanceDetailConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.defaultfield.account.SetFieldDefaultDocumentFinanceDetail;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.account.changetype.ChangeTypeRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.account.documentfinance.DocumentFinanceRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.account.documentfinancedetail.DocumentFinanceDetailRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.booking.registergroup.RegisterGroupRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.maintenance.entry.EntryRepo;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.collection.CollectionResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.documentfinancedetail.*;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.voucheringressdetail.VoucherIngressDetailResponse;
import com.saas.tourismagency.admin.backoffice.core.context.UserResolver;
import com.saas.tourismagency.admin.backoffice.domain.account.*;
import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.EntryDomain;
import com.saas.tourismagency.admin.backoffice.usecase.account.DocumentFinanceDetailUseCase;
import dev.mm.javaee.core.dto.PageDTO;
import dev.mm.javaee.core.exceptions.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Slf4j
@Service
public class DocumentFinanceDetailUseCaseImpl implements DocumentFinanceDetailUseCase {

    @Autowired
    private DocumentFinanceDetailRepo repo;

    @Autowired
    private DocumentFinanceDetailConverter converter;

    @Autowired
    private SetFieldDefaultDocumentFinanceDetail setFieldDefault;

    @Autowired
    private EntryRepo entryRepo;

    @Autowired
    private ChangeTypeRepo changeTypeRepo;

    @Autowired
    private DocumentFinanceRepo documentFinanceRepo;

    @Override
    @Transactional
    public DocumentFinanceDetailResponse create(DocumentFinanceDetailCreateRequest request) {
        EntryDomain entryDomain = null;
        if (request.getEntry() != null && !request.getEntry().isEmpty()){
            IdBaseDomain entryIdBaseDomain = entryRepo.findByIdEntity(request.getEntry());
            entryDomain = new EntryDomain();
            entryDomain.setId(entryIdBaseDomain.getId());
        }

        ChangeTypeDomain changeTypeDomain = null;
        if (request.getChangeType() != null && !request.getChangeType().isEmpty()){
            IdBaseDomain changeTypeIdBaseDomain = changeTypeRepo.findByIdEntity(request.getChangeType());
            changeTypeDomain = new ChangeTypeDomain();
            changeTypeDomain.setId(changeTypeIdBaseDomain.getId());
        }

        DocumentFinanceDomain documentFinanceDomain = null;
        if (request.getDocumentFinance() != null && !request.getDocumentFinance().isEmpty()){
            IdBaseDomain documentFinanceIdBaseDomain = documentFinanceRepo.findByIdEntity(request.getDocumentFinance());
            documentFinanceDomain = new DocumentFinanceDomain();
            documentFinanceDomain.setId(documentFinanceIdBaseDomain.getId());
        }

        DocumentFinanceDetailDomain newDomain = request.to(documentFinanceDomain, changeTypeDomain, entryDomain);
        newDomain.setTenant(UserResolver.resolveUser().getTenant());

        if (documentFinanceDomain != null && documentFinanceDomain.getId() != null){
            newDomain.setNoItem(this.getLastIndexDocumentFinanceDetail(documentFinanceDomain.getId()));
        }

        DocumentFinanceDetailDomain createdDomain = repo.create(newDomain);
        return DocumentFinanceDetailResponse.from(createdDomain);
    }

    @Override
    public DocumentFinanceDetailResponse create(DocumentFinanceDetailCreateRequest request, DocumentFinanceDomain documentFinanceDomain) {

        EntryDomain entryDomain = null;
        if (request.getEntry() != null && !request.getEntry().isEmpty()){
            IdBaseDomain entryIdBaseDomain = entryRepo.findByIdEntity(request.getEntry());
            entryDomain = new EntryDomain();
            entryDomain.setId(entryIdBaseDomain.getId());
        }

        ChangeTypeDomain changeTypeDomain = null;
        if (request.getChangeType() != null && !request.getChangeType().isEmpty()){
            IdBaseDomain changeTypeIdBaseDomain = changeTypeRepo.findByIdEntity(request.getChangeType());
            changeTypeDomain = new ChangeTypeDomain();
            changeTypeDomain.setId(changeTypeIdBaseDomain.getId());
        }

        DocumentFinanceDetailDomain newDomain = request.to(documentFinanceDomain, changeTypeDomain, entryDomain);
        newDomain.setTenant(UserResolver.resolveUser().getTenant());

        if (documentFinanceDomain != null && documentFinanceDomain.getId() != null){
            newDomain.setNoItem(this.getLastIndexDocumentFinanceDetail(documentFinanceDomain.getId()));
        }

        DocumentFinanceDetailDomain createdDomain = repo.create(newDomain);
        return DocumentFinanceDetailResponse.from(createdDomain);
    }

    @Override
    @Transactional
    public DocumentFinanceDetailResponse edit(String id, DocumentFinanceDetailUpdateRequest request) {
        if (id == null) {
            return null;
        }
        DocumentFinanceDetailDomain old = repo.findById(id);
        if (old == null) {
            throw new NotFoundException("No entity found with id '%s'.".formatted(id));
        }

        EntryDomain entryDomain = null;
        if (request.getEntry() != null && !request.getEntry().isEmpty()){
            IdBaseDomain entryIdBaseDomain = entryRepo.findByIdEntity(request.getEntry());
            entryDomain = new EntryDomain();
            entryDomain.setId(entryIdBaseDomain.getId());
        }

        ChangeTypeDomain changeTypeDomain = null;
        if (request.getChangeType() != null && !request.getChangeType().isEmpty()){
            IdBaseDomain changeTypeIdBaseDomain = changeTypeRepo.findByIdEntity(request.getChangeType());
            changeTypeDomain = new ChangeTypeDomain();
            changeTypeDomain.setId(changeTypeIdBaseDomain.getId());
        }

        DocumentFinanceDomain documentFinanceDomain = null;
        if (request.getDocumentFinance() != null && !request.getDocumentFinance().isEmpty()){
            IdBaseDomain documentFinanceIdBaseDomain = documentFinanceRepo.findByIdEntity(request.getDocumentFinance());
            documentFinanceDomain = new DocumentFinanceDomain();
            documentFinanceDomain.setId(documentFinanceIdBaseDomain.getId());
        }

        DocumentFinanceDetailDomain bankDomain = request.to(documentFinanceDomain, changeTypeDomain, entryDomain);
        DocumentFinanceDetailDomain editedDomain = repo.edit(setFieldDefault.setDefaultFieldBaseUpdated(bankDomain, old));
        return DocumentFinanceDetailResponse.from(editedDomain);
    }

    @Override
    public DocumentFinanceDetailResponse edit(String id, DocumentFinanceDetailUpdateRequest request, DocumentFinanceDomain documentFinanceDomain) {
        if (id == null) {
            return null;
        }
        DocumentFinanceDetailDomain old = repo.findById(id);
        if (old == null) {
            throw new NotFoundException("No entity found with id '%s'.".formatted(id));
        }

        EntryDomain entryDomain = null;
        if (request.getEntry() != null && !request.getEntry().isEmpty()){
            IdBaseDomain entryIdBaseDomain = entryRepo.findByIdEntity(request.getEntry());
            entryDomain = new EntryDomain();
            entryDomain.setId(entryIdBaseDomain.getId());
        }

        ChangeTypeDomain changeTypeDomain = null;
        if (request.getChangeType() != null && !request.getChangeType().isEmpty()){
            IdBaseDomain changeTypeIdBaseDomain = changeTypeRepo.findByIdEntity(request.getChangeType());
            changeTypeDomain = new ChangeTypeDomain();
            changeTypeDomain.setId(changeTypeIdBaseDomain.getId());
        }

        DocumentFinanceDetailDomain bankDomain = request.to(documentFinanceDomain, changeTypeDomain, entryDomain);
        DocumentFinanceDetailDomain editedDomain = repo.edit(setFieldDefault.setDefaultFieldBaseUpdated(bankDomain, old));
        return DocumentFinanceDetailResponse.from(editedDomain);
    }

    @Override
    @Transactional
    public void deleteById(String id) {
        DocumentFinanceDetailResponse old = findById(id);
        repo.deleteById(old.getId());
    }

    @Override
    @Transactional(readOnly = true)
    public List<DocumentFinanceDetailResponse> findAll() {
        return repo.findAll().stream().map(bankDomain -> DocumentFinanceDetailResponse.from(bankDomain)).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public DocumentFinanceDetailResponse findById(String id) {
        if (id == null) {
            return null;
        }
        DocumentFinanceDetailDomain old = repo.findById(id);
        if (old == null) {
            throw new NotFoundException("No entity found with id '%s'.".formatted(id));
        }
        return DocumentFinanceDetailResponse.from(old);
    }

    @Override
    @Transactional(readOnly = true)
    public DocumentFinanceDetailSearchResponse searchAll(DocumentFinanceDetailSearchRequest bankSearchRequest) {
       PageDTO<DocumentFinanceDetailDomain> pageDTO = repo.searchAll(bankSearchRequest);
       DocumentFinanceDetailSearchResponse bankSearchRespose = new DocumentFinanceDetailSearchResponse();
       bankSearchRespose.setItems(pageDTO.getContent().stream().map(bankDomain ->  DocumentFinanceDetailResponse.from(bankDomain)).toList());
       bankSearchRespose.setTotalElements(pageDTO.getTotalElements());
       bankSearchRespose.setTotalPages(pageDTO.getTotalPages());
       return bankSearchRespose;
    }

    @Override
    @Transactional(readOnly = true)
    public DocumentFinanceDetailSearchResponse searchFilterAll(DocumentFinanceDetailSearchFilterRequest bankSearchFilterRequest) {
        PageDTO<DocumentFinanceDetailDomain> pageDTO = repo.searchAllFilter(bankSearchFilterRequest);
        DocumentFinanceDetailSearchResponse bankSearchRespose = new DocumentFinanceDetailSearchResponse();
        bankSearchRespose.setItems(pageDTO.getContent().stream().map(bankDomain ->  DocumentFinanceDetailResponse.from(bankDomain)).toList());
        bankSearchRespose.setTotalElements(pageDTO.getTotalElements());
        bankSearchRespose.setTotalPages(pageDTO.getTotalPages());
        return bankSearchRespose;
    }

    @Override
    public DocumentFinanceDetailResponse searchByDocumentFinanceAndDocumentFinanceDetail(String idDocumentFinance, String idDocumentFinanceDetail) {
        DocumentFinanceDetailDomain documentFinanceDetailDomain = repo.searchAllByDocumentFinanceAndDocumentFinanceDetail(idDocumentFinance, idDocumentFinanceDetail);
        return DocumentFinanceDetailResponse.from(documentFinanceDetailDomain);
    }

    private Integer getLastIndexDocumentFinanceDetail(String idDocumentFinance) {
        List<DocumentFinanceDetailDomain> list = repo.searchAllByDocumentFinanceSimpleDomain(idDocumentFinance);
        Integer max = list.stream()
                .map(DocumentFinanceDetailDomain::getNoItem)
                .filter(Objects::nonNull)
                .max(Integer::compareTo)
                .orElse(0);
        return max + 1;
    }
}
