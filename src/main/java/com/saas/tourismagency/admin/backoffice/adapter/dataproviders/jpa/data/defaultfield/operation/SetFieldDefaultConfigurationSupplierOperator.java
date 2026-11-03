package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.defaultfield.operation;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.operation.ConfigurationSupplierOperatorEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.core.SetFieldDefaultEntityBase;
import com.saas.tourismagency.admin.backoffice.domain.operation.ConfigurationSupplierOperatorDomain;
import dev.mm.javaee.core.repo.SetFieldDefaultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SetFieldDefaultConfigurationSupplierOperator implements SetFieldDefaultEntity<ConfigurationSupplierOperatorEntity, ConfigurationSupplierOperatorDomain> {

    @Autowired
    private SetFieldDefaultEntityBase setFieldDefaultEntityBase;

    @Override
    public ConfigurationSupplierOperatorEntity setDefaultFieldBaseCreate(ConfigurationSupplierOperatorEntity entityParam) throws RuntimeException {
        ConfigurationSupplierOperatorEntity entity = (ConfigurationSupplierOperatorEntity) setFieldDefaultEntityBase.setDefaultFieldBaseCreate(entityParam);
        return entity;
    }

    @Override
    public ConfigurationSupplierOperatorEntity setDefaultFieldBaseUpdate(ConfigurationSupplierOperatorEntity entityParam) throws RuntimeException {
        ConfigurationSupplierOperatorEntity entity = (ConfigurationSupplierOperatorEntity) setFieldDefaultEntityBase.setDefaultFieldBaseUpdate(entityParam);
        return entity;
    }

    @Override
    public ConfigurationSupplierOperatorEntity setDefaultFieldBaseDelete(ConfigurationSupplierOperatorEntity entityParam) throws RuntimeException {
        ConfigurationSupplierOperatorEntity entity = (ConfigurationSupplierOperatorEntity) setFieldDefaultEntityBase.setDefaultFieldBaseDelete(entityParam);
        return entity;
    }

    @Override
    public ConfigurationSupplierOperatorDomain setDefaultFieldBaseUpdated(ConfigurationSupplierOperatorDomain domainTarget, ConfigurationSupplierOperatorDomain domainSource) throws RuntimeException {
        ConfigurationSupplierOperatorDomain domain = (ConfigurationSupplierOperatorDomain) setFieldDefaultEntityBase.setDefaultFieldBaseUpdated(domainTarget, domainSource);
        return domain;
    }
}
