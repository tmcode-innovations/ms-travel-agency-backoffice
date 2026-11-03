package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.defaultfield.booking;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.booking.RegisterBookingItemLodgingEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.core.SetFieldDefaultEntityBase;
import com.saas.tourismagency.admin.backoffice.domain.booking.RegisterBookingItemLodgingDomain;
import dev.mm.javaee.core.repo.SetFieldDefaultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SetFieldDefaultRegisterBookingItemLodging implements SetFieldDefaultEntity<RegisterBookingItemLodgingEntity, RegisterBookingItemLodgingDomain> {

    @Autowired
    private SetFieldDefaultEntityBase setFieldDefaultEntityBase;

    @Override
    public RegisterBookingItemLodgingEntity setDefaultFieldBaseCreate(RegisterBookingItemLodgingEntity entityParam) throws RuntimeException {
        RegisterBookingItemLodgingEntity entity = (RegisterBookingItemLodgingEntity) setFieldDefaultEntityBase.setDefaultFieldBaseCreate(entityParam);
        return entity;
    }

    @Override
    public RegisterBookingItemLodgingEntity setDefaultFieldBaseUpdate(RegisterBookingItemLodgingEntity entityParam) throws RuntimeException {
        RegisterBookingItemLodgingEntity entity = (RegisterBookingItemLodgingEntity) setFieldDefaultEntityBase.setDefaultFieldBaseUpdate(entityParam);
        return entity;
    }

    @Override
    public RegisterBookingItemLodgingEntity setDefaultFieldBaseDelete(RegisterBookingItemLodgingEntity entityParam) throws RuntimeException {
        RegisterBookingItemLodgingEntity entity = (RegisterBookingItemLodgingEntity) setFieldDefaultEntityBase.setDefaultFieldBaseDelete(entityParam);
        return entity;
    }

    @Override
    public RegisterBookingItemLodgingDomain setDefaultFieldBaseUpdated(RegisterBookingItemLodgingDomain domainTarget, RegisterBookingItemLodgingDomain domainSource) throws RuntimeException {
        RegisterBookingItemLodgingDomain domain = (RegisterBookingItemLodgingDomain) setFieldDefaultEntityBase.setDefaultFieldBaseUpdated(domainTarget, domainSource);
        return domain;
    }
}
