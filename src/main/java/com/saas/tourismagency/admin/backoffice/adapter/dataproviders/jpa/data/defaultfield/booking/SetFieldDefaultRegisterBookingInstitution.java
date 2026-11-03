package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.defaultfield.booking;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.booking.RegisterBookingInstitutionEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.core.SetFieldDefaultEntityBase;
import com.saas.tourismagency.admin.backoffice.domain.booking.RegisterBookingInstitutionDomain;
import dev.mm.javaee.core.repo.SetFieldDefaultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SetFieldDefaultRegisterBookingInstitution implements SetFieldDefaultEntity<RegisterBookingInstitutionEntity, RegisterBookingInstitutionDomain> {

    @Autowired
    private SetFieldDefaultEntityBase setFieldDefaultEntityBase;

    @Override
    public RegisterBookingInstitutionEntity setDefaultFieldBaseCreate(RegisterBookingInstitutionEntity entityParam) throws RuntimeException {
        RegisterBookingInstitutionEntity entity = (RegisterBookingInstitutionEntity) setFieldDefaultEntityBase.setDefaultFieldBaseCreate(entityParam);
        return entity;
    }

    @Override
    public RegisterBookingInstitutionEntity setDefaultFieldBaseUpdate(RegisterBookingInstitutionEntity entityParam) throws RuntimeException {
        RegisterBookingInstitutionEntity entity = (RegisterBookingInstitutionEntity) setFieldDefaultEntityBase.setDefaultFieldBaseUpdate(entityParam);
        return entity;
    }

    @Override
    public RegisterBookingInstitutionEntity setDefaultFieldBaseDelete(RegisterBookingInstitutionEntity entityParam) throws RuntimeException {
        RegisterBookingInstitutionEntity entity = (RegisterBookingInstitutionEntity) setFieldDefaultEntityBase.setDefaultFieldBaseDelete(entityParam);
        return entity;
    }

    @Override
    public RegisterBookingInstitutionDomain setDefaultFieldBaseUpdated(RegisterBookingInstitutionDomain domainTarget, RegisterBookingInstitutionDomain domainSource) throws RuntimeException {
        RegisterBookingInstitutionDomain domain = (RegisterBookingInstitutionDomain) setFieldDefaultEntityBase.setDefaultFieldBaseUpdated(domainTarget, domainSource);
        return domain;
    }
}
