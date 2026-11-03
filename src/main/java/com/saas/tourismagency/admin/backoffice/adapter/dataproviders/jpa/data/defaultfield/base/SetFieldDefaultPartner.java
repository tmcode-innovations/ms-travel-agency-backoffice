package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.defaultfield.base;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.base.PartnerEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.core.SetFieldDefaultEntityBase;
import com.saas.tourismagency.admin.backoffice.domain.base.PartnerDomain;
import dev.mm.javaee.core.repo.SetFieldDefaultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SetFieldDefaultPartner implements SetFieldDefaultEntity<PartnerEntity, PartnerDomain> {

    @Autowired
    private SetFieldDefaultEntityBase setFieldDefaultEntityBase;

    @Override
    public PartnerEntity setDefaultFieldBaseCreate(PartnerEntity countryEntity) throws RuntimeException {
        PartnerEntity entity = (PartnerEntity) setFieldDefaultEntityBase.setDefaultFieldBaseCreate(countryEntity);
        return entity;
    }

    @Override
    public PartnerEntity setDefaultFieldBaseUpdate(PartnerEntity countryEntity) throws RuntimeException {
        PartnerEntity entity = (PartnerEntity) setFieldDefaultEntityBase.setDefaultFieldBaseUpdate(countryEntity);
        return entity;
    }

    @Override
    public PartnerEntity setDefaultFieldBaseDelete(PartnerEntity countryEntity) throws RuntimeException {
        PartnerEntity entity = (PartnerEntity) setFieldDefaultEntityBase.setDefaultFieldBaseDelete(countryEntity);
        return entity;
    }

    @Override
    public PartnerDomain setDefaultFieldBaseUpdated(PartnerDomain domainTarget, PartnerDomain domainSource) throws RuntimeException {
        PartnerDomain domain = (PartnerDomain) setFieldDefaultEntityBase.setDefaultFieldBaseUpdated(domainTarget, domainSource);
        return domain;
    }
}
