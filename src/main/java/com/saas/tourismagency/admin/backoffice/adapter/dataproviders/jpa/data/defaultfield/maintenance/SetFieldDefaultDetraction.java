package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.defaultfield.maintenance;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.maintenance.DetractionEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.core.SetFieldDefaultEntityBase;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.DetractionDomain;
import dev.mm.javaee.core.repo.SetFieldDefaultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SetFieldDefaultDetraction implements SetFieldDefaultEntity<DetractionEntity, DetractionDomain> {

    @Autowired
    private SetFieldDefaultEntityBase setFieldDefaultEntityBase;

    @Override
    public DetractionEntity setDefaultFieldBaseCreate(DetractionEntity detractionEntity) throws RuntimeException {
        DetractionEntity entity = (DetractionEntity) setFieldDefaultEntityBase.setDefaultFieldBaseCreate(detractionEntity);
        return entity;
    }

    @Override
    public DetractionEntity setDefaultFieldBaseUpdate(DetractionEntity detractionEntity) throws RuntimeException {
        DetractionEntity entity = (DetractionEntity) setFieldDefaultEntityBase.setDefaultFieldBaseUpdate(detractionEntity);
        return entity;
    }

    @Override
    public DetractionEntity setDefaultFieldBaseDelete(DetractionEntity detractionEntity) throws RuntimeException {
        DetractionEntity entity = (DetractionEntity) setFieldDefaultEntityBase.setDefaultFieldBaseDelete(detractionEntity);
        return entity;
    }

    @Override
    public DetractionDomain setDefaultFieldBaseUpdated(DetractionDomain domainTarget, DetractionDomain domainSource) throws RuntimeException {
        DetractionDomain domain = (DetractionDomain) setFieldDefaultEntityBase.setDefaultFieldBaseUpdated(domainTarget, domainSource);
        return domain;
    }
}
