package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.defaultfield.maintenance;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.maintenance.DocumentAccountTypeEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.core.SetFieldDefaultEntityBase;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.DocumentAccountTypeDomain;
import dev.mm.javaee.core.repo.SetFieldDefaultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SetFieldDefaultDocumentAccountType implements SetFieldDefaultEntity<DocumentAccountTypeEntity, DocumentAccountTypeDomain> {

    @Autowired
    private SetFieldDefaultEntityBase setFieldDefaultEntityBase;

    @Override
    public DocumentAccountTypeEntity setDefaultFieldBaseCreate(DocumentAccountTypeEntity documentAccountTypeEntity) throws RuntimeException {
        DocumentAccountTypeEntity entity = (DocumentAccountTypeEntity) setFieldDefaultEntityBase.setDefaultFieldBaseCreate(documentAccountTypeEntity);
        return entity;
    }

    @Override
    public DocumentAccountTypeEntity setDefaultFieldBaseUpdate(DocumentAccountTypeEntity documentAccountTypeEntity) throws RuntimeException {
        DocumentAccountTypeEntity entity = (DocumentAccountTypeEntity) setFieldDefaultEntityBase.setDefaultFieldBaseUpdate(documentAccountTypeEntity);
        return entity;
    }

    @Override
    public DocumentAccountTypeEntity setDefaultFieldBaseDelete(DocumentAccountTypeEntity documentAccountTypeEntity) throws RuntimeException {
        DocumentAccountTypeEntity entity = (DocumentAccountTypeEntity) setFieldDefaultEntityBase.setDefaultFieldBaseDelete(documentAccountTypeEntity);
        return entity;
    }

    @Override
    public DocumentAccountTypeDomain setDefaultFieldBaseUpdated(DocumentAccountTypeDomain domainTarget, DocumentAccountTypeDomain domainSource) throws RuntimeException {
        DocumentAccountTypeDomain domain = (DocumentAccountTypeDomain) setFieldDefaultEntityBase.setDefaultFieldBaseUpdated(domainTarget, domainSource);
        return domain;
    }
}
