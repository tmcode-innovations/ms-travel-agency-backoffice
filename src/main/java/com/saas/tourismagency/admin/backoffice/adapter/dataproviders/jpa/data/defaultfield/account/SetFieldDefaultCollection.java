package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.defaultfield.account;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.account.CollectionEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.core.SetFieldDefaultEntityBase;
import com.saas.tourismagency.admin.backoffice.domain.account.CollectionDomain;
import dev.mm.javaee.core.repo.SetFieldDefaultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SetFieldDefaultCollection implements SetFieldDefaultEntity<CollectionEntity, CollectionDomain> {

    @Autowired
    private SetFieldDefaultEntityBase setFieldDefaultEntityBase;

    @Override
    public CollectionEntity setDefaultFieldBaseCreate(CollectionEntity entityParam) throws RuntimeException {
        CollectionEntity entity = (CollectionEntity) setFieldDefaultEntityBase.setDefaultFieldBaseCreate(entityParam);
        return entity;
    }

    @Override
    public CollectionEntity setDefaultFieldBaseUpdate(CollectionEntity entityParam) throws RuntimeException {
        CollectionEntity entity = (CollectionEntity) setFieldDefaultEntityBase.setDefaultFieldBaseUpdate(entityParam);
        return entity;
    }

    @Override
    public CollectionEntity setDefaultFieldBaseDelete(CollectionEntity entityParam) throws RuntimeException {
        CollectionEntity entity = (CollectionEntity) setFieldDefaultEntityBase.setDefaultFieldBaseDelete(entityParam);
        return entity;
    }

    @Override
    public CollectionDomain setDefaultFieldBaseUpdated(CollectionDomain domainTarget, CollectionDomain domainSource) throws RuntimeException {
        CollectionDomain domain = (CollectionDomain) setFieldDefaultEntityBase.setDefaultFieldBaseUpdated(domainTarget, domainSource);
        return domain;
    }
}
