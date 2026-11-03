package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.defaultfield.maintenance;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.maintenance.SupplierGlampingEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.core.SetFieldDefaultEntityBase;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.SupplierGlampingDomain;
import dev.mm.javaee.core.repo.SetFieldDefaultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SetFieldDefaultSupplierGlamping implements SetFieldDefaultEntity<SupplierGlampingEntity, SupplierGlampingDomain> {

    @Autowired
    private SetFieldDefaultEntityBase setFieldDefaultEntityBase;

    @Override
    public SupplierGlampingEntity setDefaultFieldBaseCreate(SupplierGlampingEntity supplierGlampingEntity) throws RuntimeException {
        SupplierGlampingEntity entity = (SupplierGlampingEntity) setFieldDefaultEntityBase.setDefaultFieldBaseCreate(supplierGlampingEntity);
        return entity;
    }

    @Override
    public SupplierGlampingEntity setDefaultFieldBaseUpdate(SupplierGlampingEntity supplierGlampingEntity) throws RuntimeException {
        SupplierGlampingEntity entity = (SupplierGlampingEntity) setFieldDefaultEntityBase.setDefaultFieldBaseUpdate(supplierGlampingEntity);
        return entity;
    }

    @Override
    public SupplierGlampingEntity setDefaultFieldBaseDelete(SupplierGlampingEntity supplierGlampingEntity) throws RuntimeException {
        SupplierGlampingEntity entity = (SupplierGlampingEntity) setFieldDefaultEntityBase.setDefaultFieldBaseDelete(supplierGlampingEntity);
        return entity;
    }

    @Override
    public SupplierGlampingDomain setDefaultFieldBaseUpdated(SupplierGlampingDomain domainTarget, SupplierGlampingDomain domainSource) throws RuntimeException {
        SupplierGlampingDomain domain = (SupplierGlampingDomain) setFieldDefaultEntityBase.setDefaultFieldBaseUpdated(domainTarget, domainSource);
        return domain;
    }
}
