package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.defaultfield.maintenance;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.maintenance.SupplierOperatorEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.core.SetFieldDefaultEntityBase;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.SupplierOperatorDomain;
import dev.mm.javaee.core.repo.SetFieldDefaultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SetFieldDefaultSupplierOperator implements SetFieldDefaultEntity<SupplierOperatorEntity, SupplierOperatorDomain> {

    @Autowired
    private SetFieldDefaultEntityBase setFieldDefaultEntityBase;

    @Override
    public SupplierOperatorEntity setDefaultFieldBaseCreate(SupplierOperatorEntity supplierOperatorEntity) throws RuntimeException {
        SupplierOperatorEntity entity = (SupplierOperatorEntity) setFieldDefaultEntityBase.setDefaultFieldBaseCreate(supplierOperatorEntity);
        return entity;
    }

    @Override
    public SupplierOperatorEntity setDefaultFieldBaseUpdate(SupplierOperatorEntity supplierOperatorEntity) throws RuntimeException {
        SupplierOperatorEntity entity = (SupplierOperatorEntity) setFieldDefaultEntityBase.setDefaultFieldBaseUpdate(supplierOperatorEntity);
        return entity;
    }

    @Override
    public SupplierOperatorEntity setDefaultFieldBaseDelete(SupplierOperatorEntity supplierOperatorEntity) throws RuntimeException {
        SupplierOperatorEntity entity = (SupplierOperatorEntity) setFieldDefaultEntityBase.setDefaultFieldBaseDelete(supplierOperatorEntity);
        return entity;
    }

    @Override
    public SupplierOperatorDomain setDefaultFieldBaseUpdated(SupplierOperatorDomain domainTarget, SupplierOperatorDomain domainSource) throws RuntimeException {
        SupplierOperatorDomain domain = (SupplierOperatorDomain) setFieldDefaultEntityBase.setDefaultFieldBaseUpdated(domainTarget, domainSource);
        return domain;
    }
}
