package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.defaultfield.maintenance;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.maintenance.LodgingCategoryEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.core.SetFieldDefaultEntityBase;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.LodgingCategoryDomain;
import dev.mm.javaee.core.repo.SetFieldDefaultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SetFieldDefaultLodgingCategory implements SetFieldDefaultEntity<LodgingCategoryEntity, LodgingCategoryDomain> {

    @Autowired
    private SetFieldDefaultEntityBase setFieldDefaultEntityBase;

    @Override
    public LodgingCategoryEntity setDefaultFieldBaseCreate(LodgingCategoryEntity lodgingCategoryEntity) throws RuntimeException {
        LodgingCategoryEntity entity = (LodgingCategoryEntity) setFieldDefaultEntityBase.setDefaultFieldBaseCreate(lodgingCategoryEntity);
        return entity;
    }

    @Override
    public LodgingCategoryEntity setDefaultFieldBaseUpdate(LodgingCategoryEntity lodgingCategoryEntity) throws RuntimeException {
        LodgingCategoryEntity entity = (LodgingCategoryEntity) setFieldDefaultEntityBase.setDefaultFieldBaseUpdate(lodgingCategoryEntity);
        return entity;
    }

    @Override
    public LodgingCategoryEntity setDefaultFieldBaseDelete(LodgingCategoryEntity lodgingCategoryEntity) throws RuntimeException {
        LodgingCategoryEntity entity = (LodgingCategoryEntity) setFieldDefaultEntityBase.setDefaultFieldBaseDelete(lodgingCategoryEntity);
        return entity;
    }

    @Override
    public LodgingCategoryDomain setDefaultFieldBaseUpdated(LodgingCategoryDomain domainTarget, LodgingCategoryDomain domainSource) throws RuntimeException {
        LodgingCategoryDomain domain = (LodgingCategoryDomain) setFieldDefaultEntityBase.setDefaultFieldBaseUpdated(domainTarget, domainSource);
        return domain;
    }
}
