package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.defaultfield.crm;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.crm.CostTourEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.core.SetFieldDefaultEntityBase;
import com.saas.tourismagency.admin.backoffice.domain.crm.CostTourDomain;
import dev.mm.javaee.core.repo.SetFieldDefaultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SetFieldDefaultCostTour implements SetFieldDefaultEntity<CostTourEntity, CostTourDomain> {

    @Autowired
    private SetFieldDefaultEntityBase setFieldDefaultEntityBase;

    @Override
    public CostTourEntity setDefaultFieldBaseCreate(CostTourEntity costTourEntity) throws RuntimeException {
        CostTourEntity entity = (CostTourEntity) setFieldDefaultEntityBase.setDefaultFieldBaseCreate(costTourEntity);
        return entity;
    }

    @Override
    public CostTourEntity setDefaultFieldBaseUpdate(CostTourEntity costTourEntity) throws RuntimeException {
        CostTourEntity entity = (CostTourEntity) setFieldDefaultEntityBase.setDefaultFieldBaseUpdate(costTourEntity);
        return entity;
    }

    @Override
    public CostTourEntity setDefaultFieldBaseDelete(CostTourEntity costTourEntity) throws RuntimeException {
        CostTourEntity entity = (CostTourEntity) setFieldDefaultEntityBase.setDefaultFieldBaseDelete(costTourEntity);
        return entity;
    }

    @Override
    public CostTourDomain setDefaultFieldBaseUpdated(CostTourDomain domainTarget, CostTourDomain domainSource) throws RuntimeException {
        CostTourDomain domain = (CostTourDomain) setFieldDefaultEntityBase.setDefaultFieldBaseUpdated(domainTarget, domainSource);
        return domain;
    }
}
