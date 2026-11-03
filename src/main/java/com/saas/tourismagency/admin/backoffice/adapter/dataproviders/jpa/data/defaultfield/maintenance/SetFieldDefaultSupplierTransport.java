package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.defaultfield.maintenance;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.maintenance.SupplierTransportEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.core.SetFieldDefaultEntityBase;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.SupplierTransportDomain;
import dev.mm.javaee.core.repo.SetFieldDefaultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SetFieldDefaultSupplierTransport implements SetFieldDefaultEntity<SupplierTransportEntity, SupplierTransportDomain> {

    @Autowired
    private SetFieldDefaultEntityBase setFieldDefaultEntityBase;

    @Override
    public SupplierTransportEntity setDefaultFieldBaseCreate(SupplierTransportEntity supplierTransportEntity) throws RuntimeException {
        SupplierTransportEntity entity = (SupplierTransportEntity) setFieldDefaultEntityBase.setDefaultFieldBaseCreate(supplierTransportEntity);
        return entity;
    }

    @Override
    public SupplierTransportEntity setDefaultFieldBaseUpdate(SupplierTransportEntity supplierTransportEntity) throws RuntimeException {
        SupplierTransportEntity entity = (SupplierTransportEntity) setFieldDefaultEntityBase.setDefaultFieldBaseUpdate(supplierTransportEntity);
        return entity;
    }

    @Override
    public SupplierTransportEntity setDefaultFieldBaseDelete(SupplierTransportEntity supplierTransportEntity) throws RuntimeException {
        SupplierTransportEntity entity = (SupplierTransportEntity) setFieldDefaultEntityBase.setDefaultFieldBaseDelete(supplierTransportEntity);
        return entity;
    }

    @Override
    public SupplierTransportDomain setDefaultFieldBaseUpdated(SupplierTransportDomain domainTarget, SupplierTransportDomain domainSource) throws RuntimeException {
        SupplierTransportDomain domain = (SupplierTransportDomain) setFieldDefaultEntityBase.setDefaultFieldBaseUpdated(domainTarget, domainSource);
        return domain;
    }
}
