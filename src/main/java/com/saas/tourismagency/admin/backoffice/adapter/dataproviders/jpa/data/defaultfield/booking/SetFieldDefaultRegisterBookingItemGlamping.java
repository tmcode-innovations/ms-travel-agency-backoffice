package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.defaultfield.booking;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.booking.RegisterBookingItemGlampingEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.core.SetFieldDefaultEntityBase;
import com.saas.tourismagency.admin.backoffice.domain.booking.RegisterBookingItemGlampingDomain;
import dev.mm.javaee.core.repo.SetFieldDefaultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SetFieldDefaultRegisterBookingItemGlamping implements SetFieldDefaultEntity<RegisterBookingItemGlampingEntity, RegisterBookingItemGlampingDomain> {

    @Autowired
    private SetFieldDefaultEntityBase setFieldDefaultEntityBase;

    @Override
    public RegisterBookingItemGlampingEntity setDefaultFieldBaseCreate(RegisterBookingItemGlampingEntity entityParam) throws RuntimeException {
        RegisterBookingItemGlampingEntity entity = (RegisterBookingItemGlampingEntity) setFieldDefaultEntityBase.setDefaultFieldBaseCreate(entityParam);
        return entity;
    }

    @Override
    public RegisterBookingItemGlampingEntity setDefaultFieldBaseUpdate(RegisterBookingItemGlampingEntity entityParam) throws RuntimeException {
        RegisterBookingItemGlampingEntity entity = (RegisterBookingItemGlampingEntity) setFieldDefaultEntityBase.setDefaultFieldBaseUpdate(entityParam);
        return entity;
    }

    @Override
    public RegisterBookingItemGlampingEntity setDefaultFieldBaseDelete(RegisterBookingItemGlampingEntity entityParam) throws RuntimeException {
        RegisterBookingItemGlampingEntity entity = (RegisterBookingItemGlampingEntity) setFieldDefaultEntityBase.setDefaultFieldBaseDelete(entityParam);
        return entity;
    }

    @Override
    public RegisterBookingItemGlampingDomain setDefaultFieldBaseUpdated(RegisterBookingItemGlampingDomain domainTarget, RegisterBookingItemGlampingDomain domainSource) throws RuntimeException {
        RegisterBookingItemGlampingDomain domain = (RegisterBookingItemGlampingDomain) setFieldDefaultEntityBase.setDefaultFieldBaseUpdated(domainTarget, domainSource);
        return domain;
    }
}
