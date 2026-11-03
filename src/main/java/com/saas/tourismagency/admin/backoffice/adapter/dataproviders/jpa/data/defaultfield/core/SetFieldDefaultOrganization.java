package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.defaultfield.core;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.core.OrganizationEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.core.SetFieldDefaultEntityBase;
import com.saas.tourismagency.admin.backoffice.domain.core.OrganizationDomain;
import dev.mm.javaee.core.repo.SetFieldDefaultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SetFieldDefaultOrganization implements SetFieldDefaultEntity<OrganizationEntity, OrganizationDomain> {

    @Autowired
    private SetFieldDefaultEntityBase setFieldDefaultEntityBase;

    @Override
    public OrganizationEntity setDefaultFieldBaseCreate(OrganizationEntity organizationEntity) throws RuntimeException {
        OrganizationEntity entity = (OrganizationEntity) setFieldDefaultEntityBase.setDefaultFieldBaseCreate(organizationEntity);
        return entity;
    }

    @Override
    public OrganizationEntity setDefaultFieldBaseUpdate(OrganizationEntity organizationEntity) throws RuntimeException {
        OrganizationEntity entity = (OrganizationEntity) setFieldDefaultEntityBase.setDefaultFieldBaseUpdate(organizationEntity);
        return entity;
    }

    @Override
    public OrganizationEntity setDefaultFieldBaseDelete(OrganizationEntity organizationEntity) throws RuntimeException {
        OrganizationEntity entity = (OrganizationEntity) setFieldDefaultEntityBase.setDefaultFieldBaseDelete(organizationEntity);
        return entity;
    }

    @Override
    public OrganizationDomain setDefaultFieldBaseUpdated(OrganizationDomain domainTarget, OrganizationDomain domainSource) throws RuntimeException {
        OrganizationDomain domain = (OrganizationDomain) setFieldDefaultEntityBase.setDefaultFieldBaseUpdated(domainTarget, domainSource);
        return domain;
    }

}
