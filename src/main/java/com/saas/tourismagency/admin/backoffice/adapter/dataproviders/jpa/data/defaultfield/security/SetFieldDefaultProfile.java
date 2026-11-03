package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.defaultfield.security;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.security.ProfileEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.core.SetFieldDefaultEntityBase;
import com.saas.tourismagency.admin.backoffice.domain.security.ProfileDomain;
import dev.mm.javaee.core.repo.SetFieldDefaultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SetFieldDefaultProfile implements SetFieldDefaultEntity<ProfileEntity, ProfileDomain> {

    @Autowired
    private SetFieldDefaultEntityBase setFieldDefaultEntityBase;

    @Override
    public ProfileEntity setDefaultFieldBaseCreate(ProfileEntity profileEntity) throws RuntimeException {
        ProfileEntity entity = (ProfileEntity) setFieldDefaultEntityBase.setDefaultFieldBaseCreate(profileEntity);
        return entity;
    }

    @Override
    public ProfileEntity setDefaultFieldBaseUpdate(ProfileEntity profileEntity) throws RuntimeException {
        ProfileEntity entity = (ProfileEntity) setFieldDefaultEntityBase.setDefaultFieldBaseUpdate(profileEntity);
        return entity;
    }

    @Override
    public ProfileEntity setDefaultFieldBaseDelete(ProfileEntity profileEntity) throws RuntimeException {
        ProfileEntity entity = (ProfileEntity) setFieldDefaultEntityBase.setDefaultFieldBaseDelete(profileEntity);
        return entity;
    }

    @Override
    public ProfileDomain setDefaultFieldBaseUpdated(ProfileDomain domainTarget, ProfileDomain domainSource) throws RuntimeException {
        ProfileDomain domain = (ProfileDomain) setFieldDefaultEntityBase.setDefaultFieldBaseUpdated(domainTarget, domainSource);
        return domain;
    }
}
