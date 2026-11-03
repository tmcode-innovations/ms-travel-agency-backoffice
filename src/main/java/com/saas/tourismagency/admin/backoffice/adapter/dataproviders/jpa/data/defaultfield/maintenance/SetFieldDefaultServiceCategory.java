package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.defaultfield.maintenance;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.maintenance.ServiceCategoryEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.core.SetFieldDefaultEntityBase;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.ServiceCategoryDomain;
import dev.mm.javaee.core.repo.SetFieldDefaultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SetFieldDefaultServiceCategory implements SetFieldDefaultEntity<ServiceCategoryEntity, ServiceCategoryDomain> {

    @Autowired
    private SetFieldDefaultEntityBase setFieldDefaultEntityBase;

    @Override
    public ServiceCategoryEntity setDefaultFieldBaseCreate(ServiceCategoryEntity customerEntity) throws RuntimeException {
        ServiceCategoryEntity entity = (ServiceCategoryEntity) setFieldDefaultEntityBase.setDefaultFieldBaseCreate(customerEntity);
        return entity;
    }

    @Override
    public ServiceCategoryEntity setDefaultFieldBaseUpdate(ServiceCategoryEntity customerEntity) throws RuntimeException {
        ServiceCategoryEntity entity = (ServiceCategoryEntity) setFieldDefaultEntityBase.setDefaultFieldBaseUpdate(customerEntity);
        return entity;
    }

    @Override
    public ServiceCategoryEntity setDefaultFieldBaseDelete(ServiceCategoryEntity customerEntity) throws RuntimeException {
        ServiceCategoryEntity entity = (ServiceCategoryEntity) setFieldDefaultEntityBase.setDefaultFieldBaseDelete(customerEntity);
        return entity;
    }

    @Override
    public ServiceCategoryDomain setDefaultFieldBaseUpdated(ServiceCategoryDomain domainTarget, ServiceCategoryDomain domainSource) throws RuntimeException {
        ServiceCategoryDomain domain = (ServiceCategoryDomain) setFieldDefaultEntityBase.setDefaultFieldBaseUpdated(domainTarget, domainSource);
        return domain;
    }
}
