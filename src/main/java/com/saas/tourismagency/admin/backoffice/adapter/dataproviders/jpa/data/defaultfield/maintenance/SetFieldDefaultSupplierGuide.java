package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.defaultfield.maintenance;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.maintenance.SupplierGuideEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.core.SetFieldDefaultEntityBase;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.SupplierGuideDomain;
import dev.mm.javaee.core.repo.SetFieldDefaultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SetFieldDefaultSupplierGuide implements SetFieldDefaultEntity<SupplierGuideEntity, SupplierGuideDomain> {

    @Autowired
    private SetFieldDefaultEntityBase setFieldDefaultEntityBase;

    @Override
    public SupplierGuideEntity setDefaultFieldBaseCreate(SupplierGuideEntity supplierGuideEntity) throws RuntimeException {
        SupplierGuideEntity entity = (SupplierGuideEntity) setFieldDefaultEntityBase.setDefaultFieldBaseCreate(supplierGuideEntity);
        return entity;
    }

    @Override
    public SupplierGuideEntity setDefaultFieldBaseUpdate(SupplierGuideEntity supplierGuideEntity) throws RuntimeException {
        SupplierGuideEntity entity = (SupplierGuideEntity) setFieldDefaultEntityBase.setDefaultFieldBaseUpdate(supplierGuideEntity);
        return entity;
    }

    @Override
    public SupplierGuideEntity setDefaultFieldBaseDelete(SupplierGuideEntity supplierGuideEntity) throws RuntimeException {
        SupplierGuideEntity entity = (SupplierGuideEntity) setFieldDefaultEntityBase.setDefaultFieldBaseDelete(supplierGuideEntity);
        return entity;
    }

    @Override
    public SupplierGuideDomain setDefaultFieldBaseUpdated(SupplierGuideDomain domainTarget, SupplierGuideDomain domainSource) throws RuntimeException {
        SupplierGuideDomain domain = (SupplierGuideDomain) setFieldDefaultEntityBase.setDefaultFieldBaseUpdated(domainTarget, domainSource);
        return domain;
    }
}
