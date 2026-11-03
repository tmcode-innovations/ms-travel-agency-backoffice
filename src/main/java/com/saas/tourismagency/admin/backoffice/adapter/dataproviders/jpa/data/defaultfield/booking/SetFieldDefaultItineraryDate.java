package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.defaultfield.booking;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.booking.ItineraryDateEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.core.SetFieldDefaultEntityBase;
import com.saas.tourismagency.admin.backoffice.domain.booking.ItineraryDateDomain;
import dev.mm.javaee.core.repo.SetFieldDefaultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SetFieldDefaultItineraryDate implements SetFieldDefaultEntity<ItineraryDateEntity, ItineraryDateDomain> {

    @Autowired
    private SetFieldDefaultEntityBase setFieldDefaultEntityBase;

    @Override
    public ItineraryDateEntity setDefaultFieldBaseCreate(ItineraryDateEntity entityParam) throws RuntimeException {
        ItineraryDateEntity entity = (ItineraryDateEntity) setFieldDefaultEntityBase.setDefaultFieldBaseCreate(entityParam);
        return entity;
    }

    @Override
    public ItineraryDateEntity setDefaultFieldBaseUpdate(ItineraryDateEntity entityParam) throws RuntimeException {
        ItineraryDateEntity entity = (ItineraryDateEntity) setFieldDefaultEntityBase.setDefaultFieldBaseUpdate(entityParam);
        return entity;
    }

    @Override
    public ItineraryDateEntity setDefaultFieldBaseDelete(ItineraryDateEntity entityParam) throws RuntimeException {
        ItineraryDateEntity entity = (ItineraryDateEntity) setFieldDefaultEntityBase.setDefaultFieldBaseDelete(entityParam);
        return entity;
    }

    @Override
    public ItineraryDateDomain setDefaultFieldBaseUpdated(ItineraryDateDomain domainTarget, ItineraryDateDomain domainSource) throws RuntimeException {
        ItineraryDateDomain domain = (ItineraryDateDomain) setFieldDefaultEntityBase.setDefaultFieldBaseUpdated(domainTarget, domainSource);
        return domain;
    }
}
