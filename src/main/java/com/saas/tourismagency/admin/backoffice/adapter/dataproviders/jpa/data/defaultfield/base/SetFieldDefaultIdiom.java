package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.defaultfield.base;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.base.IdiomEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.core.SetFieldDefaultEntityBase;
import com.saas.tourismagency.admin.backoffice.domain.base.IdiomDomain;
import dev.mm.javaee.core.repo.SetFieldDefaultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SetFieldDefaultIdiom implements SetFieldDefaultEntity<IdiomEntity, IdiomDomain> {

    @Autowired
    private SetFieldDefaultEntityBase setFieldDefaultEntityBase;

    @Override
    public IdiomEntity setDefaultFieldBaseCreate(IdiomEntity idiomEntity) throws RuntimeException {
        IdiomEntity entity = (IdiomEntity) setFieldDefaultEntityBase.setDefaultFieldBaseCreate(idiomEntity);
        return entity;
    }

    @Override
    public IdiomEntity setDefaultFieldBaseUpdate(IdiomEntity idiomEntity) throws RuntimeException {
        IdiomEntity entity = (IdiomEntity) setFieldDefaultEntityBase.setDefaultFieldBaseUpdate(idiomEntity);
        return entity;
    }

    @Override
    public IdiomEntity setDefaultFieldBaseDelete(IdiomEntity idiomEntity) throws RuntimeException {
        IdiomEntity entity = (IdiomEntity) setFieldDefaultEntityBase.setDefaultFieldBaseDelete(idiomEntity);
        return entity;
    }

    @Override
    public IdiomDomain setDefaultFieldBaseUpdated(IdiomDomain domainTarget, IdiomDomain domainSource) throws RuntimeException {
        IdiomDomain domain = (IdiomDomain) setFieldDefaultEntityBase.setDefaultFieldBaseUpdated(domainTarget, domainSource);
        return domain;
    }
}
