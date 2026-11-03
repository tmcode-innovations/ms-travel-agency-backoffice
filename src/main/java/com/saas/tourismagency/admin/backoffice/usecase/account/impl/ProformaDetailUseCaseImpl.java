package com.saas.tourismagency.admin.backoffice.usecase.account.impl;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.account.proformadetail.ProformaDetailConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.defaultfield.account.SetFieldDefaultProformaDetail;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.account.changetype.ChangeTypeRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.account.proforma.ProformaRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.account.proformadetail.ProformaDetailRepo;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.proformadetail.*;
import com.saas.tourismagency.admin.backoffice.core.context.UserResolver;
import com.saas.tourismagency.admin.backoffice.domain.account.*;
import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.usecase.account.ProformaDetailUseCase;
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
public class ProformaDetailUseCaseImpl implements ProformaDetailUseCase {

    @Autowired
    private ProformaDetailRepo repo;

    @Autowired
    private ProformaDetailConverter converter;

    @Autowired
    private SetFieldDefaultProformaDetail setFieldDefault;

    @Autowired
    private ChangeTypeRepo changeTypeRepo;

    @Autowired
    private ProformaRepo proformaRepo;

    @Override
    @Transactional
    public ProformaDetailResponse create(ProformaDetailCreateRequest request) {
        ChangeTypeDomain changeTypeDomain = null;
        if (request.getChangeType() != null && !request.getChangeType().isEmpty()){
            IdBaseDomain changeTypeIdBaseDomain = changeTypeRepo.findByIdEntity(request.getChangeType());
            changeTypeDomain = new ChangeTypeDomain();
            changeTypeDomain.setId(changeTypeIdBaseDomain.getId());
        }

        ProformaDomain proformaDomain = null;
        if (request.getProforma() != null && !request.getProforma().isEmpty()){
            IdBaseDomain proformaIdBaseDomain = proformaRepo.findByIdEntity(request.getProforma());
            proformaDomain = new ProformaDomain();
            proformaDomain.setId(proformaIdBaseDomain.getId());
        }

        ProformaDetailDomain newDomain = request.to(changeTypeDomain, proformaDomain);
        newDomain.setTenant(UserResolver.resolveUser().getTenant());

        if (proformaDomain != null && proformaDomain.getId() != null){
            newDomain.setNoItem(this.getLastIndexProformaDetail(proformaDomain.getId()));
        }

        ProformaDetailDomain createdDomain = repo.create(newDomain);
        return ProformaDetailResponse.from(createdDomain);
    }

    @Override
    public ProformaDetailResponse create(ProformaDetailCreateRequest request, ProformaDomain proformaDomain) {
        ChangeTypeDomain changeTypeDomain = null;
        if (request.getChangeType() != null && !request.getChangeType().isEmpty()){
            IdBaseDomain changeTypeIdBaseDomain = changeTypeRepo.findByIdEntity(request.getChangeType());
            changeTypeDomain = new ChangeTypeDomain();
            changeTypeDomain.setId(changeTypeIdBaseDomain.getId());
        }

        ProformaDetailDomain newDomain = request.to(changeTypeDomain, proformaDomain);
        newDomain.setTenant(UserResolver.resolveUser().getTenant());

        if (proformaDomain != null && proformaDomain.getId() != null){
            newDomain.setNoItem(this.getLastIndexProformaDetail(proformaDomain.getId()));
        }

        ProformaDetailDomain createdDomain = repo.create(newDomain);
        return ProformaDetailResponse.from(createdDomain);
    }

    @Override
    @Transactional
    public ProformaDetailResponse edit(String id, ProformaDetailUpdateRequest request) {
        if (id == null) {
            return null;
        }
        ProformaDetailDomain old = repo.findById(id);
        if (old == null) {
            throw new NotFoundException("No entity found with id '%s'.".formatted(id));
        }

        ChangeTypeDomain changeTypeDomain = null;
        if (request.getChangeType() != null && !request.getChangeType().isEmpty()){
            IdBaseDomain changeTypeIdBaseDomain = changeTypeRepo.findByIdEntity(request.getChangeType());
            changeTypeDomain = new ChangeTypeDomain();
            changeTypeDomain.setId(changeTypeIdBaseDomain.getId());
        }

        ProformaDomain proformaDomain = null;
        if (request.getProforma() != null && !request.getProforma().isEmpty()){
            IdBaseDomain proformaIdBaseDomain = proformaRepo.findByIdEntity(request.getProforma());
            proformaDomain = new ProformaDomain();
            proformaDomain.setId(proformaIdBaseDomain.getId());
        }

        ProformaDetailDomain proformaDetailDomain = request.to(changeTypeDomain, proformaDomain);

        proformaDetailDomain.setNoItem(old.getNoItem());

        ProformaDetailDomain editedDomain = repo.edit(setFieldDefault.setDefaultFieldBaseUpdated(proformaDetailDomain, old));
        return ProformaDetailResponse.from(editedDomain);
    }

