package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.defaultfield.maintenance;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.maintenance.SupplierLodgingEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.core.SetFieldDefaultEntityBase;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.SupplierLodgingDomain;
import dev.mm.javaee.core.repo.SetFieldDefaultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SetFieldDefaultSupplierLodging implements SetFieldDefaultEntity<SupplierLodgingEntity, SupplierLodgingDomain> {

    @Autowired
    private SetFieldDefaultEntityBase setFieldDefaultEntityBase;

    @Override
    public SupplierLodgingEntity setDefaultFieldBaseCreate(SupplierLodgingEntity supplierLodgingEntity) throws RuntimeException {
        SupplierLodgingEntity entity = (SupplierLodgingEntity) setFieldDefaultEntityBase.setDefaultFieldBaseCreate(supplierLodgingEntity);
        return entity;
    }

    @Override
    public SupplierLodgingEntity setDefaultFieldBaseUpdate(SupplierLodgingEntity supplierLodgingEntity) throws RuntimeException {
        SupplierLodgingEntity entity = (SupplierLodgingEntity) setFieldDefaultEntityBase.setDefaultFieldBaseUpdate(supplierLodgingEntity);
        return entity;
    }

    @Override
    public SupplierLodgingEntity setDefaultFieldBaseDelete(SupplierLodgingEntity supplierLodgingEntity) throws RuntimeException {
        SupplierLodgingEntity entity = (SupplierLodgingEntity) setFieldDefaultEntityBase.setDefaultFieldBaseDelete(supplierLodgingEntity);
        return entity;
    }

    @Override
    public SupplierLodgingDomain setDefaultFieldBaseUpdated(SupplierLodgingDomain domainTarget, SupplierLodgingDomain domainSource) throws RuntimeException {
        SupplierLodgingDomain domain = (SupplierLodgingDomain) setFieldDefaultEntityBase.setDefaultFieldBaseUpdated(domainTarget, domainSource);
        return domain;
    }
}
