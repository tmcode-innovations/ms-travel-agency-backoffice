package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.defaultfield.account;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.account.ProformaInvoiceEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.core.SetFieldDefaultEntityBase;
import com.saas.tourismagency.admin.backoffice.domain.account.ProformaInvoiceDomain;
import dev.mm.javaee.core.repo.SetFieldDefaultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SetFieldDefaultProformaInvoice implements SetFieldDefaultEntity<ProformaInvoiceEntity, ProformaInvoiceDomain> {

    @Autowired
    private SetFieldDefaultEntityBase setFieldDefaultEntityBase;

    @Override
    public ProformaInvoiceEntity setDefaultFieldBaseCreate(ProformaInvoiceEntity entityParam) throws RuntimeException {
        ProformaInvoiceEntity entity = (ProformaInvoiceEntity) setFieldDefaultEntityBase.setDefaultFieldBaseCreate(entityParam);
        return entity;
    }

    @Override
    public ProformaInvoiceEntity setDefaultFieldBaseUpdate(ProformaInvoiceEntity entityParam) throws RuntimeException {
        ProformaInvoiceEntity entity = (ProformaInvoiceEntity) setFieldDefaultEntityBase.setDefaultFieldBaseUpdate(entityParam);
        return entity;
    }

    @Override
    public ProformaInvoiceEntity setDefaultFieldBaseDelete(ProformaInvoiceEntity entityParam) throws RuntimeException {
        ProformaInvoiceEntity entity = (ProformaInvoiceEntity) setFieldDefaultEntityBase.setDefaultFieldBaseDelete(entityParam);
        return entity;
    }

    @Override
    public ProformaInvoiceDomain setDefaultFieldBaseUpdated(ProformaInvoiceDomain domainTarget, ProformaInvoiceDomain domainSource) throws RuntimeException {
        ProformaInvoiceDomain domain = (ProformaInvoiceDomain) setFieldDefaultEntityBase.setDefaultFieldBaseUpdated(domainTarget, domainSource);
        return domain;
    }
}
