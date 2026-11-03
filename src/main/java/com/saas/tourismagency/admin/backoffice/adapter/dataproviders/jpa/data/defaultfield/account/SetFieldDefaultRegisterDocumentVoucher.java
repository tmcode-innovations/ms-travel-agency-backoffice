package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.defaultfield.account;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.account.RegisterDocumentVoucherEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.core.SetFieldDefaultEntityBase;
import com.saas.tourismagency.admin.backoffice.domain.account.RegisterDocumentVoucherDomain;
import dev.mm.javaee.core.repo.SetFieldDefaultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SetFieldDefaultRegisterDocumentVoucher implements SetFieldDefaultEntity<RegisterDocumentVoucherEntity, RegisterDocumentVoucherDomain> {

    @Autowired
    private SetFieldDefaultEntityBase setFieldDefaultEntityBase;

    @Override
    public RegisterDocumentVoucherEntity setDefaultFieldBaseCreate(RegisterDocumentVoucherEntity entityParam) throws RuntimeException {
        RegisterDocumentVoucherEntity entity = (RegisterDocumentVoucherEntity) setFieldDefaultEntityBase.setDefaultFieldBaseCreate(entityParam);
        return entity;
    }

    @Override
    public RegisterDocumentVoucherEntity setDefaultFieldBaseUpdate(RegisterDocumentVoucherEntity entityParam) throws RuntimeException {
        RegisterDocumentVoucherEntity entity = (RegisterDocumentVoucherEntity) setFieldDefaultEntityBase.setDefaultFieldBaseUpdate(entityParam);
        return entity;
    }

    @Override
    public RegisterDocumentVoucherEntity setDefaultFieldBaseDelete(RegisterDocumentVoucherEntity entityParam) throws RuntimeException {
        RegisterDocumentVoucherEntity entity = (RegisterDocumentVoucherEntity) setFieldDefaultEntityBase.setDefaultFieldBaseDelete(entityParam);
        return entity;
    }

    @Override
    public RegisterDocumentVoucherDomain setDefaultFieldBaseUpdated(RegisterDocumentVoucherDomain domainTarget, RegisterDocumentVoucherDomain domainSource) throws RuntimeException {
        RegisterDocumentVoucherDomain domain = (RegisterDocumentVoucherDomain) setFieldDefaultEntityBase.setDefaultFieldBaseUpdated(domainTarget, domainSource);
        return domain;
    }
}
