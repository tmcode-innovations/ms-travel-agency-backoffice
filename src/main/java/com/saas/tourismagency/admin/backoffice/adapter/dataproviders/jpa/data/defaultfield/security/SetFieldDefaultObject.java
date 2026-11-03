package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.defaultfield.security;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.security.ObjectEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.core.SetFieldDefaultEntityBase;
import com.saas.tourismagency.admin.backoffice.domain.security.ObjectDomain;
import dev.mm.javaee.core.repo.SetFieldDefaultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SetFieldDefaultObject implements SetFieldDefaultEntity<ObjectEntity, ObjectDomain> {

    @Autowired
    private SetFieldDefaultEntityBase setFieldDefaultEntityBase;

    @Override
    public ObjectEntity setDefaultFieldBaseCreate(ObjectEntity objectEntity) throws RuntimeException {
        ObjectEntity entity = (ObjectEntity) setFieldDefaultEntityBase.setDefaultFieldBaseCreate(objectEntity);
        return entity;
    }

    @Override
    public ObjectEntity setDefaultFieldBaseUpdate(ObjectEntity objectEntity) throws RuntimeException {
        ObjectEntity entity = (ObjectEntity) setFieldDefaultEntityBase.setDefaultFieldBaseUpdate(objectEntity);
        return entity;
    }

    @Override
    public ObjectEntity setDefaultFieldBaseDelete(ObjectEntity objectEntity) throws RuntimeException {
        ObjectEntity entity = (ObjectEntity) setFieldDefaultEntityBase.setDefaultFieldBaseDelete(objectEntity);
        return entity;
    }

    @Override
    public ObjectDomain setDefaultFieldBaseUpdated(ObjectDomain domainTarget, ObjectDomain domainSource) throws RuntimeException {
        ObjectDomain domain = (ObjectDomain) setFieldDefaultEntityBase.setDefaultFieldBaseUpdated(domainTarget, domainSource);
        return domain;
    }
}
