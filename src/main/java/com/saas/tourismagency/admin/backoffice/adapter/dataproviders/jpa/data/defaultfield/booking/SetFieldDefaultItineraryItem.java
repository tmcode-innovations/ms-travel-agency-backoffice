package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.defaultfield.booking;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.booking.ItineraryItemEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.core.SetFieldDefaultEntityBase;
import com.saas.tourismagency.admin.backoffice.domain.booking.ItineraryItemDomain;
import dev.mm.javaee.core.repo.SetFieldDefaultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SetFieldDefaultItineraryItem implements SetFieldDefaultEntity<ItineraryItemEntity, ItineraryItemDomain> {

    @Autowired
    private SetFieldDefaultEntityBase setFieldDefaultEntityBase;

    @Override
    public ItineraryItemEntity setDefaultFieldBaseCreate(ItineraryItemEntity entityParam) throws RuntimeException {
        ItineraryItemEntity entity = (ItineraryItemEntity) setFieldDefaultEntityBase.setDefaultFieldBaseCreate(entityParam);
        return entity;
    }

    @Override
    public ItineraryItemEntity setDefaultFieldBaseUpdate(ItineraryItemEntity entityParam) throws RuntimeException {
        ItineraryItemEntity entity = (ItineraryItemEntity) setFieldDefaultEntityBase.setDefaultFieldBaseUpdate(entityParam);
        return entity;
    }

    @Override
    public ItineraryItemEntity setDefaultFieldBaseDelete(ItineraryItemEntity entityParam) throws RuntimeException {
        ItineraryItemEntity entity = (ItineraryItemEntity) setFieldDefaultEntityBase.setDefaultFieldBaseDelete(entityParam);
        return entity;
    }

    @Override
    public ItineraryItemDomain setDefaultFieldBaseUpdated(ItineraryItemDomain domainTarget, ItineraryItemDomain domainSource) throws RuntimeException {
        ItineraryItemDomain domain = (ItineraryItemDomain) setFieldDefaultEntityBase.setDefaultFieldBaseUpdated(domainTarget, domainSource);
        return domain;
    }
}
