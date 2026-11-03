package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.defaultfield.account;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.account.DateProcessEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.core.SetFieldDefaultEntityBase;
import com.saas.tourismagency.admin.backoffice.domain.account.DateProcessDomain;
import dev.mm.javaee.core.repo.SetFieldDefaultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SetFieldDefaultDateProcess implements SetFieldDefaultEntity<DateProcessEntity, DateProcessDomain> {

    @Autowired
    private SetFieldDefaultEntityBase setFieldDefaultEntityBase;

    @Override
    public DateProcessEntity setDefaultFieldBaseCreate(DateProcessEntity entityParam) throws RuntimeException {
        DateProcessEntity entity = (DateProcessEntity) setFieldDefaultEntityBase.setDefaultFieldBaseCreate(entityParam);
        return entity;
    }

    @Override
    public DateProcessEntity setDefaultFieldBaseUpdate(DateProcessEntity entityParam) throws RuntimeException {
        DateProcessEntity entity = (DateProcessEntity) setFieldDefaultEntityBase.setDefaultFieldBaseUpdate(entityParam);
        return entity;
    }

    @Override
    public DateProcessEntity setDefaultFieldBaseDelete(DateProcessEntity entityParam) throws RuntimeException {
        DateProcessEntity entity = (DateProcessEntity) setFieldDefaultEntityBase.setDefaultFieldBaseDelete(entityParam);
        return entity;
    }

    @Override
    public DateProcessDomain setDefaultFieldBaseUpdated(DateProcessDomain domainTarget, DateProcessDomain domainSource) throws RuntimeException {
        DateProcessDomain domain = (DateProcessDomain) setFieldDefaultEntityBase.setDefaultFieldBaseUpdated(domainTarget, domainSource);
        return domain;
    }
}
