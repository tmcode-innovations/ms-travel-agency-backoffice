package com.saas.tourismagency.admin.backoffice.usecase.account.impl;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.account.proforma.ProformaConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.defaultfield.account.SetFieldDefaultProforma;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.account.proforma.ProformaRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.account.proformadetail.ProformaDetailRepo;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.proforma.*;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.proformadetail.ProformaDetailCreateRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.proformadetail.ProformaDetailResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.proformadetail.ProformaDetailUpdateRequest;
import com.saas.tourismagency.admin.backoffice.domain.account.ProformaDomain;
import com.saas.tourismagency.admin.backoffice.usecase.account.ProformaDetailUseCase;
import com.saas.tourismagency.admin.backoffice.usecase.account.ProformaUseCase;
import dev.mm.javaee.core.dto.PageDTO;
import dev.mm.javaee.core.exceptions.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ProformaUseCaseImpl implements ProformaUseCase {

    @Autowired
    private ProformaRepo repo;

    @Autowired
    private ProformaConverter converter;

    @Autowired
    private SetFieldDefaultProforma setFieldDefault;

    @Autowired
    private ProformaDetailUseCase proformaDetailUseCase;

    @Autowired
    private ProformaDetailRepo proformaDetailRepo;

    @Override
    @Transactional
    public ProformaResponse create(ProformaCreateRequest request) {
        ProformaDomain createdDomain = repo.create(request.to());
        return ProformaResponse.from(createdDomain);
    }

    @Override
    @Transactional
    public ProformaResponse edit(String id, ProformaUpdateRequest request) {
        if (id == null) {
            return null;
        }
        ProformaDomain old = repo.findById(id);
        if (old == null) {
            throw new NotFoundException("No entity found with id '%s'.".formatted(id));
        }
        ProformaDomain bankDomain = request.to();
        ProformaDomain editedDomain = repo.edit(setFieldDefault.setDefaultFieldBaseUpdated(bankDomain, old));
        return ProformaResponse.from(editedDomain);
    }

    @Override
    @Transactional
    public void deleteById(String id) {
        ProformaResponse old = findById(id);
        repo.deleteById(old.getId());
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProformaResponse> findAll() {
        return repo.findAll().stream().map(bankDomain -> ProformaResponse.from(bankDomain)).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public ProformaResponse findById(String id) {
        if (id == null) {
            return null;
        }
        ProformaDomain old = repo.findById(id);
        if (old == null) {
            throw new NotFoundException("No entity found with id '%s'.".formatted(id));
        }
        return ProformaResponse.from(old);
    }

    @Override
    @Transactional(readOnly = true)
    public ProformaSearchResponse searchAll(ProformaSearchRequest bankSearchRequest) {
       PageDTO<ProformaDomain> pageDTO = repo.searchAll(bankSearchRequest);
       ProformaSearchResponse bankSearchRespose = new ProformaSearchResponse();
       bankSearchRespose.setItems(pageDTO.getContent().stream().map(bankDomain ->  ProformaResponse.from(bankDomain)).toList());
       bankSearchRespose.setTotalElements(pageDTO.getTotalElements());
       bankSearchRespose.setTotalPages(pageDTO.getTotalPages());
       return bankSearchRespose;
    }

    @Override
    @Transactional(readOnly = true)
    public ProformaSearchResponse searchFilterAll(ProformaSearchFilterRequest bankSearchFilterRequest) {
        PageDTO<ProformaDomain> pageDTO = repo.searchAllFilter(bankSearchFilterRequest);
        ProformaSearchResponse bankSearchRespose = new ProformaSearchResponse();
        bankSearchRespose.setItems(pageDTO.getContent().stream().map(bankDomain ->  ProformaResponse.from(bankDomain)).toList());
        bankSearchRespose.setTotalElements(pageDTO.getTotalElements());
        bankSearchRespose.setTotalPages(pageDTO.getTotalPages());
        return bankSearchRespose;
    }

    @Override
    @Transactional
    public ProformaDetailResponse addProformaDetail(ProformaDetailCreateRequest request, String idProforma) {
        if (idProforma == null) {
            return null;
        }
        ProformaDomain proformaDomain = repo.findById(idProforma);
        if (proformaDomain == null) {
            throw new NotFoundException("No proforma found with id '%s'.".formatted(idProforma));
        }
        ProformaDetailResponse result = proformaDetailUseCase.create(request, proformaDomain);
        return result;
    }

    @Override
    @Transactional
    public ProformaDetailResponse editProformaDetail(String id, ProformaDetailUpdateRequest request, String idProforma) {
        if (idProforma == null) {
            return null;
        }
        ProformaDomain proformaDomain = repo.findById(idProforma);
        if (proformaDomain == null) {
            throw new NotFoundException("No proforma found with id '%s'.".formatted(idProforma));
        }
        ProformaDetailResponse result = proformaDetailUseCase.edit(id, request, proformaDomain);
        return result;
    }

    @Override
    @Transactional
    public void deleteProformaDetail(String id, String idProforma) {
        ProformaResponse old = findById(idProforma);
        proformaDetailUseCase.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProformaDetailResponse> findAllProformaDetailByProforma(String idProforma) {
        if (idProforma == null) {
            return null;
        }
        ProformaDomain proformaDomain = repo.findById(idProforma);
        if (proformaDomain == null) {
            throw new NotFoundException("No proforma found with id '%s'.".formatted(idProforma));
        }
        return proformaDetailRepo.searchAllByProforma(idProforma).stream().map(ProformaDetailResponse::from).collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public ProformaDetailResponse findByIdAndProforma(String id, String idProforma) {
        if (idProforma == null) {
            return null;
        }
        ProformaDomain proformaDomain = repo.findById(idProforma);
        if (proformaDomain == null) {
            throw new NotFoundException("No proforma found with id '%s'.".formatted(idProforma));
        }
        return proformaDetailUseCase.searchByProformaAndProformaDetail(idProforma, id);
    }
}
