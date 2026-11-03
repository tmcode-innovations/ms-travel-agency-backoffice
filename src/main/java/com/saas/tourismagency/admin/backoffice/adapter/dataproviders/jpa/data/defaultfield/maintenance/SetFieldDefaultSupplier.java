package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.defaultfield.maintenance;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.maintenance.SupplierEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.core.SetFieldDefaultEntityBase;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.SupplierDomain;
import dev.mm.javaee.core.repo.SetFieldDefaultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SetFieldDefaultSupplier implements SetFieldDefaultEntity<SupplierEntity, SupplierDomain> {

    @Autowired
    private SetFieldDefaultEntityBase setFieldDefaultEntityBase;

    @Override
    public SupplierEntity setDefaultFieldBaseCreate(SupplierEntity supplierEntity) throws RuntimeException {
        SupplierEntity entity = (SupplierEntity) setFieldDefaultEntityBase.setDefaultFieldBaseCreate(supplierEntity);
        return entity;
    }

    @Override
    public SupplierEntity setDefaultFieldBaseUpdate(SupplierEntity supplierEntity) throws RuntimeException {
        SupplierEntity entity = (SupplierEntity) setFieldDefaultEntityBase.setDefaultFieldBaseUpdate(supplierEntity);
        return entity;
    }

    @Override
    public SupplierEntity setDefaultFieldBaseDelete(SupplierEntity supplierEntity) throws RuntimeException {
        SupplierEntity entity = (SupplierEntity) setFieldDefaultEntityBase.setDefaultFieldBaseDelete(supplierEntity);
        return entity;
    }

    @Override
    public SupplierDomain setDefaultFieldBaseUpdated(SupplierDomain domainTarget, SupplierDomain domainSource) throws RuntimeException {
        SupplierDomain domain = (SupplierDomain) setFieldDefaultEntityBase.setDefaultFieldBaseUpdated(domainTarget, domainSource);
        return domain;
    }
}
