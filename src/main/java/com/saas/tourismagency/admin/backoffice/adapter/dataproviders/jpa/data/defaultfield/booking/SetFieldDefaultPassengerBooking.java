package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.defaultfield.booking;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.booking.PassengerBookingEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.core.SetFieldDefaultEntityBase;
import com.saas.tourismagency.admin.backoffice.domain.booking.PassengerBookingDomain;
import dev.mm.javaee.core.repo.SetFieldDefaultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SetFieldDefaultPassengerBooking implements SetFieldDefaultEntity<PassengerBookingEntity, PassengerBookingDomain> {

    @Autowired
    private SetFieldDefaultEntityBase setFieldDefaultEntityBase;

    @Override
    public PassengerBookingEntity setDefaultFieldBaseCreate(PassengerBookingEntity entityParam) throws RuntimeException {
        PassengerBookingEntity entity = (PassengerBookingEntity) setFieldDefaultEntityBase.setDefaultFieldBaseCreate(entityParam);
        return entity;
    }

    @Override
    public PassengerBookingEntity setDefaultFieldBaseUpdate(PassengerBookingEntity entityParam) throws RuntimeException {
        PassengerBookingEntity entity = (PassengerBookingEntity) setFieldDefaultEntityBase.setDefaultFieldBaseUpdate(entityParam);
        return entity;
    }

    @Override
    public PassengerBookingEntity setDefaultFieldBaseDelete(PassengerBookingEntity entityParam) throws RuntimeException {
        PassengerBookingEntity entity = (PassengerBookingEntity) setFieldDefaultEntityBase.setDefaultFieldBaseDelete(entityParam);
        return entity;
    }

    @Override
    public PassengerBookingDomain setDefaultFieldBaseUpdated(PassengerBookingDomain domainTarget, PassengerBookingDomain domainSource) throws RuntimeException {
        PassengerBookingDomain domain = (PassengerBookingDomain) setFieldDefaultEntityBase.setDefaultFieldBaseUpdated(domainTarget, domainSource);
        return domain;
    }
}
