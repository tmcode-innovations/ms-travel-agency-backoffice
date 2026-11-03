package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.defaultfield.maintenance;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.maintenance.BankEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.core.SetFieldDefaultEntityBase;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.BankDomain;
import dev.mm.javaee.core.repo.SetFieldDefaultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SetFieldDefaultBank implements SetFieldDefaultEntity<BankEntity, BankDomain> {

    @Autowired
    private SetFieldDefaultEntityBase setFieldDefaultEntityBase;

    @Override
    public BankEntity setDefaultFieldBaseCreate(BankEntity bankEntity) throws RuntimeException {
        BankEntity entity = (BankEntity) setFieldDefaultEntityBase.setDefaultFieldBaseCreate(bankEntity);
        return entity;
    }

    @Override
    public BankEntity setDefaultFieldBaseUpdate(BankEntity bankEntity) throws RuntimeException {
        BankEntity entity = (BankEntity) setFieldDefaultEntityBase.setDefaultFieldBaseUpdate(bankEntity);
        return entity;
    }

    @Override
    public BankEntity setDefaultFieldBaseDelete(BankEntity bankEntity) throws RuntimeException {
        BankEntity entity = (BankEntity) setFieldDefaultEntityBase.setDefaultFieldBaseDelete(bankEntity);
        return entity;
    }

    @Override
    public BankDomain setDefaultFieldBaseUpdated(BankDomain domainTarget, BankDomain domainSource) throws RuntimeException {
        BankDomain domain = (BankDomain) setFieldDefaultEntityBase.setDefaultFieldBaseUpdated(domainTarget, domainSource);
        return domain;
    }
}
