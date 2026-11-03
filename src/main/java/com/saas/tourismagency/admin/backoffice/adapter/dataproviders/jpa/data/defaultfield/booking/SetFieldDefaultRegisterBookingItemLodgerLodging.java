package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.defaultfield.booking;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.booking.RegisterBookingItemLodgerLodgingEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.core.SetFieldDefaultEntityBase;
import com.saas.tourismagency.admin.backoffice.domain.booking.RegisterBookingItemLodgerLodgingDomain;
import dev.mm.javaee.core.repo.SetFieldDefaultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SetFieldDefaultRegisterBookingItemLodgerLodging implements SetFieldDefaultEntity<RegisterBookingItemLodgerLodgingEntity, RegisterBookingItemLodgerLodgingDomain> {

    @Autowired
    private SetFieldDefaultEntityBase setFieldDefaultEntityBase;

    @Override
    public RegisterBookingItemLodgerLodgingEntity setDefaultFieldBaseCreate(RegisterBookingItemLodgerLodgingEntity entityParam) throws RuntimeException {
        RegisterBookingItemLodgerLodgingEntity entity = (RegisterBookingItemLodgerLodgingEntity) setFieldDefaultEntityBase.setDefaultFieldBaseCreate(entityParam);
        return entity;
    }

    @Override
    public RegisterBookingItemLodgerLodgingEntity setDefaultFieldBaseUpdate(RegisterBookingItemLodgerLodgingEntity entityParam) throws RuntimeException {
        RegisterBookingItemLodgerLodgingEntity entity = (RegisterBookingItemLodgerLodgingEntity) setFieldDefaultEntityBase.setDefaultFieldBaseUpdate(entityParam);
        return entity;
    }

    @Override
    public RegisterBookingItemLodgerLodgingEntity setDefaultFieldBaseDelete(RegisterBookingItemLodgerLodgingEntity entityParam) throws RuntimeException {
        RegisterBookingItemLodgerLodgingEntity entity = (RegisterBookingItemLodgerLodgingEntity) setFieldDefaultEntityBase.setDefaultFieldBaseDelete(entityParam);
        return entity;
    }

    @Override
    public RegisterBookingItemLodgerLodgingDomain setDefaultFieldBaseUpdated(RegisterBookingItemLodgerLodgingDomain domainTarget, RegisterBookingItemLodgerLodgingDomain domainSource) throws RuntimeException {
        RegisterBookingItemLodgerLodgingDomain domain = (RegisterBookingItemLodgerLodgingDomain) setFieldDefaultEntityBase.setDefaultFieldBaseUpdated(domainTarget, domainSource);
        return domain;
    }
}
