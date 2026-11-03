package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.defaultfield.maintenance;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.maintenance.CostSheetTwoPercentEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.core.SetFieldDefaultEntityBase;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.CostSheetTwoPercentDomain;
import dev.mm.javaee.core.repo.SetFieldDefaultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SetFieldDefaultCostSheetTwoPercent implements SetFieldDefaultEntity<CostSheetTwoPercentEntity, CostSheetTwoPercentDomain> {

    @Autowired
    private SetFieldDefaultEntityBase setFieldDefaultEntityBase;

    @Override
    public CostSheetTwoPercentEntity setDefaultFieldBaseCreate(CostSheetTwoPercentEntity costSheetTwoPercentEntity) throws RuntimeException {
        CostSheetTwoPercentEntity entity = (CostSheetTwoPercentEntity) setFieldDefaultEntityBase.setDefaultFieldBaseCreate(costSheetTwoPercentEntity);
        return entity;
    }

    @Override
    public CostSheetTwoPercentEntity setDefaultFieldBaseUpdate(CostSheetTwoPercentEntity costSheetTwoPercentEntity) throws RuntimeException {
        CostSheetTwoPercentEntity entity = (CostSheetTwoPercentEntity) setFieldDefaultEntityBase.setDefaultFieldBaseUpdate(costSheetTwoPercentEntity);
        return entity;
    }

    @Override
    public CostSheetTwoPercentEntity setDefaultFieldBaseDelete(CostSheetTwoPercentEntity costSheetTwoPercentEntity) throws RuntimeException {
        CostSheetTwoPercentEntity entity = (CostSheetTwoPercentEntity) setFieldDefaultEntityBase.setDefaultFieldBaseDelete(costSheetTwoPercentEntity);
        return entity;
    }

    @Override
    public CostSheetTwoPercentDomain setDefaultFieldBaseUpdated(CostSheetTwoPercentDomain domainTarget, CostSheetTwoPercentDomain domainSource) throws RuntimeException {
        CostSheetTwoPercentDomain domain = (CostSheetTwoPercentDomain) setFieldDefaultEntityBase.setDefaultFieldBaseUpdated(domainTarget, domainSource);
        return domain;
    }
}
