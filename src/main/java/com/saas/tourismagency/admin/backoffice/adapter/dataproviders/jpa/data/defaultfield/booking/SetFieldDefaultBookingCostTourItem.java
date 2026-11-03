package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.defaultfield.booking;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.booking.CostTourItemEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.core.SetFieldDefaultEntityBase;
import com.saas.tourismagency.admin.backoffice.domain.booking.CostTourItemDomain;
import dev.mm.javaee.core.repo.SetFieldDefaultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SetFieldDefaultBookingCostTourItem implements SetFieldDefaultEntity<CostTourItemEntity, CostTourItemDomain> {

    @Autowired
    private SetFieldDefaultEntityBase setFieldDefaultEntityBase;

    @Override
    public CostTourItemEntity setDefaultFieldBaseCreate(CostTourItemEntity costTourItemEntity) throws RuntimeException {
        CostTourItemEntity entity = (CostTourItemEntity) setFieldDefaultEntityBase.setDefaultFieldBaseCreate(costTourItemEntity);
        return entity;
    }

    @Override
    public CostTourItemEntity setDefaultFieldBaseUpdate(CostTourItemEntity costTourItemEntity) throws RuntimeException {
        CostTourItemEntity entity = (CostTourItemEntity) setFieldDefaultEntityBase.setDefaultFieldBaseUpdate(costTourItemEntity);
        return entity;
    }

    @Override
    public CostTourItemEntity setDefaultFieldBaseDelete(CostTourItemEntity costTourItemEntity) throws RuntimeException {
        CostTourItemEntity entity = (CostTourItemEntity) setFieldDefaultEntityBase.setDefaultFieldBaseDelete(costTourItemEntity);
        return entity;
    }

    @Override
    public CostTourItemDomain setDefaultFieldBaseUpdated(CostTourItemDomain domainTarget, CostTourItemDomain domainSource) throws RuntimeException {
        CostTourItemDomain domain = (CostTourItemDomain) setFieldDefaultEntityBase.setDefaultFieldBaseUpdated(domainTarget, domainSource);
        return domain;
    }
}
