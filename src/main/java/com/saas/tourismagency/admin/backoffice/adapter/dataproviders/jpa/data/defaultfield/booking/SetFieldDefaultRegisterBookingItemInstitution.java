package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.defaultfield.booking;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.booking.RegisterBookingItemInstitutionEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.core.SetFieldDefaultEntityBase;
import com.saas.tourismagency.admin.backoffice.domain.booking.RegisterBookingItemInstitutionDomain;
import dev.mm.javaee.core.repo.SetFieldDefaultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SetFieldDefaultRegisterBookingItemInstitution implements SetFieldDefaultEntity<RegisterBookingItemInstitutionEntity, RegisterBookingItemInstitutionDomain> {

    @Autowired
    private SetFieldDefaultEntityBase setFieldDefaultEntityBase;

    @Override
    public RegisterBookingItemInstitutionEntity setDefaultFieldBaseCreate(RegisterBookingItemInstitutionEntity entityParam) throws RuntimeException {
        RegisterBookingItemInstitutionEntity entity = (RegisterBookingItemInstitutionEntity) setFieldDefaultEntityBase.setDefaultFieldBaseCreate(entityParam);
        return entity;
    }

    @Override
    public RegisterBookingItemInstitutionEntity setDefaultFieldBaseUpdate(RegisterBookingItemInstitutionEntity entityParam) throws RuntimeException {
        RegisterBookingItemInstitutionEntity entity = (RegisterBookingItemInstitutionEntity) setFieldDefaultEntityBase.setDefaultFieldBaseUpdate(entityParam);
        return entity;
    }

    @Override
    public RegisterBookingItemInstitutionEntity setDefaultFieldBaseDelete(RegisterBookingItemInstitutionEntity entityParam) throws RuntimeException {
        RegisterBookingItemInstitutionEntity entity = (RegisterBookingItemInstitutionEntity) setFieldDefaultEntityBase.setDefaultFieldBaseDelete(entityParam);
        return entity;
    }

    @Override
    public RegisterBookingItemInstitutionDomain setDefaultFieldBaseUpdated(RegisterBookingItemInstitutionDomain domainTarget, RegisterBookingItemInstitutionDomain domainSource) throws RuntimeException {
        RegisterBookingItemInstitutionDomain domain = (RegisterBookingItemInstitutionDomain) setFieldDefaultEntityBase.setDefaultFieldBaseUpdated(domainTarget, domainSource);
        return domain;
    }
}
