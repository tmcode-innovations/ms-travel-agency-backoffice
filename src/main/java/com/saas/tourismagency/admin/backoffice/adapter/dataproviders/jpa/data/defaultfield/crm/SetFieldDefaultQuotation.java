package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.defaultfield.crm;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.crm.QuotationEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.core.SetFieldDefaultEntityBase;
import com.saas.tourismagency.admin.backoffice.domain.crm.QuotationDomain;
import dev.mm.javaee.core.repo.SetFieldDefaultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SetFieldDefaultQuotation implements SetFieldDefaultEntity<QuotationEntity, QuotationDomain> {

    @Autowired
    private SetFieldDefaultEntityBase setFieldDefaultEntityBase;

    @Override
    public QuotationEntity setDefaultFieldBaseCreate(QuotationEntity quotationEntity) throws RuntimeException {
        QuotationEntity entity = (QuotationEntity) setFieldDefaultEntityBase.setDefaultFieldBaseCreate(quotationEntity);
        return entity;
    }

    @Override
    public QuotationEntity setDefaultFieldBaseUpdate(QuotationEntity quotationEntity) throws RuntimeException {
        QuotationEntity entity = (QuotationEntity) setFieldDefaultEntityBase.setDefaultFieldBaseUpdate(quotationEntity);
        return entity;
    }

    @Override
    public QuotationEntity setDefaultFieldBaseDelete(QuotationEntity quotationEntity) throws RuntimeException {
        QuotationEntity entity = (QuotationEntity) setFieldDefaultEntityBase.setDefaultFieldBaseDelete(quotationEntity);
        return entity;
    }

    @Override
    public QuotationDomain setDefaultFieldBaseUpdated(QuotationDomain domainTarget, QuotationDomain domainSource) throws RuntimeException {
        QuotationDomain domain = (QuotationDomain) setFieldDefaultEntityBase.setDefaultFieldBaseUpdated(domainTarget, domainSource);
        return domain;
    }
}
