package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.defaultfield.booking;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.booking.RegisterBookingGlampingEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.core.SetFieldDefaultEntityBase;
import com.saas.tourismagency.admin.backoffice.domain.booking.RegisterBookingGlampingDomain;
import dev.mm.javaee.core.repo.SetFieldDefaultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SetFieldDefaultRegisterBookingGlamping implements SetFieldDefaultEntity<RegisterBookingGlampingEntity, RegisterBookingGlampingDomain> {

    @Autowired
    private SetFieldDefaultEntityBase setFieldDefaultEntityBase;

    @Override
    public RegisterBookingGlampingEntity setDefaultFieldBaseCreate(RegisterBookingGlampingEntity entityParam) throws RuntimeException {
        RegisterBookingGlampingEntity entity = (RegisterBookingGlampingEntity) setFieldDefaultEntityBase.setDefaultFieldBaseCreate(entityParam);
        return entity;
    }

    @Override
    public RegisterBookingGlampingEntity setDefaultFieldBaseUpdate(RegisterBookingGlampingEntity entityParam) throws RuntimeException {
        RegisterBookingGlampingEntity entity = (RegisterBookingGlampingEntity) setFieldDefaultEntityBase.setDefaultFieldBaseUpdate(entityParam);
        return entity;
    }

    @Override
    public RegisterBookingGlampingEntity setDefaultFieldBaseDelete(RegisterBookingGlampingEntity entityParam) throws RuntimeException {
        RegisterBookingGlampingEntity entity = (RegisterBookingGlampingEntity) setFieldDefaultEntityBase.setDefaultFieldBaseDelete(entityParam);
        return entity;
    }

    @Override
    public RegisterBookingGlampingDomain setDefaultFieldBaseUpdated(RegisterBookingGlampingDomain domainTarget, RegisterBookingGlampingDomain domainSource) throws RuntimeException {
        RegisterBookingGlampingDomain domain = (RegisterBookingGlampingDomain) setFieldDefaultEntityBase.setDefaultFieldBaseUpdated(domainTarget, domainSource);
        return domain;
    }
}
