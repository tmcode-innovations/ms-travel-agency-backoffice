package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.defaultfield.booking;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.booking.RegisterBookingItemLodgerEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.core.SetFieldDefaultEntityBase;
import com.saas.tourismagency.admin.backoffice.domain.booking.RegisterBookingItemLodgerDomain;
import dev.mm.javaee.core.repo.SetFieldDefaultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SetFieldDefaultRegisterBookingItemLodger implements SetFieldDefaultEntity<RegisterBookingItemLodgerEntity, RegisterBookingItemLodgerDomain> {

    @Autowired
    private SetFieldDefaultEntityBase setFieldDefaultEntityBase;

    @Override
    public RegisterBookingItemLodgerEntity setDefaultFieldBaseCreate(RegisterBookingItemLodgerEntity entityParam) throws RuntimeException {
        RegisterBookingItemLodgerEntity entity = (RegisterBookingItemLodgerEntity) setFieldDefaultEntityBase.setDefaultFieldBaseCreate(entityParam);
        return entity;
    }

    @Override
    public RegisterBookingItemLodgerEntity setDefaultFieldBaseUpdate(RegisterBookingItemLodgerEntity entityParam) throws RuntimeException {
        RegisterBookingItemLodgerEntity entity = (RegisterBookingItemLodgerEntity) setFieldDefaultEntityBase.setDefaultFieldBaseUpdate(entityParam);
        return entity;
    }

    @Override
    public RegisterBookingItemLodgerEntity setDefaultFieldBaseDelete(RegisterBookingItemLodgerEntity entityParam) throws RuntimeException {
        RegisterBookingItemLodgerEntity entity = (RegisterBookingItemLodgerEntity) setFieldDefaultEntityBase.setDefaultFieldBaseDelete(entityParam);
        return entity;
    }

    @Override
    public RegisterBookingItemLodgerDomain setDefaultFieldBaseUpdated(RegisterBookingItemLodgerDomain domainTarget, RegisterBookingItemLodgerDomain domainSource) throws RuntimeException {
        RegisterBookingItemLodgerDomain domain = (RegisterBookingItemLodgerDomain) setFieldDefaultEntityBase.setDefaultFieldBaseUpdated(domainTarget, domainSource);
        return domain;
    }
}
