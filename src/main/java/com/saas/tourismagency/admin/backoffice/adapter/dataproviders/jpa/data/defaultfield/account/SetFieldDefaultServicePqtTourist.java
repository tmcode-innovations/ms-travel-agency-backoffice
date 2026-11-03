package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.defaultfield.account;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.account.ServicePqtTouristEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.core.SetFieldDefaultEntityBase;
import com.saas.tourismagency.admin.backoffice.domain.account.ServicePqtTouristDomain;
import dev.mm.javaee.core.repo.SetFieldDefaultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SetFieldDefaultServicePqtTourist implements SetFieldDefaultEntity<ServicePqtTouristEntity, ServicePqtTouristDomain> {

    @Autowired
    private SetFieldDefaultEntityBase setFieldDefaultEntityBase;

    @Override
    public ServicePqtTouristEntity setDefaultFieldBaseCreate(ServicePqtTouristEntity entityParam) throws RuntimeException {
        ServicePqtTouristEntity entity = (ServicePqtTouristEntity) setFieldDefaultEntityBase.setDefaultFieldBaseCreate(entityParam);
        return entity;
    }

    @Override
    public ServicePqtTouristEntity setDefaultFieldBaseUpdate(ServicePqtTouristEntity entityParam) throws RuntimeException {
        ServicePqtTouristEntity entity = (ServicePqtTouristEntity) setFieldDefaultEntityBase.setDefaultFieldBaseUpdate(entityParam);
        return entity;
    }

    @Override
    public ServicePqtTouristEntity setDefaultFieldBaseDelete(ServicePqtTouristEntity entityParam) throws RuntimeException {
        ServicePqtTouristEntity entity = (ServicePqtTouristEntity) setFieldDefaultEntityBase.setDefaultFieldBaseDelete(entityParam);
        return entity;
    }

    @Override
    public ServicePqtTouristDomain setDefaultFieldBaseUpdated(ServicePqtTouristDomain domainTarget, ServicePqtTouristDomain domainSource) throws RuntimeException {
        ServicePqtTouristDomain domain = (ServicePqtTouristDomain) setFieldDefaultEntityBase.setDefaultFieldBaseUpdated(domainTarget, domainSource);
        return domain;
    }
}
