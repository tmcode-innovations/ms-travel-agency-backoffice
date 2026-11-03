package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.defaultfield.hr;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.hr.PositionEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.core.SetFieldDefaultEntityBase;
import com.saas.tourismagency.admin.backoffice.domain.hr.PositionDomain;
import dev.mm.javaee.core.repo.SetFieldDefaultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SetFieldDefaultPosition implements SetFieldDefaultEntity<PositionEntity, PositionDomain> {

    @Autowired
    private SetFieldDefaultEntityBase setFieldDefaultEntityBase;

    @Override
    public PositionEntity setDefaultFieldBaseCreate(PositionEntity countryEntity) throws RuntimeException {
        PositionEntity entity = (PositionEntity) setFieldDefaultEntityBase.setDefaultFieldBaseCreate(countryEntity);
        return entity;
    }

    @Override
    public PositionEntity setDefaultFieldBaseUpdate(PositionEntity countryEntity) throws RuntimeException {
        PositionEntity entity = (PositionEntity) setFieldDefaultEntityBase.setDefaultFieldBaseUpdate(countryEntity);
        return entity;
    }

    @Override
    public PositionEntity setDefaultFieldBaseDelete(PositionEntity countryEntity) throws RuntimeException {
        PositionEntity entity = (PositionEntity) setFieldDefaultEntityBase.setDefaultFieldBaseDelete(countryEntity);
        return entity;
    }

    @Override
    public PositionDomain setDefaultFieldBaseUpdated(PositionDomain domainTarget, PositionDomain domainSource) throws RuntimeException {
        PositionDomain domain = (PositionDomain) setFieldDefaultEntityBase.setDefaultFieldBaseUpdated(domainTarget, domainSource);
        return domain;
    }
}
