package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.defaultfield.crm;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.crm.PassengerEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.core.SetFieldDefaultEntityBase;
import com.saas.tourismagency.admin.backoffice.domain.crm.PassengerDomain;
import dev.mm.javaee.core.repo.SetFieldDefaultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SetFieldDefaultPassenger implements SetFieldDefaultEntity<PassengerEntity, PassengerDomain> {

    @Autowired
    private SetFieldDefaultEntityBase setFieldDefaultEntityBase;

    @Override
    public PassengerEntity setDefaultFieldBaseCreate(PassengerEntity passengerEntity) throws RuntimeException {
        PassengerEntity entity = (PassengerEntity) setFieldDefaultEntityBase.setDefaultFieldBaseCreate(passengerEntity);
        return entity;
    }

    @Override
    public PassengerEntity setDefaultFieldBaseUpdate(PassengerEntity passengerEntity) throws RuntimeException {
        PassengerEntity entity = (PassengerEntity) setFieldDefaultEntityBase.setDefaultFieldBaseUpdate(passengerEntity);
        return entity;
    }

    @Override
    public PassengerEntity setDefaultFieldBaseDelete(PassengerEntity passengerEntity) throws RuntimeException {
        PassengerEntity entity = (PassengerEntity) setFieldDefaultEntityBase.setDefaultFieldBaseDelete(passengerEntity);
        return entity;
    }

    @Override
    public PassengerDomain setDefaultFieldBaseUpdated(PassengerDomain domainTarget, PassengerDomain domainSource) throws RuntimeException {
        PassengerDomain domain = (PassengerDomain) setFieldDefaultEntityBase.setDefaultFieldBaseUpdated(domainTarget, domainSource);
        return domain;
    }
}
