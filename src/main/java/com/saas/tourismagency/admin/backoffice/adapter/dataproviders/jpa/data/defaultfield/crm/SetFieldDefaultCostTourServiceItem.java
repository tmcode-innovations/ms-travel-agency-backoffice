package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.defaultfield.crm;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.crm.CostTourServiceItemEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.core.SetFieldDefaultEntityBase;
import com.saas.tourismagency.admin.backoffice.domain.crm.CostTourServiceItemDomain;
import dev.mm.javaee.core.repo.SetFieldDefaultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SetFieldDefaultCostTourServiceItem implements SetFieldDefaultEntity<CostTourServiceItemEntity, CostTourServiceItemDomain> {

    @Autowired
    private SetFieldDefaultEntityBase setFieldDefaultEntityBase;

    @Override
    public CostTourServiceItemEntity setDefaultFieldBaseCreate(CostTourServiceItemEntity costTourServiceItemEntity) throws RuntimeException {
        CostTourServiceItemEntity entity = (CostTourServiceItemEntity) setFieldDefaultEntityBase.setDefaultFieldBaseCreate(costTourServiceItemEntity);
        return entity;
    }

    @Override
    public CostTourServiceItemEntity setDefaultFieldBaseUpdate(CostTourServiceItemEntity costTourServiceItemEntity) throws RuntimeException {
        CostTourServiceItemEntity entity = (CostTourServiceItemEntity) setFieldDefaultEntityBase.setDefaultFieldBaseUpdate(costTourServiceItemEntity);
        return entity;
    }

    @Override
    public CostTourServiceItemEntity setDefaultFieldBaseDelete(CostTourServiceItemEntity costTourServiceItemEntity) throws RuntimeException {
        CostTourServiceItemEntity entity = (CostTourServiceItemEntity) setFieldDefaultEntityBase.setDefaultFieldBaseDelete(costTourServiceItemEntity);
        return entity;
    }

    @Override
    public CostTourServiceItemDomain setDefaultFieldBaseUpdated(CostTourServiceItemDomain domainTarget, CostTourServiceItemDomain domainSource) throws RuntimeException {
        CostTourServiceItemDomain domain = (CostTourServiceItemDomain) setFieldDefaultEntityBase.setDefaultFieldBaseUpdated(domainTarget, domainSource);
        return domain;
    }
}
