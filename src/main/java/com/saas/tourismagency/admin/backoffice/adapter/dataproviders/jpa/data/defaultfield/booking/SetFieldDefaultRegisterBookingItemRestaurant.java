package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.defaultfield.booking;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.booking.RegisterBookingItemRestaurantEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.core.SetFieldDefaultEntityBase;
import com.saas.tourismagency.admin.backoffice.domain.booking.RegisterBookingItemRestaurantDomain;
import dev.mm.javaee.core.repo.SetFieldDefaultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SetFieldDefaultRegisterBookingItemRestaurant implements SetFieldDefaultEntity<RegisterBookingItemRestaurantEntity, RegisterBookingItemRestaurantDomain> {

    @Autowired
    private SetFieldDefaultEntityBase setFieldDefaultEntityBase;

    @Override
    public RegisterBookingItemRestaurantEntity setDefaultFieldBaseCreate(RegisterBookingItemRestaurantEntity entityParam) throws RuntimeException {
        RegisterBookingItemRestaurantEntity entity = (RegisterBookingItemRestaurantEntity) setFieldDefaultEntityBase.setDefaultFieldBaseCreate(entityParam);
        return entity;
    }

    @Override
    public RegisterBookingItemRestaurantEntity setDefaultFieldBaseUpdate(RegisterBookingItemRestaurantEntity entityParam) throws RuntimeException {
        RegisterBookingItemRestaurantEntity entity = (RegisterBookingItemRestaurantEntity) setFieldDefaultEntityBase.setDefaultFieldBaseUpdate(entityParam);
        return entity;
    }

    @Override
    public RegisterBookingItemRestaurantEntity setDefaultFieldBaseDelete(RegisterBookingItemRestaurantEntity entityParam) throws RuntimeException {
        RegisterBookingItemRestaurantEntity entity = (RegisterBookingItemRestaurantEntity) setFieldDefaultEntityBase.setDefaultFieldBaseDelete(entityParam);
        return entity;
    }

    @Override
    public RegisterBookingItemRestaurantDomain setDefaultFieldBaseUpdated(RegisterBookingItemRestaurantDomain domainTarget, RegisterBookingItemRestaurantDomain domainSource) throws RuntimeException {
        RegisterBookingItemRestaurantDomain domain = (RegisterBookingItemRestaurantDomain) setFieldDefaultEntityBase.setDefaultFieldBaseUpdated(domainTarget, domainSource);
        return domain;
    }
}
