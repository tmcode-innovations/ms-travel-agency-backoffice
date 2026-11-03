package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.defaultfield.crm;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.crm.TourEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.core.SetFieldDefaultEntityBase;
import com.saas.tourismagency.admin.backoffice.domain.crm.TourDomain;
import dev.mm.javaee.core.repo.SetFieldDefaultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SetFieldDefaultTour implements SetFieldDefaultEntity<TourEntity, TourDomain> {

    @Autowired
    private SetFieldDefaultEntityBase setFieldDefaultEntityBase;

    @Override
    public TourEntity setDefaultFieldBaseCreate(TourEntity tourEntity) throws RuntimeException {
        TourEntity entity = (TourEntity) setFieldDefaultEntityBase.setDefaultFieldBaseCreate(tourEntity);
        return entity;
    }

    @Override
    public TourEntity setDefaultFieldBaseUpdate(TourEntity tourEntity) throws RuntimeException {
        TourEntity entity = (TourEntity) setFieldDefaultEntityBase.setDefaultFieldBaseUpdate(tourEntity);
        return entity;
    }

    @Override
    public TourEntity setDefaultFieldBaseDelete(TourEntity tourEntity) throws RuntimeException {
        TourEntity entity = (TourEntity) setFieldDefaultEntityBase.setDefaultFieldBaseDelete(tourEntity);
        return entity;
    }

    @Override
    public TourDomain setDefaultFieldBaseUpdated(TourDomain domainTarget, TourDomain domainSource) throws RuntimeException {
        TourDomain domain = (TourDomain) setFieldDefaultEntityBase.setDefaultFieldBaseUpdated(domainTarget, domainSource);
        return domain;
    }
}
