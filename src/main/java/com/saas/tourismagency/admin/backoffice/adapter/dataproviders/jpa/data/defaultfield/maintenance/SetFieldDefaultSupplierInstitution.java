package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.defaultfield.maintenance;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.maintenance.SupplierInstitutionEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.core.SetFieldDefaultEntityBase;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.SupplierInstitutionDomain;
import dev.mm.javaee.core.repo.SetFieldDefaultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SetFieldDefaultSupplierInstitution implements SetFieldDefaultEntity<SupplierInstitutionEntity, SupplierInstitutionDomain> {

    @Autowired
    private SetFieldDefaultEntityBase setFieldDefaultEntityBase;

    @Override
    public SupplierInstitutionEntity setDefaultFieldBaseCreate(SupplierInstitutionEntity supplierInstitutionEntity) throws RuntimeException {
        SupplierInstitutionEntity entity = (SupplierInstitutionEntity) setFieldDefaultEntityBase.setDefaultFieldBaseCreate(supplierInstitutionEntity);
        return entity;
    }

    @Override
    public SupplierInstitutionEntity setDefaultFieldBaseUpdate(SupplierInstitutionEntity supplierInstitutionEntity) throws RuntimeException {
        SupplierInstitutionEntity entity = (SupplierInstitutionEntity) setFieldDefaultEntityBase.setDefaultFieldBaseUpdate(supplierInstitutionEntity);
        return entity;
    }

    @Override
    public SupplierInstitutionEntity setDefaultFieldBaseDelete(SupplierInstitutionEntity supplierInstitutionEntity) throws RuntimeException {
        SupplierInstitutionEntity entity = (SupplierInstitutionEntity) setFieldDefaultEntityBase.setDefaultFieldBaseDelete(supplierInstitutionEntity);
        return entity;
    }

    @Override
    public SupplierInstitutionDomain setDefaultFieldBaseUpdated(SupplierInstitutionDomain domainTarget, SupplierInstitutionDomain domainSource) throws RuntimeException {
        SupplierInstitutionDomain domain = (SupplierInstitutionDomain) setFieldDefaultEntityBase.setDefaultFieldBaseUpdated(domainTarget, domainSource);
        return domain;
    }
}
