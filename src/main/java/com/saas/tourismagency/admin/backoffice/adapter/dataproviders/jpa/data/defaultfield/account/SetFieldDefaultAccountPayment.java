package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.defaultfield.account;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.account.AccountPaymentEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.core.SetFieldDefaultEntityBase;
import com.saas.tourismagency.admin.backoffice.domain.account.AccountPaymentDomain;
import dev.mm.javaee.core.repo.SetFieldDefaultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SetFieldDefaultAccountPayment implements SetFieldDefaultEntity<AccountPaymentEntity, AccountPaymentDomain> {

    @Autowired
    private SetFieldDefaultEntityBase setFieldDefaultEntityBase;

    @Override
    public AccountPaymentEntity setDefaultFieldBaseCreate(AccountPaymentEntity entityParam) throws RuntimeException {
        AccountPaymentEntity entity = (AccountPaymentEntity) setFieldDefaultEntityBase.setDefaultFieldBaseCreate(entityParam);
        return entity;
    }

    @Override
    public AccountPaymentEntity setDefaultFieldBaseUpdate(AccountPaymentEntity entityParam) throws RuntimeException {
        AccountPaymentEntity entity = (AccountPaymentEntity) setFieldDefaultEntityBase.setDefaultFieldBaseUpdate(entityParam);
        return entity;
    }

    @Override
    public AccountPaymentEntity setDefaultFieldBaseDelete(AccountPaymentEntity entityParam) throws RuntimeException {
        AccountPaymentEntity entity = (AccountPaymentEntity) setFieldDefaultEntityBase.setDefaultFieldBaseDelete(entityParam);
        return entity;
    }

    @Override
    public AccountPaymentDomain setDefaultFieldBaseUpdated(AccountPaymentDomain domainTarget, AccountPaymentDomain domainSource) throws RuntimeException {
        AccountPaymentDomain domain = (AccountPaymentDomain) setFieldDefaultEntityBase.setDefaultFieldBaseUpdated(domainTarget, domainSource);
        return domain;
    }
}
