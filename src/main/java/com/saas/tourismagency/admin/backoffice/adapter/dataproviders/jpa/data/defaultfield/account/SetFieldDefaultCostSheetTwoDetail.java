package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.defaultfield.account;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.account.CostSheetTwoDetailEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.core.SetFieldDefaultEntityBase;
import com.saas.tourismagency.admin.backoffice.domain.account.CostSheetTwoDetailDomain;
import dev.mm.javaee.core.repo.SetFieldDefaultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SetFieldDefaultCostSheetTwoDetail implements SetFieldDefaultEntity<CostSheetTwoDetailEntity, CostSheetTwoDetailDomain> {

    @Autowired
    private SetFieldDefaultEntityBase setFieldDefaultEntityBase;

    @Override
    public CostSheetTwoDetailEntity setDefaultFieldBaseCreate(CostSheetTwoDetailEntity entityParam) throws RuntimeException {
        CostSheetTwoDetailEntity entity = (CostSheetTwoDetailEntity) setFieldDefaultEntityBase.setDefaultFieldBaseCreate(entityParam);
        return entity;
    }

    @Override
    public CostSheetTwoDetailEntity setDefaultFieldBaseUpdate(CostSheetTwoDetailEntity entityParam) throws RuntimeException {
        CostSheetTwoDetailEntity entity = (CostSheetTwoDetailEntity) setFieldDefaultEntityBase.setDefaultFieldBaseUpdate(entityParam);
        return entity;
    }

    @Override
    public CostSheetTwoDetailEntity setDefaultFieldBaseDelete(CostSheetTwoDetailEntity entityParam) throws RuntimeException {
        CostSheetTwoDetailEntity entity = (CostSheetTwoDetailEntity) setFieldDefaultEntityBase.setDefaultFieldBaseDelete(entityParam);
        return entity;
    }

    @Override
    public CostSheetTwoDetailDomain setDefaultFieldBaseUpdated(CostSheetTwoDetailDomain domainTarget, CostSheetTwoDetailDomain domainSource) throws RuntimeException {
        CostSheetTwoDetailDomain domain = (CostSheetTwoDetailDomain) setFieldDefaultEntityBase.setDefaultFieldBaseUpdated(domainTarget, domainSource);
        return domain;
    }
}
