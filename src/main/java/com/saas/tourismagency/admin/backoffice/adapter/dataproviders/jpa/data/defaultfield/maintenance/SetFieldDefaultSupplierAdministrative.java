package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.defaultfield.maintenance;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.maintenance.SupplierAdministrativeEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.core.SetFieldDefaultEntityBase;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.SupplierAdministrativeDomain;
import dev.mm.javaee.core.repo.SetFieldDefaultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SetFieldDefaultSupplierAdministrative implements SetFieldDefaultEntity<SupplierAdministrativeEntity, SupplierAdministrativeDomain> {

    @Autowired
    private SetFieldDefaultEntityBase setFieldDefaultEntityBase;

    @Override
    public SupplierAdministrativeEntity setDefaultFieldBaseCreate(SupplierAdministrativeEntity supplierAdministrativeEntity) throws RuntimeException {
        SupplierAdministrativeEntity entity = (SupplierAdministrativeEntity) setFieldDefaultEntityBase.setDefaultFieldBaseCreate(supplierAdministrativeEntity);
        return entity;
    }

    @Override
    public SupplierAdministrativeEntity setDefaultFieldBaseUpdate(SupplierAdministrativeEntity supplierAdministrativeEntity) throws RuntimeException {
        SupplierAdministrativeEntity entity = (SupplierAdministrativeEntity) setFieldDefaultEntityBase.setDefaultFieldBaseUpdate(supplierAdministrativeEntity);
        return entity;
    }

    @Override
    public SupplierAdministrativeEntity setDefaultFieldBaseDelete(SupplierAdministrativeEntity supplierAdministrativeEntity) throws RuntimeException {
        SupplierAdministrativeEntity entity = (SupplierAdministrativeEntity) setFieldDefaultEntityBase.setDefaultFieldBaseDelete(supplierAdministrativeEntity);
        return entity;
    }

    @Override
    public SupplierAdministrativeDomain setDefaultFieldBaseUpdated(SupplierAdministrativeDomain domainTarget, SupplierAdministrativeDomain domainSource) throws RuntimeException {
        SupplierAdministrativeDomain domain = (SupplierAdministrativeDomain) setFieldDefaultEntityBase.setDefaultFieldBaseUpdated(domainTarget, domainSource);
        return domain;
    }
}
