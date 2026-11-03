package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.defaultfield.account;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.account.ChangeTypeEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.core.SetFieldDefaultEntityBase;
import com.saas.tourismagency.admin.backoffice.domain.account.ChangeTypeDomain;
import dev.mm.javaee.core.repo.SetFieldDefaultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SetFieldDefaultChangeType implements SetFieldDefaultEntity<ChangeTypeEntity, ChangeTypeDomain> {

    @Autowired
    private SetFieldDefaultEntityBase setFieldDefaultEntityBase;

    @Override
    public ChangeTypeEntity setDefaultFieldBaseCreate(ChangeTypeEntity entityParam) throws RuntimeException {
        ChangeTypeEntity entity = (ChangeTypeEntity) setFieldDefaultEntityBase.setDefaultFieldBaseCreate(entityParam);
        return entity;
    }

    @Override
    public ChangeTypeEntity setDefaultFieldBaseUpdate(ChangeTypeEntity entityParam) throws RuntimeException {
        ChangeTypeEntity entity = (ChangeTypeEntity) setFieldDefaultEntityBase.setDefaultFieldBaseUpdate(entityParam);
        return entity;
    }

    @Override
    public ChangeTypeEntity setDefaultFieldBaseDelete(ChangeTypeEntity entityParam) throws RuntimeException {
        ChangeTypeEntity entity = (ChangeTypeEntity) setFieldDefaultEntityBase.setDefaultFieldBaseDelete(entityParam);
        return entity;
    }

    @Override
    public ChangeTypeDomain setDefaultFieldBaseUpdated(ChangeTypeDomain domainTarget, ChangeTypeDomain domainSource) throws RuntimeException {
        ChangeTypeDomain domain = (ChangeTypeDomain) setFieldDefaultEntityBase.setDefaultFieldBaseUpdated(domainTarget, domainSource);
        return domain;
    }
}
