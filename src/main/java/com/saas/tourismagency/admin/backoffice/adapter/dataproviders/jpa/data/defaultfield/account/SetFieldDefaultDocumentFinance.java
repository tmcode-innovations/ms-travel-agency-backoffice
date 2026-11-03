package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.defaultfield.account;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.account.DocumentFinanceEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.core.SetFieldDefaultEntityBase;
import com.saas.tourismagency.admin.backoffice.domain.account.DocumentFinanceDomain;
import dev.mm.javaee.core.repo.SetFieldDefaultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SetFieldDefaultDocumentFinance implements SetFieldDefaultEntity<DocumentFinanceEntity, DocumentFinanceDomain> {

    @Autowired
    private SetFieldDefaultEntityBase setFieldDefaultEntityBase;

    @Override
    public DocumentFinanceEntity setDefaultFieldBaseCreate(DocumentFinanceEntity entityParam) throws RuntimeException {
        DocumentFinanceEntity entity = (DocumentFinanceEntity) setFieldDefaultEntityBase.setDefaultFieldBaseCreate(entityParam);
        return entity;
    }

    @Override
    public DocumentFinanceEntity setDefaultFieldBaseUpdate(DocumentFinanceEntity entityParam) throws RuntimeException {
        DocumentFinanceEntity entity = (DocumentFinanceEntity) setFieldDefaultEntityBase.setDefaultFieldBaseUpdate(entityParam);
        return entity;
    }

    @Override
    public DocumentFinanceEntity setDefaultFieldBaseDelete(DocumentFinanceEntity entityParam) throws RuntimeException {
        DocumentFinanceEntity entity = (DocumentFinanceEntity) setFieldDefaultEntityBase.setDefaultFieldBaseDelete(entityParam);
        return entity;
    }

    @Override
    public DocumentFinanceDomain setDefaultFieldBaseUpdated(DocumentFinanceDomain domainTarget, DocumentFinanceDomain domainSource) throws RuntimeException {
        DocumentFinanceDomain domain = (DocumentFinanceDomain) setFieldDefaultEntityBase.setDefaultFieldBaseUpdated(domainTarget, domainSource);
        return domain;
    }
}
