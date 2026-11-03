package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.defaultfield.account;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.account.InvoiceCommissionEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.core.SetFieldDefaultEntityBase;
import com.saas.tourismagency.admin.backoffice.domain.account.InvoiceCommissionDomain;
import dev.mm.javaee.core.repo.SetFieldDefaultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SetFieldDefaultInvoiceCommission implements SetFieldDefaultEntity<InvoiceCommissionEntity, InvoiceCommissionDomain> {

    @Autowired
    private SetFieldDefaultEntityBase setFieldDefaultEntityBase;

    @Override
    public InvoiceCommissionEntity setDefaultFieldBaseCreate(InvoiceCommissionEntity entityParam) throws RuntimeException {
        InvoiceCommissionEntity entity = (InvoiceCommissionEntity) setFieldDefaultEntityBase.setDefaultFieldBaseCreate(entityParam);
        return entity;
    }

    @Override
    public InvoiceCommissionEntity setDefaultFieldBaseUpdate(InvoiceCommissionEntity entityParam) throws RuntimeException {
        InvoiceCommissionEntity entity = (InvoiceCommissionEntity) setFieldDefaultEntityBase.setDefaultFieldBaseUpdate(entityParam);
        return entity;
    }

    @Override
    public InvoiceCommissionEntity setDefaultFieldBaseDelete(InvoiceCommissionEntity entityParam) throws RuntimeException {
        InvoiceCommissionEntity entity = (InvoiceCommissionEntity) setFieldDefaultEntityBase.setDefaultFieldBaseDelete(entityParam);
        return entity;
    }

    @Override
    public InvoiceCommissionDomain setDefaultFieldBaseUpdated(InvoiceCommissionDomain domainTarget, InvoiceCommissionDomain domainSource) throws RuntimeException {
        InvoiceCommissionDomain domain = (InvoiceCommissionDomain) setFieldDefaultEntityBase.setDefaultFieldBaseUpdated(domainTarget, domainSource);
        return domain;
    }
}
