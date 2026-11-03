package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.defaultfield.account;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.account.CostSheetTwoEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.core.SetFieldDefaultEntityBase;
import com.saas.tourismagency.admin.backoffice.domain.account.CostSheetTwoDomain;
import dev.mm.javaee.core.repo.SetFieldDefaultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SetFieldDefaultCostSheetTwo implements SetFieldDefaultEntity<CostSheetTwoEntity, CostSheetTwoDomain> {

    @Autowired
    private SetFieldDefaultEntityBase setFieldDefaultEntityBase;

    @Override
    public CostSheetTwoEntity setDefaultFieldBaseCreate(CostSheetTwoEntity entityParam) throws RuntimeException {
        CostSheetTwoEntity entity = (CostSheetTwoEntity) setFieldDefaultEntityBase.setDefaultFieldBaseCreate(entityParam);
        return entity;
    }

    @Override
    public CostSheetTwoEntity setDefaultFieldBaseUpdate(CostSheetTwoEntity entityParam) throws RuntimeException {
        CostSheetTwoEntity entity = (CostSheetTwoEntity) setFieldDefaultEntityBase.setDefaultFieldBaseUpdate(entityParam);
        return entity;
    }

    @Override
    public CostSheetTwoEntity setDefaultFieldBaseDelete(CostSheetTwoEntity entityParam) throws RuntimeException {
        CostSheetTwoEntity entity = (CostSheetTwoEntity) setFieldDefaultEntityBase.setDefaultFieldBaseDelete(entityParam);
        return entity;
    }

    @Override
    public CostSheetTwoDomain setDefaultFieldBaseUpdated(CostSheetTwoDomain domainTarget, CostSheetTwoDomain domainSource) throws RuntimeException {
        CostSheetTwoDomain domain = (CostSheetTwoDomain) setFieldDefaultEntityBase.setDefaultFieldBaseUpdated(domainTarget, domainSource);
        return domain;
    }
}
