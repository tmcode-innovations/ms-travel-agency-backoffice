package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.defaultfield.security;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.security.UserPasswordEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.core.SetFieldDefaultEntityBase;
import com.saas.tourismagency.admin.backoffice.domain.security.UserPasswordDomain;
import dev.mm.javaee.core.repo.SetFieldDefaultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SetFieldDefaultUserPassword implements SetFieldDefaultEntity<UserPasswordEntity, UserPasswordDomain> {

    @Autowired
    private SetFieldDefaultEntityBase setFieldDefaultEntityBase;

    @Override
    public UserPasswordEntity setDefaultFieldBaseCreate(UserPasswordEntity userPasswordEntity) throws RuntimeException {
        UserPasswordEntity entity = (UserPasswordEntity) setFieldDefaultEntityBase.setDefaultFieldBaseCreate(userPasswordEntity);
        return entity;
    }

    @Override
    public UserPasswordEntity setDefaultFieldBaseUpdate(UserPasswordEntity userPasswordEntity) throws RuntimeException {
        UserPasswordEntity entity = (UserPasswordEntity) setFieldDefaultEntityBase.setDefaultFieldBaseUpdate(userPasswordEntity);
        return entity;
    }

    @Override
    public UserPasswordEntity setDefaultFieldBaseDelete(UserPasswordEntity userPasswordEntity) throws RuntimeException {
        UserPasswordEntity entity = (UserPasswordEntity) setFieldDefaultEntityBase.setDefaultFieldBaseDelete(userPasswordEntity);
        return entity;
    }

    @Override
    public UserPasswordDomain setDefaultFieldBaseUpdated(UserPasswordDomain domainTarget, UserPasswordDomain domainSource) throws RuntimeException {
        UserPasswordDomain domain = (UserPasswordDomain) setFieldDefaultEntityBase.setDefaultFieldBaseUpdated(domainTarget, domainSource);
        return domain;
    }
}
