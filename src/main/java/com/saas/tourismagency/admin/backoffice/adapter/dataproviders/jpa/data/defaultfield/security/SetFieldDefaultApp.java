package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.defaultfield.security;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.security.AppEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.core.SetFieldDefaultEntityBase;
import com.saas.tourismagency.admin.backoffice.domain.security.AppDomain;
import dev.mm.javaee.core.repo.SetFieldDefaultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SetFieldDefaultApp implements SetFieldDefaultEntity<AppEntity, AppDomain> {

    @Autowired
    private SetFieldDefaultEntityBase setFieldDefaultEntityBase;

    @Override
    public AppEntity setDefaultFieldBaseCreate(AppEntity appEntity) throws RuntimeException {
        AppEntity entity = (AppEntity) setFieldDefaultEntityBase.setDefaultFieldBaseCreate(appEntity);
        return entity;
    }

    @Override
    public AppEntity setDefaultFieldBaseUpdate(AppEntity appEntity) throws RuntimeException {
        AppEntity entity = (AppEntity) setFieldDefaultEntityBase.setDefaultFieldBaseUpdate(appEntity);
        return entity;
    }

    @Override
    public AppEntity setDefaultFieldBaseDelete(AppEntity appEntity) throws RuntimeException {
        AppEntity entity = (AppEntity) setFieldDefaultEntityBase.setDefaultFieldBaseDelete(appEntity);
        return entity;
    }

    @Override
    public AppDomain setDefaultFieldBaseUpdated(AppDomain domainTarget, AppDomain domainSource) throws RuntimeException {
        AppDomain domain = (AppDomain) setFieldDefaultEntityBase.setDefaultFieldBaseUpdated(domainTarget, domainSource);
        return domain;
    }
}