    @Override
    public ProformaDetailResponse edit(String id, ProformaDetailUpdateRequest request, ProformaDomain proformaDomain) {
        if (id == null) {
            return null;
        }
        ProformaDetailDomain old = repo.findById(id);
        if (old == null) {
            throw new NotFoundException("No entity found with id '%s'.".formatted(id));
        }

        ChangeTypeDomain changeTypeDomain = null;
        if (request.getChangeType() != null && !request.getChangeType().isEmpty()){
            IdBaseDomain changeTypeIdBaseDomain = changeTypeRepo.findByIdEntity(request.getChangeType());
            changeTypeDomain = new ChangeTypeDomain();
            changeTypeDomain.setId(changeTypeIdBaseDomain.getId());
        }

        ProformaDetailDomain proformaDetailDomain = request.to(changeTypeDomain, proformaDomain);

        proformaDetailDomain.setNoItem(old.getNoItem());

        ProformaDetailDomain editedDomain = repo.edit(setFieldDefault.setDefaultFieldBaseUpdated(proformaDetailDomain, old));
        return ProformaDetailResponse.from(editedDomain);
    }

    @Override
    @Transactional
    public void deleteById(String id) {
        ProformaDetailResponse old = findById(id);
        repo.deleteById(old.getId());
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProformaDetailResponse> findAll() {
        return repo.findAll().stream().map(bankDomain -> ProformaDetailResponse.from(bankDomain)).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public ProformaDetailResponse findById(String id) {
        if (id == null) {
            return null;
        }
        ProformaDetailDomain old = repo.findById(id);
        if (old == null) {
            throw new NotFoundException("No entity found with id '%s'.".formatted(id));
        }
        return ProformaDetailResponse.from(old);
    }

    @Override
    @Transactional(readOnly = true)
    public ProformaDetailSearchResponse searchAll(ProformaDetailSearchRequest bankSearchRequest) {
       PageDTO<ProformaDetailDomain> pageDTO = repo.searchAll(bankSearchRequest);
       ProformaDetailSearchResponse bankSearchRespose = new ProformaDetailSearchResponse();
       bankSearchRespose.setItems(pageDTO.getContent().stream().map(bankDomain ->  ProformaDetailResponse.from(bankDomain)).toList());
       bankSearchRespose.setTotalElements(pageDTO.getTotalElements());
       bankSearchRespose.setTotalPages(pageDTO.getTotalPages());
       return bankSearchRespose;
    }

    @Override
    @Transactional(readOnly = true)
    public ProformaDetailSearchResponse searchFilterAll(ProformaDetailSearchFilterRequest bankSearchFilterRequest) {
        PageDTO<ProformaDetailDomain> pageDTO = repo.searchAllFilter(bankSearchFilterRequest);
        ProformaDetailSearchResponse bankSearchRespose = new ProformaDetailSearchResponse();
        bankSearchRespose.setItems(pageDTO.getContent().stream().map(bankDomain ->  ProformaDetailResponse.from(bankDomain)).toList());
        bankSearchRespose.setTotalElements(pageDTO.getTotalElements());
        bankSearchRespose.setTotalPages(pageDTO.getTotalPages());
        return bankSearchRespose;
    }

    @Override
    public ProformaDetailResponse searchByProformaAndProformaDetail(String idProforma, String idProformaDetail) {
        ProformaDetailDomain proformaDetailDomain = repo.searchAllByProformaAndProformaDetail(idProforma, idProformaDetail);
        return ProformaDetailResponse.from(proformaDetailDomain);
    }

    @Override
    @Transactional
    public void recalculateProformaTotals(String idProforma) {
        if (idProforma == null) {
            return;
        }
        List<ProformaDetailDomain> details = repo.searchAllByProforma(idProforma);
        double totalSoles = 0.0;
        double totalUsd = 0.0;
        for (ProformaDetailDomain detail : details) {
            if (detail.getImportSoles() != null) {
                totalSoles += detail.getImportSoles();
            }
            if (detail.getImportUsd() != null) {
                totalUsd += detail.getImportUsd();
            }
        }
        ProformaDomain proformaDomain = proformaRepo.findById(idProforma);
        if (proformaDomain != null) {
            proformaDomain.setImportTotalSoles(totalSoles);
            proformaDomain.setImportTotalUsd(totalUsd);
            proformaRepo.edit(proformaDomain);
        }
    }

    private Integer getLastIndexProformaDetail(String idProforma) {
        List<ProformaDetailDomain> list = repo.searchAllByProformaSimpleDomain(idProforma);
        Integer max = list.stream()
                .map(ProformaDetailDomain::getNoItem)
                .filter(Objects::nonNull)
                .max(Integer::compareTo)
                .orElse(0);
        return max + 1;
    }
}
