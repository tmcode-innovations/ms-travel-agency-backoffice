package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.defaultfield.base;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.base.AreaEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.core.SetFieldDefaultEntityBase;
import com.saas.tourismagency.admin.backoffice.domain.base.AreaDomain;
import dev.mm.javaee.core.repo.SetFieldDefaultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SetFieldDefaultArea implements SetFieldDefaultEntity<AreaEntity, AreaDomain> {

    @Autowired
    private SetFieldDefaultEntityBase setFieldDefaultEntityBase;

    @Override
    public AreaEntity setDefaultFieldBaseCreate(AreaEntity areaEntity) throws RuntimeException {
        AreaEntity entity = (AreaEntity) setFieldDefaultEntityBase.setDefaultFieldBaseCreate(areaEntity);
        return entity;
    }

    @Override
    public AreaEntity setDefaultFieldBaseUpdate(AreaEntity areaEntity) throws RuntimeException {
        AreaEntity entity = (AreaEntity) setFieldDefaultEntityBase.setDefaultFieldBaseUpdate(areaEntity);
        return entity;
    }

    @Override
    public AreaEntity setDefaultFieldBaseDelete(AreaEntity areaEntity) throws RuntimeException {
        AreaEntity entity = (AreaEntity) setFieldDefaultEntityBase.setDefaultFieldBaseDelete(areaEntity);
        return entity;
    }

    @Override
    public AreaDomain setDefaultFieldBaseUpdated(AreaDomain domainTarget, AreaDomain domainSource) throws RuntimeException {
        AreaDomain domain = (AreaDomain) setFieldDefaultEntityBase.setDefaultFieldBaseUpdated(domainTarget, domainSource);
        return domain;
    }
}
