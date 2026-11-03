package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.defaultfield.crm;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.crm.TourDateEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.core.SetFieldDefaultEntityBase;
import com.saas.tourismagency.admin.backoffice.domain.crm.TourDateDomain;
import dev.mm.javaee.core.repo.SetFieldDefaultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SetFieldDefaultTourDate implements SetFieldDefaultEntity<TourDateEntity, TourDateDomain> {

    @Autowired
    private SetFieldDefaultEntityBase setFieldDefaultEntityBase;

    @Override
    public TourDateEntity setDefaultFieldBaseCreate(TourDateEntity tourDateEntity) throws RuntimeException {
        TourDateEntity entity = (TourDateEntity) setFieldDefaultEntityBase.setDefaultFieldBaseCreate(tourDateEntity);
        return entity;
    }

    @Override
    public TourDateEntity setDefaultFieldBaseUpdate(TourDateEntity tourDateEntity) throws RuntimeException {
        TourDateEntity entity = (TourDateEntity) setFieldDefaultEntityBase.setDefaultFieldBaseUpdate(tourDateEntity);
        return entity;
    }

    @Override
    public TourDateEntity setDefaultFieldBaseDelete(TourDateEntity tourDateEntity) throws RuntimeException {
        TourDateEntity entity = (TourDateEntity) setFieldDefaultEntityBase.setDefaultFieldBaseDelete(tourDateEntity);
        return entity;
    }

    @Override
    public TourDateDomain setDefaultFieldBaseUpdated(TourDateDomain domainTarget, TourDateDomain domainSource) throws RuntimeException {
        TourDateDomain domain = (TourDateDomain) setFieldDefaultEntityBase.setDefaultFieldBaseUpdated(domainTarget, domainSource);
        return domain;
    }
}
