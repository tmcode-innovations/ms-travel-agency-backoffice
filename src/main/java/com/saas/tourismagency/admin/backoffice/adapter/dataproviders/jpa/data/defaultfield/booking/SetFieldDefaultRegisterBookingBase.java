package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.defaultfield.booking;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.booking.RegisterBookingBaseEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.core.SetFieldDefaultEntityBase;
import com.saas.tourismagency.admin.backoffice.domain.booking.RegisterBookingBaseDomain;
import dev.mm.javaee.core.repo.SetFieldDefaultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SetFieldDefaultRegisterBookingBase implements SetFieldDefaultEntity<RegisterBookingBaseEntity, RegisterBookingBaseDomain> {

    @Autowired
    private SetFieldDefaultEntityBase setFieldDefaultEntityBase;

    @Override
    public RegisterBookingBaseEntity setDefaultFieldBaseCreate(RegisterBookingBaseEntity entityParam) throws RuntimeException {
        RegisterBookingBaseEntity entity = (RegisterBookingBaseEntity) setFieldDefaultEntityBase.setDefaultFieldBaseCreate(entityParam);
        return entity;
    }

    @Override
    public RegisterBookingBaseEntity setDefaultFieldBaseUpdate(RegisterBookingBaseEntity entityParam) throws RuntimeException {
        RegisterBookingBaseEntity entity = (RegisterBookingBaseEntity) setFieldDefaultEntityBase.setDefaultFieldBaseUpdate(entityParam);
        return entity;
    }

    @Override
    public RegisterBookingBaseEntity setDefaultFieldBaseDelete(RegisterBookingBaseEntity entityParam) throws RuntimeException {
        RegisterBookingBaseEntity entity = (RegisterBookingBaseEntity) setFieldDefaultEntityBase.setDefaultFieldBaseDelete(entityParam);
        return entity;
    }

    @Override
    public RegisterBookingBaseDomain setDefaultFieldBaseUpdated(RegisterBookingBaseDomain domainTarget, RegisterBookingBaseDomain domainSource) throws RuntimeException {
        RegisterBookingBaseDomain domain = (RegisterBookingBaseDomain) setFieldDefaultEntityBase.setDefaultFieldBaseUpdated(domainTarget, domainSource);
        return domain;
    }
}
