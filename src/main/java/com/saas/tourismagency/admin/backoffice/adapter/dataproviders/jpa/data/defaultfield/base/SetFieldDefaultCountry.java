package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.defaultfield.base;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.base.CountryEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.core.SetFieldDefaultEntityBase;
import com.saas.tourismagency.admin.backoffice.domain.base.CountryDomain;
import dev.mm.javaee.core.repo.SetFieldDefaultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SetFieldDefaultCountry implements SetFieldDefaultEntity<CountryEntity, CountryDomain> {

    @Autowired
    private SetFieldDefaultEntityBase setFieldDefaultEntityBase;

    @Override
    public CountryEntity setDefaultFieldBaseCreate(CountryEntity countryEntity) throws RuntimeException {
        CountryEntity entity = (CountryEntity) setFieldDefaultEntityBase.setDefaultFieldBaseCreate(countryEntity);
        return entity;
    }

    @Override
    public CountryEntity setDefaultFieldBaseUpdate(CountryEntity countryEntity) throws RuntimeException {
        CountryEntity entity = (CountryEntity) setFieldDefaultEntityBase.setDefaultFieldBaseUpdate(countryEntity);
        return entity;
    }

    @Override
    public CountryEntity setDefaultFieldBaseDelete(CountryEntity countryEntity) throws RuntimeException {
        CountryEntity entity = (CountryEntity) setFieldDefaultEntityBase.setDefaultFieldBaseDelete(countryEntity);
        return entity;
    }

    @Override
    public CountryDomain setDefaultFieldBaseUpdated(CountryDomain domainTarget, CountryDomain domainSource) throws RuntimeException {
        CountryDomain domain = (CountryDomain) setFieldDefaultEntityBase.setDefaultFieldBaseUpdated(domainTarget, domainSource);
        return domain;
    }
}
