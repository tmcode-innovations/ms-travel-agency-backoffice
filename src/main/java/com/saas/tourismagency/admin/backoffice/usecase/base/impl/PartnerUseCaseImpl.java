package com.saas.tourismagency.admin.backoffice.usecase.base.impl;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.base.partner.PartnerConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.defaultfield.base.SetFieldDefaultPartner;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.base.partner.PartnerRepo;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.base.partner.*;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.base.partner.PartnerCreateRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.base.partner.PartnerResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.base.partner.PartnerUpdateRequest;
import com.saas.tourismagency.admin.backoffice.core.context.UserResolver;
import com.saas.tourismagency.admin.backoffice.domain.base.PartnerDomain;
import com.saas.tourismagency.admin.backoffice.usecase.base.PartnerUseCase;
import dev.mm.javaee.core.dto.PageDTO;
import dev.mm.javaee.core.exceptions.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
public class PartnerUseCaseImpl implements PartnerUseCase {

    @Autowired
    private PartnerRepo repo;

    @Autowired
    private PartnerConverter converter;

    @Autowired
    private SetFieldDefaultPartner setFieldDefault;

    @Override
    @Transactional
    public PartnerResponse create(PartnerCreateRequest request) {
        PartnerDomain newDomain = request.to();
        newDomain.setTenant(UserResolver.resolveUser().getTenant());
        PartnerDomain createdDomain = repo.create(newDomain);
        return PartnerResponse.from(createdDomain);
    }

    @Override
    @Transactional
    public PartnerResponse edit(String id, PartnerUpdateRequest request) {
        if (id == null) {
            return null;
        }
        PartnerDomain old = repo.findById(id);
        if (old == null) {
            throw new NotFoundException("No partner found with id '%s'.".formatted(id));
        }
        PartnerDomain partnerDomain = request.to();
        PartnerDomain editedDomain = repo.edit(setFieldDefault.setDefaultFieldBaseUpdated(partnerDomain, old));
        return PartnerResponse.from(editedDomain);
    }

    @Override
    @Transactional
    public void deleteById(String id) {
        PartnerResponse old = findById(id);
        repo.deleteById(old.getId());
    }

    @Override
    @Transactional(readOnly = true)
    public List<PartnerResponse> findAll() {
        return repo.findAll(false).stream().map(partnerDomain -> PartnerResponse.from(partnerDomain)).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public PartnerResponse findById(String id) {
        if (id == null) {
            return null;
        }
        PartnerDomain old = repo.findById(id);
        if (old == null) {
            throw new NotFoundException("No partner found with id '%s'.".formatted(id));
        }
        return PartnerResponse.from(old);
    }

    @Override
    @Transactional(readOnly = true)
    public PartnerSearchResponse searchAll(PartnerSearchRequest partnerSearchRequest) {
       PageDTO<PartnerDomain> pageDTO = repo.searchAll(partnerSearchRequest);
       PartnerSearchResponse partnerSearchRespose = new PartnerSearchResponse();
       partnerSearchRespose.setPartners(pageDTO.getContent().stream().map(partnerDomain ->  PartnerResponse.from(partnerDomain)).toList());
       partnerSearchRespose.setTotalElements(pageDTO.getTotalElements());
       partnerSearchRespose.setTotalPages(pageDTO.getTotalPages());
       return partnerSearchRespose;
    }

    @Override
    @Transactional(readOnly = true)
    public PartnerSearchResponse searchFilterAll(PartnerSearchFilterRequest partnerSearchFilterRequest) {
        PageDTO<PartnerDomain> pageDTO = repo.searchAllFilter(partnerSearchFilterRequest);
        PartnerSearchResponse partnerSearchRespose = new PartnerSearchResponse();
        partnerSearchRespose.setPartners(pageDTO.getContent().stream().map(partnerDomain ->  PartnerResponse.from(partnerDomain)).toList());
        partnerSearchRespose.setTotalElements(pageDTO.getTotalElements());
        partnerSearchRespose.setTotalPages(pageDTO.getTotalPages());
        return partnerSearchRespose;
    }
}
