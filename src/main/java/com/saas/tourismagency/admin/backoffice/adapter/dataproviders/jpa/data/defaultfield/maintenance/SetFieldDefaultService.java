package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.defaultfield.maintenance;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.maintenance.ServiceEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.core.SetFieldDefaultEntityBase;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.ServiceDomain;
import dev.mm.javaee.core.repo.SetFieldDefaultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SetFieldDefaultService implements SetFieldDefaultEntity<ServiceEntity, ServiceDomain> {

    @Autowired
    private SetFieldDefaultEntityBase setFieldDefaultEntityBase;

    @Override
    public ServiceEntity setDefaultFieldBaseCreate(ServiceEntity customerEntity) throws RuntimeException {
        ServiceEntity entity = (ServiceEntity) setFieldDefaultEntityBase.setDefaultFieldBaseCreate(customerEntity);
        return entity;
    }

    @Override
    public ServiceEntity setDefaultFieldBaseUpdate(ServiceEntity customerEntity) throws RuntimeException {
        ServiceEntity entity = (ServiceEntity) setFieldDefaultEntityBase.setDefaultFieldBaseUpdate(customerEntity);
        return entity;
    }

    @Override
    public ServiceEntity setDefaultFieldBaseDelete(ServiceEntity customerEntity) throws RuntimeException {
        ServiceEntity entity = (ServiceEntity) setFieldDefaultEntityBase.setDefaultFieldBaseDelete(customerEntity);
        return entity;
    }

    @Override
    public ServiceDomain setDefaultFieldBaseUpdated(ServiceDomain domainTarget, ServiceDomain domainSource) throws RuntimeException {
        ServiceDomain domain = (ServiceDomain) setFieldDefaultEntityBase.setDefaultFieldBaseUpdated(domainTarget, domainSource);
        return domain;
    }
}
