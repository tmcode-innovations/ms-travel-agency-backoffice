package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.defaultfield.booking;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.booking.RegisterBookingItemLodgingFeedingEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.core.SetFieldDefaultEntityBase;
import com.saas.tourismagency.admin.backoffice.domain.booking.RegisterBookingItemLodgingFeedingDomain;
import dev.mm.javaee.core.repo.SetFieldDefaultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SetFieldDefaultRegisterBookingItemLodgingFeeding implements SetFieldDefaultEntity<RegisterBookingItemLodgingFeedingEntity, RegisterBookingItemLodgingFeedingDomain> {

    @Autowired
    private SetFieldDefaultEntityBase setFieldDefaultEntityBase;

    @Override
    public RegisterBookingItemLodgingFeedingEntity setDefaultFieldBaseCreate(RegisterBookingItemLodgingFeedingEntity entityParam) throws RuntimeException {
        RegisterBookingItemLodgingFeedingEntity entity = (RegisterBookingItemLodgingFeedingEntity) setFieldDefaultEntityBase.setDefaultFieldBaseCreate(entityParam);
        return entity;
    }

    @Override
    public RegisterBookingItemLodgingFeedingEntity setDefaultFieldBaseUpdate(RegisterBookingItemLodgingFeedingEntity entityParam) throws RuntimeException {
        RegisterBookingItemLodgingFeedingEntity entity = (RegisterBookingItemLodgingFeedingEntity) setFieldDefaultEntityBase.setDefaultFieldBaseUpdate(entityParam);
        return entity;
    }

    @Override
    public RegisterBookingItemLodgingFeedingEntity setDefaultFieldBaseDelete(RegisterBookingItemLodgingFeedingEntity entityParam) throws RuntimeException {
        RegisterBookingItemLodgingFeedingEntity entity = (RegisterBookingItemLodgingFeedingEntity) setFieldDefaultEntityBase.setDefaultFieldBaseDelete(entityParam);
        return entity;
    }

    @Override
    public RegisterBookingItemLodgingFeedingDomain setDefaultFieldBaseUpdated(RegisterBookingItemLodgingFeedingDomain domainTarget, RegisterBookingItemLodgingFeedingDomain domainSource) throws RuntimeException {
        RegisterBookingItemLodgingFeedingDomain domain = (RegisterBookingItemLodgingFeedingDomain) setFieldDefaultEntityBase.setDefaultFieldBaseUpdated(domainTarget, domainSource);
        return domain;
    }
}
