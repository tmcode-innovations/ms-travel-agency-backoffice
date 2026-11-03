package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.defaultfield.maintenance;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.maintenance.SupplierLodgerEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.core.SetFieldDefaultEntityBase;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.SupplierLodgerDomain;
import dev.mm.javaee.core.repo.SetFieldDefaultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SetFieldDefaultSupplierLodger implements SetFieldDefaultEntity<SupplierLodgerEntity, SupplierLodgerDomain> {

    @Autowired
    private SetFieldDefaultEntityBase setFieldDefaultEntityBase;

    @Override
    public SupplierLodgerEntity setDefaultFieldBaseCreate(SupplierLodgerEntity supplierLodgerEntity) throws RuntimeException {
        SupplierLodgerEntity entity = (SupplierLodgerEntity) setFieldDefaultEntityBase.setDefaultFieldBaseCreate(supplierLodgerEntity);
        return entity;
    }

    @Override
    public SupplierLodgerEntity setDefaultFieldBaseUpdate(SupplierLodgerEntity supplierLodgerEntity) throws RuntimeException {
        SupplierLodgerEntity entity = (SupplierLodgerEntity) setFieldDefaultEntityBase.setDefaultFieldBaseUpdate(supplierLodgerEntity);
        return entity;
    }

    @Override
    public SupplierLodgerEntity setDefaultFieldBaseDelete(SupplierLodgerEntity supplierLodgerEntity) throws RuntimeException {
        SupplierLodgerEntity entity = (SupplierLodgerEntity) setFieldDefaultEntityBase.setDefaultFieldBaseDelete(supplierLodgerEntity);
        return entity;
    }

    @Override
    public SupplierLodgerDomain setDefaultFieldBaseUpdated(SupplierLodgerDomain domainTarget, SupplierLodgerDomain domainSource) throws RuntimeException {
        SupplierLodgerDomain domain = (SupplierLodgerDomain) setFieldDefaultEntityBase.setDefaultFieldBaseUpdated(domainTarget, domainSource);
        return domain;
    }
}
