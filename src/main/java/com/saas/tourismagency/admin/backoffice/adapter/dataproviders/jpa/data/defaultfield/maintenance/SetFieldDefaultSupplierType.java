package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.defaultfield.maintenance;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.maintenance.SupplierTypeEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.core.SetFieldDefaultEntityBase;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.SupplierTypeDomain;
import dev.mm.javaee.core.repo.SetFieldDefaultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SetFieldDefaultSupplierType implements SetFieldDefaultEntity<SupplierTypeEntity, SupplierTypeDomain> {

    @Autowired
    private SetFieldDefaultEntityBase setFieldDefaultEntityBase;

    @Override
    public SupplierTypeEntity setDefaultFieldBaseCreate(SupplierTypeEntity supplierTypeEntity) throws RuntimeException {
        SupplierTypeEntity entity = (SupplierTypeEntity) setFieldDefaultEntityBase.setDefaultFieldBaseCreate(supplierTypeEntity);
        return entity;
    }

    @Override
    public SupplierTypeEntity setDefaultFieldBaseUpdate(SupplierTypeEntity supplierTypeEntity) throws RuntimeException {
        SupplierTypeEntity entity = (SupplierTypeEntity) setFieldDefaultEntityBase.setDefaultFieldBaseUpdate(supplierTypeEntity);
        return entity;
    }

    @Override
    public SupplierTypeEntity setDefaultFieldBaseDelete(SupplierTypeEntity supplierTypeEntity) throws RuntimeException {
        SupplierTypeEntity entity = (SupplierTypeEntity) setFieldDefaultEntityBase.setDefaultFieldBaseDelete(supplierTypeEntity);
        return entity;
    }

    @Override
    public SupplierTypeDomain setDefaultFieldBaseUpdated(SupplierTypeDomain domainTarget, SupplierTypeDomain domainSource) throws RuntimeException {
        SupplierTypeDomain domain = (SupplierTypeDomain) setFieldDefaultEntityBase.setDefaultFieldBaseUpdated(domainTarget, domainSource);
        return domain;
    }
}
