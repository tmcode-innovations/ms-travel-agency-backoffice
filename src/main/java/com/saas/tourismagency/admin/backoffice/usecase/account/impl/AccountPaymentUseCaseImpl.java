package com.saas.tourismagency.admin.backoffice.usecase.account.impl;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.account.accountpayment.AccountPaymentConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.defaultfield.account.SetFieldDefaultAccountPayment;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.account.accountpayment.AccountPaymentRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.account.changetype.ChangeTypeRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.account.proforma.ProformaRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.maintenance.bank.BankRepo;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.accountpayment.*;
import com.saas.tourismagency.admin.backoffice.core.context.UserResolver;
import com.saas.tourismagency.admin.backoffice.domain.account.AccountPaymentDomain;
import com.saas.tourismagency.admin.backoffice.domain.account.ChangeTypeDomain;
import com.saas.tourismagency.admin.backoffice.domain.account.ProformaDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.BankDomain;
import com.saas.tourismagency.admin.backoffice.usecase.account.AccountPaymentUseCase;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.proforma.ProformaSearchFilterRequest;
import com.saas.tourismagency.admin.backoffice.domain.account.enums.PaymentTypeEnum;
import dev.mm.javaee.core.dto.PageDTO;
import dev.mm.javaee.core.exceptions.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class AccountPaymentUseCaseImpl implements AccountPaymentUseCase {

    @Autowired
    private AccountPaymentRepo repo;

    @Autowired
    private AccountPaymentConverter converter;

    @Autowired
    private SetFieldDefaultAccountPayment setFieldDefault;

    @Autowired
    private ProformaRepo proformaRepo;

    @Autowired
    private BankRepo bankRepo;

    @Autowired
    private ChangeTypeRepo changeTypeRepo;

    @Override
    @Transactional
    public AccountPaymentResponse create(AccountPaymentCreateRequest request) {
        ProformaDomain proformaDomain = null;
        if (request.getProforma() != null && !request.getProforma().isEmpty()){
            IdBaseDomain proformaIdBaseDomain = proformaRepo.findByIdEntity(request.getProforma());
            proformaDomain = new ProformaDomain();
            proformaDomain.setId(proformaIdBaseDomain.getId());
        }

        BankDomain bankDomain = null;
        if (request.getBank() != null && !request.getBank().isEmpty()){
            IdBaseDomain bankIdBaseDomain = bankRepo.findByIdEntity(request.getBank());
            bankDomain = new BankDomain();
            bankDomain.setId(bankIdBaseDomain.getId());
        }

        ChangeTypeDomain changeTypeDomain = null;
        if (request.getChangeType() != null && !request.getChangeType().isEmpty()){
            IdBaseDomain changeTypeIdBaseDomain = changeTypeRepo.findByIdEntity(request.getChangeType());
            changeTypeDomain = new ChangeTypeDomain();
            changeTypeDomain.setId(changeTypeIdBaseDomain.getId());
        }

        AccountPaymentDomain newDomain = request.to(proformaDomain, bankDomain, changeTypeDomain);
        newDomain.setTenant(UserResolver.resolveUser().getTenant());
        AccountPaymentDomain createdDomain = repo.create(newDomain);

        if (proformaDomain != null && proformaDomain.getId() != null) {
            recalculateProformaAmortization(proformaDomain.getId());
        }

        AccountPaymentDomain resolvedDomain = repo.findById(createdDomain.getId());
        return AccountPaymentResponse.from(resolvedDomain);
    }

    @Override
    @Transactional
    public AccountPaymentResponse edit(String id, AccountPaymentUpdateRequest request) {
        if (id == null) {
            return null;
        }
        AccountPaymentDomain old = repo.findById(id);
        if (old == null) {
            throw new NotFoundException("No entity found with id '%s'.".formatted(id));
        }

        ProformaDomain proformaDomain = null;
        if (request.getProforma() != null && !request.getProforma().isEmpty()){
            IdBaseDomain proformaIdBaseDomain = proformaRepo.findByIdEntity(request.getProforma());
            proformaDomain = new ProformaDomain();
            proformaDomain.setId(proformaIdBaseDomain.getId());
        }

        BankDomain bankDomain = null;
        if (request.getBank() != null && !request.getBank().isEmpty()){
            IdBaseDomain bankIdBaseDomain = bankRepo.findByIdEntity(request.getBank());
            bankDomain = new BankDomain();
            bankDomain.setId(bankIdBaseDomain.getId());
        }

        ChangeTypeDomain changeTypeDomain = null;
        if (request.getChangeType() != null && !request.getChangeType().isEmpty()){
            IdBaseDomain changeTypeIdBaseDomain = changeTypeRepo.findByIdEntity(request.getChangeType());
            changeTypeDomain = new ChangeTypeDomain();
            changeTypeDomain.setId(changeTypeIdBaseDomain.getId());
        }

        AccountPaymentDomain accountPaymentDomain = request.to(proformaDomain, bankDomain, changeTypeDomain);
        AccountPaymentDomain editedDomain = repo.edit(setFieldDefault.setDefaultFieldBaseUpdated(accountPaymentDomain, old));

        if (proformaDomain != null && proformaDomain.getId() != null) {
            recalculateProformaAmortization(proformaDomain.getId());
        }

        AccountPaymentDomain resolvedDomain = repo.findById(editedDomain.getId());
        return AccountPaymentResponse.from(resolvedDomain);
    }

    @Override
    @Transactional
    public void deleteById(String id) {
        AccountPaymentResponse old = findById(id);

        String proformaId = null;
        if (old.getProforma() != null) {
            proformaId = old.getProforma().getId();
        }

        repo.deleteById(old.getId());

        if (proformaId != null) {
            recalculateProformaAmortization(proformaId);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<AccountPaymentResponse> findAll() {
        return repo.findAll().stream().map(bankDomain -> AccountPaymentResponse.from(bankDomain)).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public AccountPaymentResponse findById(String id) {
        if (id == null) {
            return null;
        }
        AccountPaymentDomain old = repo.findById(id);
        if (old == null) {
            throw new NotFoundException("No entity found with id '%s'.".formatted(id));
        }
        return AccountPaymentResponse.from(old);
    }

    @Override
    @Transactional(readOnly = true)
    public AccountPaymentSearchResponse searchAll(AccountPaymentSearchRequest bankSearchRequest) {
       PageDTO<AccountPaymentDomain> pageDTO = repo.searchAll(bankSearchRequest);
       AccountPaymentSearchResponse bankSearchRespose = new AccountPaymentSearchResponse();
       bankSearchRespose.setItems(pageDTO.getContent().stream().map(bankDomain ->  AccountPaymentResponse.from(bankDomain)).toList());
       bankSearchRespose.setTotalElements(pageDTO.getTotalElements());
       bankSearchRespose.setTotalPages(pageDTO.getTotalPages());
       return bankSearchRespose;
    }

    @Override
    @Transactional(readOnly = true)
    public AccountPaymentSearchResponse searchFilterAll(AccountPaymentSearchFilterRequest bankSearchFilterRequest) {
        PageDTO<AccountPaymentDomain> pageDTO = repo.searchAllFilter(bankSearchFilterRequest);
        AccountPaymentSearchResponse bankSearchRespose = new AccountPaymentSearchResponse();
        bankSearchRespose.setItems(pageDTO.getContent().stream().map(bankDomain ->  AccountPaymentResponse.from(bankDomain)).toList());
        bankSearchRespose.setTotalElements(pageDTO.getTotalElements());
        bankSearchRespose.setTotalPages(pageDTO.getTotalPages());
        return bankSearchRespose;
    }

    @Override
    @Transactional(readOnly = true)
    public List<AccountPaymentResponse> findByProformaTypeAndIdentify(String proformaType, String identify) {
        Map<String, String> filter = new HashMap<>();
        if (StringUtils.isNotEmpty(proformaType)) {
            filter.put("proformaType", proformaType);
        }
        if (StringUtils.isNotEmpty(identify)) {
            filter.put("identify", identify);
        }

        ProformaSearchFilterRequest proformaSearchRequest = new ProformaSearchFilterRequest();
        proformaSearchRequest.setPage(0);
        proformaSearchRequest.setSize(10);
        proformaSearchRequest.setFilter(filter);
        proformaSearchRequest.setSort(new HashMap<>());

        PageDTO<ProformaDomain> proformaPage = proformaRepo.searchAllFilter(proformaSearchRequest);

        if (proformaPage.getContent() == null || proformaPage.getContent().isEmpty()) {
            return List.of();
        }

        String proformaId = proformaPage.getContent().get(0).getId();

        List<AccountPaymentDomain> payments = repo.findByProformaIdFullData(proformaId);

        return payments.stream()
                .map(AccountPaymentResponse::from)
                .toList();
    }

    private void recalculateProformaAmortization(String proformaId) {
        List<AccountPaymentDomain> payments = repo.findByProformaId(proformaId);

        double totalAmortizationUsd = 0.0;
        double totalAmortizationSoles = 0.0;
        for (AccountPaymentDomain payment : payments) {
            if (payment.getImportMontUsd() != null) {
                totalAmortizationUsd += payment.getImportMontUsd();
            }
            if (payment.getImportMontSoles() != null) {
                totalAmortizationSoles += payment.getImportMontSoles();
            }
        }

        ProformaDomain proforma = proformaRepo.findById(proformaId);
        double importTotalUsd = proforma.getImportTotalUsd() != null ? proforma.getImportTotalUsd() : 0.0;
        double importTotalSoles = proforma.getImportTotalSoles() != null ? proforma.getImportTotalSoles() : 0.0;

        double saldxAmortizarUsd = importTotalUsd - totalAmortizationUsd;
        double saldxAmortizarSoles = importTotalSoles - totalAmortizationSoles;

        proformaRepo.updateAmortizationById(proformaId,
                totalAmortizationUsd, totalAmortizationSoles,
                saldxAmortizarUsd, saldxAmortizarSoles);
    }
}
