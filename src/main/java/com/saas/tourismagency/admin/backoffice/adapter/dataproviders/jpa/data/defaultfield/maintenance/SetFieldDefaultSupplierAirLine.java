package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.defaultfield.maintenance;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.maintenance.SupplierAirLineEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.core.SetFieldDefaultEntityBase;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.SupplierAirLineDomain;
import dev.mm.javaee.core.repo.SetFieldDefaultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SetFieldDefaultSupplierAirLine implements SetFieldDefaultEntity<SupplierAirLineEntity, SupplierAirLineDomain> {

    @Autowired
    private SetFieldDefaultEntityBase setFieldDefaultEntityBase;

    @Override
    public SupplierAirLineEntity setDefaultFieldBaseCreate(SupplierAirLineEntity supplierAirLineEntity) throws RuntimeException {
        SupplierAirLineEntity entity = (SupplierAirLineEntity) setFieldDefaultEntityBase.setDefaultFieldBaseCreate(supplierAirLineEntity);
        return entity;
    }

    @Override
    public SupplierAirLineEntity setDefaultFieldBaseUpdate(SupplierAirLineEntity supplierAirLineEntity) throws RuntimeException {
        SupplierAirLineEntity entity = (SupplierAirLineEntity) setFieldDefaultEntityBase.setDefaultFieldBaseUpdate(supplierAirLineEntity);
        return entity;
    }

    @Override
    public SupplierAirLineEntity setDefaultFieldBaseDelete(SupplierAirLineEntity supplierAirLineEntity) throws RuntimeException {
        SupplierAirLineEntity entity = (SupplierAirLineEntity) setFieldDefaultEntityBase.setDefaultFieldBaseDelete(supplierAirLineEntity);
        return entity;
    }

    @Override
    public SupplierAirLineDomain setDefaultFieldBaseUpdated(SupplierAirLineDomain domainTarget, SupplierAirLineDomain domainSource) throws RuntimeException {
        SupplierAirLineDomain domain = (SupplierAirLineDomain) setFieldDefaultEntityBase.setDefaultFieldBaseUpdated(domainTarget, domainSource);
        return domain;
    }
}
