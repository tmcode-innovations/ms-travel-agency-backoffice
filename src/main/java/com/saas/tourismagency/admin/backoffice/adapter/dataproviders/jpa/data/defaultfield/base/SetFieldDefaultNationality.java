package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.defaultfield.base;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.base.NationalityEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.core.SetFieldDefaultEntityBase;
import com.saas.tourismagency.admin.backoffice.domain.base.NationalityDomain;
import dev.mm.javaee.core.repo.SetFieldDefaultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SetFieldDefaultNationality implements SetFieldDefaultEntity<NationalityEntity, NationalityDomain> {

    @Autowired
    private SetFieldDefaultEntityBase setFieldDefaultEntityBase;

    @Override
    public NationalityEntity setDefaultFieldBaseCreate(NationalityEntity nationalityEntity) throws RuntimeException {
        NationalityEntity entity = (NationalityEntity) setFieldDefaultEntityBase.setDefaultFieldBaseCreate(nationalityEntity);
        return entity;
    }

    @Override
    public NationalityEntity setDefaultFieldBaseUpdate(NationalityEntity nationalityEntity) throws RuntimeException {
        NationalityEntity entity = (NationalityEntity) setFieldDefaultEntityBase.setDefaultFieldBaseUpdate(nationalityEntity);
        return entity;
    }

    @Override
    public NationalityEntity setDefaultFieldBaseDelete(NationalityEntity nationalityEntity) throws RuntimeException {
        NationalityEntity entity = (NationalityEntity) setFieldDefaultEntityBase.setDefaultFieldBaseDelete(nationalityEntity);
        return entity;
    }

    @Override
    public NationalityDomain setDefaultFieldBaseUpdated(NationalityDomain domainTarget, NationalityDomain domainSource) throws RuntimeException {
        NationalityDomain domain = (NationalityDomain) setFieldDefaultEntityBase.setDefaultFieldBaseUpdated(domainTarget, domainSource);
        return domain;
    }
}
