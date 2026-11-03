package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.defaultfield.maintenance;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.maintenance.DocumentTypeEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.core.SetFieldDefaultEntityBase;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.DocumentTypeDomain;
import dev.mm.javaee.core.repo.SetFieldDefaultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SetFieldDefaultDocumentType implements SetFieldDefaultEntity<DocumentTypeEntity, DocumentTypeDomain> {

    @Autowired
    private SetFieldDefaultEntityBase setFieldDefaultEntityBase;

    @Override
    public DocumentTypeEntity setDefaultFieldBaseCreate(DocumentTypeEntity documentTypeEntity) throws RuntimeException {
        DocumentTypeEntity entity = (DocumentTypeEntity) setFieldDefaultEntityBase.setDefaultFieldBaseCreate(documentTypeEntity);
        return entity;
    }

    @Override
    public DocumentTypeEntity setDefaultFieldBaseUpdate(DocumentTypeEntity documentTypeEntity) throws RuntimeException {
        DocumentTypeEntity entity = (DocumentTypeEntity) setFieldDefaultEntityBase.setDefaultFieldBaseUpdate(documentTypeEntity);
        return entity;
    }

    @Override
    public DocumentTypeEntity setDefaultFieldBaseDelete(DocumentTypeEntity documentTypeEntity) throws RuntimeException {
        DocumentTypeEntity entity = (DocumentTypeEntity) setFieldDefaultEntityBase.setDefaultFieldBaseDelete(documentTypeEntity);
        return entity;
    }

    @Override
    public DocumentTypeDomain setDefaultFieldBaseUpdated(DocumentTypeDomain domainTarget, DocumentTypeDomain domainSource) throws RuntimeException {
        DocumentTypeDomain domain = (DocumentTypeDomain) setFieldDefaultEntityBase.setDefaultFieldBaseUpdated(domainTarget, domainSource);
        return domain;
    }
}
