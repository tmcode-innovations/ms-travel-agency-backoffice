package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.defaultfield.account;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.account.CurrencyEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.core.SetFieldDefaultEntityBase;
import com.saas.tourismagency.admin.backoffice.domain.account.CurrencyDomain;
import dev.mm.javaee.core.repo.SetFieldDefaultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SetFieldDefaultCurrency implements SetFieldDefaultEntity<CurrencyEntity, CurrencyDomain> {

    @Autowired
    private SetFieldDefaultEntityBase setFieldDefaultEntityBase;

    @Override
    public CurrencyEntity setDefaultFieldBaseCreate(CurrencyEntity entityParam) throws RuntimeException {
        CurrencyEntity entity = (CurrencyEntity) setFieldDefaultEntityBase.setDefaultFieldBaseCreate(entityParam);
        return entity;
    }

    @Override
    public CurrencyEntity setDefaultFieldBaseUpdate(CurrencyEntity entityParam) throws RuntimeException {
        CurrencyEntity entity = (CurrencyEntity) setFieldDefaultEntityBase.setDefaultFieldBaseUpdate(entityParam);
        return entity;
    }

    @Override
    public CurrencyEntity setDefaultFieldBaseDelete(CurrencyEntity entityParam) throws RuntimeException {
        CurrencyEntity entity = (CurrencyEntity) setFieldDefaultEntityBase.setDefaultFieldBaseDelete(entityParam);
        return entity;
    }

    @Override
    public CurrencyDomain setDefaultFieldBaseUpdated(CurrencyDomain domainTarget, CurrencyDomain domainSource) throws RuntimeException {
        CurrencyDomain domain = (CurrencyDomain) setFieldDefaultEntityBase.setDefaultFieldBaseUpdated(domainTarget, domainSource);
        return domain;
    }
}
