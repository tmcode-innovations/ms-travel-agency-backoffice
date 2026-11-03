package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.defaultfield.booking;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.booking.RegisterBookingRestaurantEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.core.SetFieldDefaultEntityBase;
import com.saas.tourismagency.admin.backoffice.domain.booking.RegisterBookingRestaurantDomain;
import dev.mm.javaee.core.repo.SetFieldDefaultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SetFieldDefaultRegisterBookingRestaurant implements SetFieldDefaultEntity<RegisterBookingRestaurantEntity, RegisterBookingRestaurantDomain> {

    @Autowired
    private SetFieldDefaultEntityBase setFieldDefaultEntityBase;

    @Override
    public RegisterBookingRestaurantEntity setDefaultFieldBaseCreate(RegisterBookingRestaurantEntity entityParam) throws RuntimeException {
        RegisterBookingRestaurantEntity entity = (RegisterBookingRestaurantEntity) setFieldDefaultEntityBase.setDefaultFieldBaseCreate(entityParam);
        return entity;
    }

    @Override
    public RegisterBookingRestaurantEntity setDefaultFieldBaseUpdate(RegisterBookingRestaurantEntity entityParam) throws RuntimeException {
        RegisterBookingRestaurantEntity entity = (RegisterBookingRestaurantEntity) setFieldDefaultEntityBase.setDefaultFieldBaseUpdate(entityParam);
        return entity;
    }

    @Override
    public RegisterBookingRestaurantEntity setDefaultFieldBaseDelete(RegisterBookingRestaurantEntity entityParam) throws RuntimeException {
        RegisterBookingRestaurantEntity entity = (RegisterBookingRestaurantEntity) setFieldDefaultEntityBase.setDefaultFieldBaseDelete(entityParam);
        return entity;
    }

    @Override
    public RegisterBookingRestaurantDomain setDefaultFieldBaseUpdated(RegisterBookingRestaurantDomain domainTarget, RegisterBookingRestaurantDomain domainSource) throws RuntimeException {
        RegisterBookingRestaurantDomain domain = (RegisterBookingRestaurantDomain) setFieldDefaultEntityBase.setDefaultFieldBaseUpdated(domainTarget, domainSource);
        return domain;
    }
}
