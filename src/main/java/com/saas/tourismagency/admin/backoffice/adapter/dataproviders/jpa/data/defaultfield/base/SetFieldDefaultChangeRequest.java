package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.defaultfield.base;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.base.ChangeRequestEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.core.SetFieldDefaultEntityBase;
import com.saas.tourismagency.admin.backoffice.domain.base.ChangeRequestDomain;
import dev.mm.javaee.core.repo.SetFieldDefaultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SetFieldDefaultChangeRequest implements SetFieldDefaultEntity<ChangeRequestEntity, ChangeRequestDomain> {

    @Autowired
    private SetFieldDefaultEntityBase setFieldDefaultEntityBase;

    @Override
    public ChangeRequestEntity setDefaultFieldBaseCreate(ChangeRequestEntity changeRequestEntity) throws RuntimeException {
        ChangeRequestEntity entity = (ChangeRequestEntity) setFieldDefaultEntityBase.setDefaultFieldBaseCreate(changeRequestEntity);
        return entity;
    }

    @Override
    public ChangeRequestEntity setDefaultFieldBaseUpdate(ChangeRequestEntity changeRequestEntity) throws RuntimeException {
        ChangeRequestEntity entity = (ChangeRequestEntity) setFieldDefaultEntityBase.setDefaultFieldBaseUpdate(changeRequestEntity);
        return entity;
    }

    @Override
    public ChangeRequestEntity setDefaultFieldBaseDelete(ChangeRequestEntity changeRequestEntity) throws RuntimeException {
        ChangeRequestEntity entity = (ChangeRequestEntity) setFieldDefaultEntityBase.setDefaultFieldBaseDelete(changeRequestEntity);
        return entity;
    }

    @Override
    public ChangeRequestDomain setDefaultFieldBaseUpdated(ChangeRequestDomain domainTarget, ChangeRequestDomain domainSource) throws RuntimeException {
        ChangeRequestDomain domain = (ChangeRequestDomain) setFieldDefaultEntityBase.setDefaultFieldBaseUpdated(domainTarget, domainSource);
        return domain;
    }
}
