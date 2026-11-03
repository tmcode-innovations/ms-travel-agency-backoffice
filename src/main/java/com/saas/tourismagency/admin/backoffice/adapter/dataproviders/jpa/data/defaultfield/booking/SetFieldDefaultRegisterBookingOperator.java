package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.defaultfield.booking;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.booking.RegisterBookingOperatorEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.core.SetFieldDefaultEntityBase;
import com.saas.tourismagency.admin.backoffice.domain.booking.RegisterBookingOperatorDomain;
import dev.mm.javaee.core.repo.SetFieldDefaultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SetFieldDefaultRegisterBookingOperator implements SetFieldDefaultEntity<RegisterBookingOperatorEntity, RegisterBookingOperatorDomain> {

    @Autowired
    private SetFieldDefaultEntityBase setFieldDefaultEntityBase;

    @Override
    public RegisterBookingOperatorEntity setDefaultFieldBaseCreate(RegisterBookingOperatorEntity entityParam) throws RuntimeException {
        RegisterBookingOperatorEntity entity = (RegisterBookingOperatorEntity) setFieldDefaultEntityBase.setDefaultFieldBaseCreate(entityParam);
        return entity;
    }

    @Override
    public RegisterBookingOperatorEntity setDefaultFieldBaseUpdate(RegisterBookingOperatorEntity entityParam) throws RuntimeException {
        RegisterBookingOperatorEntity entity = (RegisterBookingOperatorEntity) setFieldDefaultEntityBase.setDefaultFieldBaseUpdate(entityParam);
        return entity;
    }

    @Override
    public RegisterBookingOperatorEntity setDefaultFieldBaseDelete(RegisterBookingOperatorEntity entityParam) throws RuntimeException {
        RegisterBookingOperatorEntity entity = (RegisterBookingOperatorEntity) setFieldDefaultEntityBase.setDefaultFieldBaseDelete(entityParam);
        return entity;
    }

    @Override
    public RegisterBookingOperatorDomain setDefaultFieldBaseUpdated(RegisterBookingOperatorDomain domainTarget, RegisterBookingOperatorDomain domainSource) throws RuntimeException {
        RegisterBookingOperatorDomain domain = (RegisterBookingOperatorDomain) setFieldDefaultEntityBase.setDefaultFieldBaseUpdated(domainTarget, domainSource);
        return domain;
    }
}
