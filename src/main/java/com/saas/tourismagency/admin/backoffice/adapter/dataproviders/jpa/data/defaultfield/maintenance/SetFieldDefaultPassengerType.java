package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.defaultfield.maintenance;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.maintenance.PassengerTypeEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.core.SetFieldDefaultEntityBase;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.PassengerTypeDomain;
import dev.mm.javaee.core.repo.SetFieldDefaultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SetFieldDefaultPassengerType implements SetFieldDefaultEntity<PassengerTypeEntity, PassengerTypeDomain> {

    @Autowired
    private SetFieldDefaultEntityBase setFieldDefaultEntityBase;

    @Override
    public PassengerTypeEntity setDefaultFieldBaseCreate(PassengerTypeEntity passengerTypeEntity) throws RuntimeException {
        PassengerTypeEntity entity = (PassengerTypeEntity) setFieldDefaultEntityBase.setDefaultFieldBaseCreate(passengerTypeEntity);
        return entity;
    }

    @Override
    public PassengerTypeEntity setDefaultFieldBaseUpdate(PassengerTypeEntity passengerTypeEntity) throws RuntimeException {
        PassengerTypeEntity entity = (PassengerTypeEntity) setFieldDefaultEntityBase.setDefaultFieldBaseUpdate(passengerTypeEntity);
        return entity;
    }

    @Override
    public PassengerTypeEntity setDefaultFieldBaseDelete(PassengerTypeEntity passengerTypeEntity) throws RuntimeException {
        PassengerTypeEntity entity = (PassengerTypeEntity) setFieldDefaultEntityBase.setDefaultFieldBaseDelete(passengerTypeEntity);
        return entity;
    }

    @Override
    public PassengerTypeDomain setDefaultFieldBaseUpdated(PassengerTypeDomain domainTarget, PassengerTypeDomain domainSource) throws RuntimeException {
        PassengerTypeDomain domain = (PassengerTypeDomain) setFieldDefaultEntityBase.setDefaultFieldBaseUpdated(domainTarget, domainSource);
        return domain;
    }
}
