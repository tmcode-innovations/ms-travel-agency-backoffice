package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.defaultfield.crm;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.crm.FileQuotationEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.core.SetFieldDefaultEntityBase;
import com.saas.tourismagency.admin.backoffice.domain.crm.FileQuotationDomain;
import dev.mm.javaee.core.repo.SetFieldDefaultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SetFieldDefaultFileQuotation implements SetFieldDefaultEntity<FileQuotationEntity, FileQuotationDomain> {

    @Autowired
    private SetFieldDefaultEntityBase setFieldDefaultEntityBase;

    @Override
    public FileQuotationEntity setDefaultFieldBaseCreate(FileQuotationEntity fileQuotationEntity) throws RuntimeException {
        FileQuotationEntity entity = (FileQuotationEntity) setFieldDefaultEntityBase.setDefaultFieldBaseCreate(fileQuotationEntity);
        return entity;
    }

    @Override
    public FileQuotationEntity setDefaultFieldBaseUpdate(FileQuotationEntity fileQuotationEntity) throws RuntimeException {
        FileQuotationEntity entity = (FileQuotationEntity) setFieldDefaultEntityBase.setDefaultFieldBaseUpdate(fileQuotationEntity);
        return entity;
    }

    @Override
    public FileQuotationEntity setDefaultFieldBaseDelete(FileQuotationEntity fileQuotationEntity) throws RuntimeException {
        FileQuotationEntity entity = (FileQuotationEntity) setFieldDefaultEntityBase.setDefaultFieldBaseDelete(fileQuotationEntity);
        return entity;
    }

    @Override
    public FileQuotationDomain setDefaultFieldBaseUpdated(FileQuotationDomain domainTarget, FileQuotationDomain domainSource) throws RuntimeException {
        FileQuotationDomain domain = (FileQuotationDomain) setFieldDefaultEntityBase.setDefaultFieldBaseUpdated(domainTarget, domainSource);
        return domain;
    }
}
