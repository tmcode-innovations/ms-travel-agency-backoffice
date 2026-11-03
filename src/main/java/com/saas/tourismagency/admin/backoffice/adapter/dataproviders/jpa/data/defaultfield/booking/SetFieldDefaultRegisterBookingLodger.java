package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.defaultfield.booking;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.booking.RegisterBookingLodgerEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.core.SetFieldDefaultEntityBase;
import com.saas.tourismagency.admin.backoffice.domain.booking.RegisterBookingLodgerDomain;
import dev.mm.javaee.core.repo.SetFieldDefaultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SetFieldDefaultRegisterBookingLodger implements SetFieldDefaultEntity<RegisterBookingLodgerEntity, RegisterBookingLodgerDomain> {

    @Autowired
    private SetFieldDefaultEntityBase setFieldDefaultEntityBase;

    @Override
    public RegisterBookingLodgerEntity setDefaultFieldBaseCreate(RegisterBookingLodgerEntity entityParam) throws RuntimeException {
        RegisterBookingLodgerEntity entity = (RegisterBookingLodgerEntity) setFieldDefaultEntityBase.setDefaultFieldBaseCreate(entityParam);
        return entity;
    }

    @Override
    public RegisterBookingLodgerEntity setDefaultFieldBaseUpdate(RegisterBookingLodgerEntity entityParam) throws RuntimeException {
        RegisterBookingLodgerEntity entity = (RegisterBookingLodgerEntity) setFieldDefaultEntityBase.setDefaultFieldBaseUpdate(entityParam);
        return entity;
    }

    @Override
    public RegisterBookingLodgerEntity setDefaultFieldBaseDelete(RegisterBookingLodgerEntity entityParam) throws RuntimeException {
        RegisterBookingLodgerEntity entity = (RegisterBookingLodgerEntity) setFieldDefaultEntityBase.setDefaultFieldBaseDelete(entityParam);
        return entity;
    }

    @Override
    public RegisterBookingLodgerDomain setDefaultFieldBaseUpdated(RegisterBookingLodgerDomain domainTarget, RegisterBookingLodgerDomain domainSource) throws RuntimeException {
        RegisterBookingLodgerDomain domain = (RegisterBookingLodgerDomain) setFieldDefaultEntityBase.setDefaultFieldBaseUpdated(domainTarget, domainSource);
        return domain;
    }
}
