package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.defaultfield.account;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.account.InvoiceEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.core.SetFieldDefaultEntityBase;
import com.saas.tourismagency.admin.backoffice.domain.account.InvoiceDomain;
import dev.mm.javaee.core.repo.SetFieldDefaultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SetFieldDefaultInvoice implements SetFieldDefaultEntity<InvoiceEntity, InvoiceDomain> {

    @Autowired
    private SetFieldDefaultEntityBase setFieldDefaultEntityBase;

    @Override
    public InvoiceEntity setDefaultFieldBaseCreate(InvoiceEntity entityParam) throws RuntimeException {
        InvoiceEntity entity = (InvoiceEntity) setFieldDefaultEntityBase.setDefaultFieldBaseCreate(entityParam);
        return entity;
    }

    @Override
    public InvoiceEntity setDefaultFieldBaseUpdate(InvoiceEntity entityParam) throws RuntimeException {
        InvoiceEntity entity = (InvoiceEntity) setFieldDefaultEntityBase.setDefaultFieldBaseUpdate(entityParam);
        return entity;
    }

    @Override
    public InvoiceEntity setDefaultFieldBaseDelete(InvoiceEntity entityParam) throws RuntimeException {
        InvoiceEntity entity = (InvoiceEntity) setFieldDefaultEntityBase.setDefaultFieldBaseDelete(entityParam);
        return entity;
    }

    @Override
    public InvoiceDomain setDefaultFieldBaseUpdated(InvoiceDomain domainTarget, InvoiceDomain domainSource) throws RuntimeException {
        InvoiceDomain domain = (InvoiceDomain) setFieldDefaultEntityBase.setDefaultFieldBaseUpdated(domainTarget, domainSource);
        return domain;
    }
}
