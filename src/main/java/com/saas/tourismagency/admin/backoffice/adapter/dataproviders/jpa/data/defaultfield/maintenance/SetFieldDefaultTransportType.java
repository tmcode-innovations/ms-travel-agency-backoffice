package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.defaultfield.maintenance;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.maintenance.TransportTypeEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.core.SetFieldDefaultEntityBase;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.TransportTypeDomain;
import dev.mm.javaee.core.repo.SetFieldDefaultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SetFieldDefaultTransportType implements SetFieldDefaultEntity<TransportTypeEntity, TransportTypeDomain> {

    @Autowired
    private SetFieldDefaultEntityBase setFieldDefaultEntityBase;

    @Override
    public TransportTypeEntity setDefaultFieldBaseCreate(TransportTypeEntity transportTypeEntity) throws RuntimeException {
        TransportTypeEntity entity = (TransportTypeEntity) setFieldDefaultEntityBase.setDefaultFieldBaseCreate(transportTypeEntity);
        return entity;
    }

    @Override
    public TransportTypeEntity setDefaultFieldBaseUpdate(TransportTypeEntity transportTypeEntity) throws RuntimeException {
        TransportTypeEntity entity = (TransportTypeEntity) setFieldDefaultEntityBase.setDefaultFieldBaseUpdate(transportTypeEntity);
        return entity;
    }

    @Override
    public TransportTypeEntity setDefaultFieldBaseDelete(TransportTypeEntity transportTypeEntity) throws RuntimeException {
        TransportTypeEntity entity = (TransportTypeEntity) setFieldDefaultEntityBase.setDefaultFieldBaseDelete(transportTypeEntity);
        return entity;
    }

    @Override
    public TransportTypeDomain setDefaultFieldBaseUpdated(TransportTypeDomain domainTarget, TransportTypeDomain domainSource) throws RuntimeException {
        TransportTypeDomain domain = (TransportTypeDomain) setFieldDefaultEntityBase.setDefaultFieldBaseUpdated(domainTarget, domainSource);
        return domain;
    }
}
