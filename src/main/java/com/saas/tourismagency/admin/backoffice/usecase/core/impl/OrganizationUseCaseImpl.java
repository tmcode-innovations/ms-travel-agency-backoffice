package com.saas.tourismagency.admin.backoffice.usecase.core.impl;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.core.organization.OrganizationConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.core.OrganizationRepo;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.core.organization.OrganizationResponse;
import com.saas.tourismagency.admin.backoffice.domain.core.OrganizationDomain;
import com.saas.tourismagency.admin.backoffice.usecase.core.OrganizationUseCase;
import dev.mm.javaee.core.exceptions.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
public class OrganizationUseCaseImpl implements OrganizationUseCase {

    @Autowired
    private OrganizationRepo repo;

    @Autowired
    private OrganizationConverter converter;

    @Override
    @Transactional(readOnly = true)
    public List<OrganizationResponse> findAll() {
        return repo.findAll(false).stream().map(organizationDomain -> OrganizationResponse.from(organizationDomain)).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public OrganizationResponse findById(String id) {
        if (id == null) {
            return null;
        }
        OrganizationDomain old = repo.findById(id);
        if (old == null) {
            throw new NotFoundException("No organization found with id '%s'.".formatted(id));
        }
        return OrganizationResponse.from(old);
    }

}
