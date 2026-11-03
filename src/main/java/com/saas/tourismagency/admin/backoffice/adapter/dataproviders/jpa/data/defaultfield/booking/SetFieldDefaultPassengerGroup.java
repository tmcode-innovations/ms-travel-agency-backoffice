package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.defaultfield.booking;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.booking.PassengerGroupEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.core.SetFieldDefaultEntityBase;
import com.saas.tourismagency.admin.backoffice.domain.booking.PassengerGroupDomain;
import dev.mm.javaee.core.repo.SetFieldDefaultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SetFieldDefaultPassengerGroup implements SetFieldDefaultEntity<PassengerGroupEntity, PassengerGroupDomain> {

    @Autowired
    private SetFieldDefaultEntityBase setFieldDefaultEntityBase;

    @Override
    public PassengerGroupEntity setDefaultFieldBaseCreate(PassengerGroupEntity entityParam) throws RuntimeException {
        PassengerGroupEntity entity = (PassengerGroupEntity) setFieldDefaultEntityBase.setDefaultFieldBaseCreate(entityParam);
        return entity;
    }

    @Override
    public PassengerGroupEntity setDefaultFieldBaseUpdate(PassengerGroupEntity entityParam) throws RuntimeException {
        PassengerGroupEntity entity = (PassengerGroupEntity) setFieldDefaultEntityBase.setDefaultFieldBaseUpdate(entityParam);
        return entity;
    }

    @Override
    public PassengerGroupEntity setDefaultFieldBaseDelete(PassengerGroupEntity entityParam) throws RuntimeException {
        PassengerGroupEntity entity = (PassengerGroupEntity) setFieldDefaultEntityBase.setDefaultFieldBaseDelete(entityParam);
        return entity;
    }

    @Override
    public PassengerGroupDomain setDefaultFieldBaseUpdated(PassengerGroupDomain domainTarget, PassengerGroupDomain domainSource) throws RuntimeException {
        PassengerGroupDomain domain = (PassengerGroupDomain) setFieldDefaultEntityBase.setDefaultFieldBaseUpdated(domainTarget, domainSource);
        return domain;
    }
}
