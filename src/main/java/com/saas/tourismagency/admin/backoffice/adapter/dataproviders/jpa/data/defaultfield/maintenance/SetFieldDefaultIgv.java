package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.defaultfield.maintenance;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.maintenance.IgvEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.core.SetFieldDefaultEntityBase;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.IgvDomain;
import dev.mm.javaee.core.repo.SetFieldDefaultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SetFieldDefaultIgv implements SetFieldDefaultEntity<IgvEntity, IgvDomain> {

    @Autowired
    private SetFieldDefaultEntityBase setFieldDefaultEntityBase;

    @Override
    public IgvEntity setDefaultFieldBaseCreate(IgvEntity igvEntity) throws RuntimeException {
        IgvEntity entity = (IgvEntity) setFieldDefaultEntityBase.setDefaultFieldBaseCreate(igvEntity);
        return entity;
    }

    @Override
    public IgvEntity setDefaultFieldBaseUpdate(IgvEntity igvEntity) throws RuntimeException {
        IgvEntity entity = (IgvEntity) setFieldDefaultEntityBase.setDefaultFieldBaseUpdate(igvEntity);
        return entity;
    }

    @Override
    public IgvEntity setDefaultFieldBaseDelete(IgvEntity igvEntity) throws RuntimeException {
        IgvEntity entity = (IgvEntity) setFieldDefaultEntityBase.setDefaultFieldBaseDelete(igvEntity);
        return entity;
    }

    @Override
    public IgvDomain setDefaultFieldBaseUpdated(IgvDomain domainTarget, IgvDomain domainSource) throws RuntimeException {
        IgvDomain domain = (IgvDomain) setFieldDefaultEntityBase.setDefaultFieldBaseUpdated(domainTarget, domainSource);
        return domain;
    }
}
