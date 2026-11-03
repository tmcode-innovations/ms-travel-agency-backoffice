package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.defaultfield.maintenance;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.maintenance.SupplierRestaurantEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.core.SetFieldDefaultEntityBase;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.SupplierRestaurantDomain;
import dev.mm.javaee.core.repo.SetFieldDefaultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SetFieldDefaultSupplierRestaurant implements SetFieldDefaultEntity<SupplierRestaurantEntity, SupplierRestaurantDomain> {

    @Autowired
    private SetFieldDefaultEntityBase setFieldDefaultEntityBase;

    @Override
    public SupplierRestaurantEntity setDefaultFieldBaseCreate(SupplierRestaurantEntity supplierRestaurantEntity) throws RuntimeException {
        SupplierRestaurantEntity entity = (SupplierRestaurantEntity) setFieldDefaultEntityBase.setDefaultFieldBaseCreate(supplierRestaurantEntity);
        return entity;
    }

    @Override
    public SupplierRestaurantEntity setDefaultFieldBaseUpdate(SupplierRestaurantEntity supplierRestaurantEntity) throws RuntimeException {
        SupplierRestaurantEntity entity = (SupplierRestaurantEntity) setFieldDefaultEntityBase.setDefaultFieldBaseUpdate(supplierRestaurantEntity);
        return entity;
    }

    @Override
    public SupplierRestaurantEntity setDefaultFieldBaseDelete(SupplierRestaurantEntity supplierRestaurantEntity) throws RuntimeException {
        SupplierRestaurantEntity entity = (SupplierRestaurantEntity) setFieldDefaultEntityBase.setDefaultFieldBaseDelete(supplierRestaurantEntity);
        return entity;
    }

    @Override
    public SupplierRestaurantDomain setDefaultFieldBaseUpdated(SupplierRestaurantDomain domainTarget, SupplierRestaurantDomain domainSource) throws RuntimeException {
        SupplierRestaurantDomain domain = (SupplierRestaurantDomain) setFieldDefaultEntityBase.setDefaultFieldBaseUpdated(domainTarget, domainSource);
        return domain;
    }
}
