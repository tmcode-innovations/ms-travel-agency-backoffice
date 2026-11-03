package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.defaultfield.crm;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.crm.ItineraryDateEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.core.SetFieldDefaultEntityBase;
import com.saas.tourismagency.admin.backoffice.domain.crm.ItineraryDateDomain;
import dev.mm.javaee.core.repo.SetFieldDefaultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SetFieldDefaultItineraryDateCrm implements SetFieldDefaultEntity<ItineraryDateEntity, ItineraryDateDomain> {

    @Autowired
    private SetFieldDefaultEntityBase setFieldDefaultEntityBase;

    @Override
    public ItineraryDateEntity setDefaultFieldBaseCreate(ItineraryDateEntity itineraryDateEntity) throws RuntimeException {
        ItineraryDateEntity entity = (ItineraryDateEntity) setFieldDefaultEntityBase.setDefaultFieldBaseCreate(itineraryDateEntity);
        return entity;
    }

    @Override
    public ItineraryDateEntity setDefaultFieldBaseUpdate(ItineraryDateEntity itineraryDateEntity) throws RuntimeException {
        ItineraryDateEntity entity = (ItineraryDateEntity) setFieldDefaultEntityBase.setDefaultFieldBaseUpdate(itineraryDateEntity);
        return entity;
    }

    @Override
    public ItineraryDateEntity setDefaultFieldBaseDelete(ItineraryDateEntity itineraryDateEntity) throws RuntimeException {
        ItineraryDateEntity entity = (ItineraryDateEntity) setFieldDefaultEntityBase.setDefaultFieldBaseDelete(itineraryDateEntity);
        return entity;
    }

    @Override
    public ItineraryDateDomain setDefaultFieldBaseUpdated(ItineraryDateDomain domainTarget, ItineraryDateDomain domainSource) throws RuntimeException {
        ItineraryDateDomain domain = (ItineraryDateDomain) setFieldDefaultEntityBase.setDefaultFieldBaseUpdated(domainTarget, domainSource);
        return domain;
    }
}
