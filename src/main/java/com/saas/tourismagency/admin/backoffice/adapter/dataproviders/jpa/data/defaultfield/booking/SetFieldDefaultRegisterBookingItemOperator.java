package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.defaultfield.booking;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.booking.RegisterBookingItemOperatorEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.core.SetFieldDefaultEntityBase;
import com.saas.tourismagency.admin.backoffice.domain.booking.RegisterBookingItemOperatorDomain;
import dev.mm.javaee.core.repo.SetFieldDefaultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SetFieldDefaultRegisterBookingItemOperator implements SetFieldDefaultEntity<RegisterBookingItemOperatorEntity, RegisterBookingItemOperatorDomain> {

    @Autowired
    private SetFieldDefaultEntityBase setFieldDefaultEntityBase;

    @Override
    public RegisterBookingItemOperatorEntity setDefaultFieldBaseCreate(RegisterBookingItemOperatorEntity entityParam) throws RuntimeException {
        RegisterBookingItemOperatorEntity entity = (RegisterBookingItemOperatorEntity) setFieldDefaultEntityBase.setDefaultFieldBaseCreate(entityParam);
        return entity;
    }

    @Override
    public RegisterBookingItemOperatorEntity setDefaultFieldBaseUpdate(RegisterBookingItemOperatorEntity entityParam) throws RuntimeException {
        RegisterBookingItemOperatorEntity entity = (RegisterBookingItemOperatorEntity) setFieldDefaultEntityBase.setDefaultFieldBaseUpdate(entityParam);
        return entity;
    }

    @Override
    public RegisterBookingItemOperatorEntity setDefaultFieldBaseDelete(RegisterBookingItemOperatorEntity entityParam) throws RuntimeException {
        RegisterBookingItemOperatorEntity entity = (RegisterBookingItemOperatorEntity) setFieldDefaultEntityBase.setDefaultFieldBaseDelete(entityParam);
        return entity;
    }

    @Override
    public RegisterBookingItemOperatorDomain setDefaultFieldBaseUpdated(RegisterBookingItemOperatorDomain domainTarget, RegisterBookingItemOperatorDomain domainSource) throws RuntimeException {
        RegisterBookingItemOperatorDomain domain = (RegisterBookingItemOperatorDomain) setFieldDefaultEntityBase.setDefaultFieldBaseUpdated(domainTarget, domainSource);
        return domain;
    }
}
