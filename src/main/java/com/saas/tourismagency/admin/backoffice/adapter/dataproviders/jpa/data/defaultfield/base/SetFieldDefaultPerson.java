package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.defaultfield.base;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.base.PersonEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.core.SetFieldDefaultEntityBase;
import com.saas.tourismagency.admin.backoffice.domain.base.PersonDomain;
import dev.mm.javaee.core.repo.SetFieldDefaultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SetFieldDefaultPerson implements SetFieldDefaultEntity<PersonEntity, PersonDomain> {

    @Autowired
    private SetFieldDefaultEntityBase setFieldDefaultEntityBase;

    @Override
    public PersonEntity setDefaultFieldBaseCreate(PersonEntity countryEntity) throws RuntimeException {
        PersonEntity entity = (PersonEntity) setFieldDefaultEntityBase.setDefaultFieldBaseCreate(countryEntity);
        return entity;
    }

    @Override
    public PersonEntity setDefaultFieldBaseUpdate(PersonEntity countryEntity) throws RuntimeException {
        PersonEntity entity = (PersonEntity) setFieldDefaultEntityBase.setDefaultFieldBaseUpdate(countryEntity);
        return entity;
    }

    @Override
    public PersonEntity setDefaultFieldBaseDelete(PersonEntity countryEntity) throws RuntimeException {
        PersonEntity entity = (PersonEntity) setFieldDefaultEntityBase.setDefaultFieldBaseDelete(countryEntity);
        return entity;
    }

    @Override
    public PersonDomain setDefaultFieldBaseUpdated(PersonDomain domainTarget, PersonDomain domainSource) throws RuntimeException {
        PersonDomain domain = (PersonDomain) setFieldDefaultEntityBase.setDefaultFieldBaseUpdated(domainTarget, domainSource);
        return domain;
    }
}
