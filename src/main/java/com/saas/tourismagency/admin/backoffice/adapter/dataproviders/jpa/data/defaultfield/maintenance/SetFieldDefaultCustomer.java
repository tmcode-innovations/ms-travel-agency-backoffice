package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.defaultfield.maintenance;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.maintenance.CustomerEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.core.SetFieldDefaultEntityBase;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.CustomerDomain;
import dev.mm.javaee.core.repo.SetFieldDefaultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SetFieldDefaultCustomer implements SetFieldDefaultEntity<CustomerEntity, CustomerDomain> {

    @Autowired
    private SetFieldDefaultEntityBase setFieldDefaultEntityBase;

    @Override
    public CustomerEntity setDefaultFieldBaseCreate(CustomerEntity customerEntity) throws RuntimeException {
        CustomerEntity entity = (CustomerEntity) setFieldDefaultEntityBase.setDefaultFieldBaseCreate(customerEntity);
        return entity;
    }

    @Override
    public CustomerEntity setDefaultFieldBaseUpdate(CustomerEntity customerEntity) throws RuntimeException {
        CustomerEntity entity = (CustomerEntity) setFieldDefaultEntityBase.setDefaultFieldBaseUpdate(customerEntity);
        return entity;
    }

    @Override
    public CustomerEntity setDefaultFieldBaseDelete(CustomerEntity customerEntity) throws RuntimeException {
        CustomerEntity entity = (CustomerEntity) setFieldDefaultEntityBase.setDefaultFieldBaseDelete(customerEntity);
        return entity;
    }

    @Override
    public CustomerDomain setDefaultFieldBaseUpdated(CustomerDomain domainTarget, CustomerDomain domainSource) throws RuntimeException {
        CustomerDomain domain = (CustomerDomain) setFieldDefaultEntityBase.setDefaultFieldBaseUpdated(domainTarget, domainSource);
        return domain;
    }
}
