package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.defaultfield.crm;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.crm.ItineraryItemEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.core.SetFieldDefaultEntityBase;
import com.saas.tourismagency.admin.backoffice.domain.crm.ItineraryItemDomain;
import dev.mm.javaee.core.repo.SetFieldDefaultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SetFieldDefaultItineraryItemCrm implements SetFieldDefaultEntity<ItineraryItemEntity, ItineraryItemDomain> {

    @Autowired
    private SetFieldDefaultEntityBase setFieldDefaultEntityBase;

    @Override
    public ItineraryItemEntity setDefaultFieldBaseCreate(ItineraryItemEntity itineraryItemEntity) throws RuntimeException {
        ItineraryItemEntity entity = (ItineraryItemEntity) setFieldDefaultEntityBase.setDefaultFieldBaseCreate(itineraryItemEntity);
        return entity;
    }

    @Override
    public ItineraryItemEntity setDefaultFieldBaseUpdate(ItineraryItemEntity itineraryItemEntity) throws RuntimeException {
        ItineraryItemEntity entity = (ItineraryItemEntity) setFieldDefaultEntityBase.setDefaultFieldBaseUpdate(itineraryItemEntity);
        return entity;
    }

    @Override
    public ItineraryItemEntity setDefaultFieldBaseDelete(ItineraryItemEntity itineraryItemEntity) throws RuntimeException {
        ItineraryItemEntity entity = (ItineraryItemEntity) setFieldDefaultEntityBase.setDefaultFieldBaseDelete(itineraryItemEntity);
        return entity;
    }

    @Override
    public ItineraryItemDomain setDefaultFieldBaseUpdated(ItineraryItemDomain domainTarget, ItineraryItemDomain domainSource) throws RuntimeException {
        ItineraryItemDomain domain = (ItineraryItemDomain) setFieldDefaultEntityBase.setDefaultFieldBaseUpdated(domainTarget, domainSource);
        return domain;
    }
}
