package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.defaultfield.security;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.security.UserEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.core.SetFieldDefaultEntityBase;
import com.saas.tourismagency.admin.backoffice.domain.security.UserDomain;
import dev.mm.javaee.core.repo.SetFieldDefaultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SetFieldDefaultUser implements SetFieldDefaultEntity<UserEntity, UserDomain> {

    @Autowired
    private SetFieldDefaultEntityBase setFieldDefaultEntityBase;

    @Override
    public UserEntity setDefaultFieldBaseCreate(UserEntity userEntity) throws RuntimeException {
        UserEntity entity = (UserEntity) setFieldDefaultEntityBase.setDefaultFieldBaseCreate(userEntity);
        return entity;
    }

    @Override
    public UserEntity setDefaultFieldBaseUpdate(UserEntity userEntity) throws RuntimeException {
        UserEntity entity = (UserEntity) setFieldDefaultEntityBase.setDefaultFieldBaseUpdate(userEntity);
        return entity;
    }

    @Override
    public UserEntity setDefaultFieldBaseDelete(UserEntity userEntity) throws RuntimeException {
        UserEntity entity = (UserEntity) setFieldDefaultEntityBase.setDefaultFieldBaseDelete(userEntity);
        return entity;
    }

    @Override
    public UserDomain setDefaultFieldBaseUpdated(UserDomain domainTarget, UserDomain domainSource) throws RuntimeException {
        UserDomain domain = (UserDomain) setFieldDefaultEntityBase.setDefaultFieldBaseUpdated(domainTarget, domainSource);
        return domain;
    }
}
