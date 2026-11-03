package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.defaultfield.core;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.core.CompanyEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.core.SetFieldDefaultEntityBase;
import com.saas.tourismagency.admin.backoffice.domain.core.CompanyDomain;
import dev.mm.javaee.core.repo.SetFieldDefaultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SetFieldDefaultCompany implements SetFieldDefaultEntity<CompanyEntity, CompanyDomain> {

    @Autowired
    private SetFieldDefaultEntityBase setFieldDefaultEntityBase;

    @Override
    public CompanyEntity setDefaultFieldBaseCreate(CompanyEntity companyEntity) throws RuntimeException {
        CompanyEntity entity = (CompanyEntity) setFieldDefaultEntityBase.setDefaultFieldBaseCreate(companyEntity);
        return entity;
    }

    @Override
    public CompanyEntity setDefaultFieldBaseUpdate(CompanyEntity companyEntity) throws RuntimeException {
        CompanyEntity entity = (CompanyEntity) setFieldDefaultEntityBase.setDefaultFieldBaseUpdate(companyEntity);
        return entity;
    }

    @Override
    public CompanyEntity setDefaultFieldBaseDelete(CompanyEntity companyEntity) throws RuntimeException {
        CompanyEntity entity = (CompanyEntity) setFieldDefaultEntityBase.setDefaultFieldBaseDelete(companyEntity);
        return entity;
    }

    @Override
    public CompanyDomain setDefaultFieldBaseUpdated(CompanyDomain domainTarget, CompanyDomain domainSource) throws RuntimeException {
        CompanyDomain domain = (CompanyDomain) setFieldDefaultEntityBase.setDefaultFieldBaseUpdated(domainTarget, domainSource);
        return domain;
    }

}
