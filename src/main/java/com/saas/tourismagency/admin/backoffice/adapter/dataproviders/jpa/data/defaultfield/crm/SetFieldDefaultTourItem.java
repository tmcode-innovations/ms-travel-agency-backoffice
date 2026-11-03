package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.defaultfield.crm;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.crm.TourItemEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.core.SetFieldDefaultEntityBase;
import com.saas.tourismagency.admin.backoffice.domain.crm.TourItemDomain;
import dev.mm.javaee.core.repo.SetFieldDefaultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SetFieldDefaultTourItem implements SetFieldDefaultEntity<TourItemEntity, TourItemDomain> {

    @Autowired
    private SetFieldDefaultEntityBase setFieldDefaultEntityBase;

    @Override
    public TourItemEntity setDefaultFieldBaseCreate(TourItemEntity tourItemEntity) throws RuntimeException {
        TourItemEntity entity = (TourItemEntity) setFieldDefaultEntityBase.setDefaultFieldBaseCreate(tourItemEntity);
        return entity;
    }

    @Override
    public TourItemEntity setDefaultFieldBaseUpdate(TourItemEntity tourItemEntity) throws RuntimeException {
        TourItemEntity entity = (TourItemEntity) setFieldDefaultEntityBase.setDefaultFieldBaseUpdate(tourItemEntity);
        return entity;
    }

    @Override
    public TourItemEntity setDefaultFieldBaseDelete(TourItemEntity tourItemEntity) throws RuntimeException {
        TourItemEntity entity = (TourItemEntity) setFieldDefaultEntityBase.setDefaultFieldBaseDelete(tourItemEntity);
        return entity;
    }

    @Override
    public TourItemDomain setDefaultFieldBaseUpdated(TourItemDomain domainTarget, TourItemDomain domainSource) throws RuntimeException {
        TourItemDomain domain = (TourItemDomain) setFieldDefaultEntityBase.setDefaultFieldBaseUpdated(domainTarget, domainSource);
        return domain;
    }
}
