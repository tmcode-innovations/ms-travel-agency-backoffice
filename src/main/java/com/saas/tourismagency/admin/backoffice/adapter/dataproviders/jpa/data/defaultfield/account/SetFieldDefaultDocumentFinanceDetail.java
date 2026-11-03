package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.defaultfield.account;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.account.DocumentFinanceDetailEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.core.SetFieldDefaultEntityBase;
import com.saas.tourismagency.admin.backoffice.domain.account.DocumentFinanceDetailDomain;
import dev.mm.javaee.core.repo.SetFieldDefaultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SetFieldDefaultDocumentFinanceDetail implements SetFieldDefaultEntity<DocumentFinanceDetailEntity, DocumentFinanceDetailDomain> {

    @Autowired
    private SetFieldDefaultEntityBase setFieldDefaultEntityBase;

    @Override
    public DocumentFinanceDetailEntity setDefaultFieldBaseCreate(DocumentFinanceDetailEntity entityParam) throws RuntimeException {
        DocumentFinanceDetailEntity entity = (DocumentFinanceDetailEntity) setFieldDefaultEntityBase.setDefaultFieldBaseCreate(entityParam);
        return entity;
    }

    @Override
    public DocumentFinanceDetailEntity setDefaultFieldBaseUpdate(DocumentFinanceDetailEntity entityParam) throws RuntimeException {
        DocumentFinanceDetailEntity entity = (DocumentFinanceDetailEntity) setFieldDefaultEntityBase.setDefaultFieldBaseUpdate(entityParam);
        return entity;
    }

    @Override
    public DocumentFinanceDetailEntity setDefaultFieldBaseDelete(DocumentFinanceDetailEntity entityParam) throws RuntimeException {
        DocumentFinanceDetailEntity entity = (DocumentFinanceDetailEntity) setFieldDefaultEntityBase.setDefaultFieldBaseDelete(entityParam);
        return entity;
    }

    @Override
    public DocumentFinanceDetailDomain setDefaultFieldBaseUpdated(DocumentFinanceDetailDomain domainTarget, DocumentFinanceDetailDomain domainSource) throws RuntimeException {
        DocumentFinanceDetailDomain domain = (DocumentFinanceDetailDomain) setFieldDefaultEntityBase.setDefaultFieldBaseUpdated(domainTarget, domainSource);
        return domain;
    }
}
