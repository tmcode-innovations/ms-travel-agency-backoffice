package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.defaultfield.booking;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.booking.RegisterBookingLodgingEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.core.SetFieldDefaultEntityBase;
import com.saas.tourismagency.admin.backoffice.domain.booking.RegisterBookingLodgingDomain;
import dev.mm.javaee.core.repo.SetFieldDefaultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SetFieldDefaultRegisterBookingLodging implements SetFieldDefaultEntity<RegisterBookingLodgingEntity, RegisterBookingLodgingDomain> {

    @Autowired
    private SetFieldDefaultEntityBase setFieldDefaultEntityBase;

    @Override
    public RegisterBookingLodgingEntity setDefaultFieldBaseCreate(RegisterBookingLodgingEntity entityParam) throws RuntimeException {
        RegisterBookingLodgingEntity entity = (RegisterBookingLodgingEntity) setFieldDefaultEntityBase.setDefaultFieldBaseCreate(entityParam);
        return entity;
    }

    @Override
    public RegisterBookingLodgingEntity setDefaultFieldBaseUpdate(RegisterBookingLodgingEntity entityParam) throws RuntimeException {
        RegisterBookingLodgingEntity entity = (RegisterBookingLodgingEntity) setFieldDefaultEntityBase.setDefaultFieldBaseUpdate(entityParam);
        return entity;
    }

    @Override
    public RegisterBookingLodgingEntity setDefaultFieldBaseDelete(RegisterBookingLodgingEntity entityParam) throws RuntimeException {
        RegisterBookingLodgingEntity entity = (RegisterBookingLodgingEntity) setFieldDefaultEntityBase.setDefaultFieldBaseDelete(entityParam);
        return entity;
    }

    @Override
    public RegisterBookingLodgingDomain setDefaultFieldBaseUpdated(RegisterBookingLodgingDomain domainTarget, RegisterBookingLodgingDomain domainSource) throws RuntimeException {
        RegisterBookingLodgingDomain domain = (RegisterBookingLodgingDomain) setFieldDefaultEntityBase.setDefaultFieldBaseUpdated(domainTarget, domainSource);
        return domain;
    }
}
