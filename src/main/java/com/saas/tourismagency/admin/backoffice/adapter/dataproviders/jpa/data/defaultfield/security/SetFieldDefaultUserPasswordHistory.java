package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.defaultfield.security;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.security.UserPasswordHistoryEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.core.SetFieldDefaultEntityBase;
import com.saas.tourismagency.admin.backoffice.domain.security.UserPasswordHistoryDomain;
import dev.mm.javaee.core.repo.SetFieldDefaultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SetFieldDefaultUserPasswordHistory implements SetFieldDefaultEntity<UserPasswordHistoryEntity, UserPasswordHistoryDomain> {

    @Autowired
    private SetFieldDefaultEntityBase setFieldDefaultEntityBase;

    @Override
    public UserPasswordHistoryEntity setDefaultFieldBaseCreate(UserPasswordHistoryEntity userPasswordHistoryEntity) throws RuntimeException {
        UserPasswordHistoryEntity entity = (UserPasswordHistoryEntity) setFieldDefaultEntityBase.setDefaultFieldBaseCreate(userPasswordHistoryEntity);
        return entity;
    }

    @Override
    public UserPasswordHistoryEntity setDefaultFieldBaseUpdate(UserPasswordHistoryEntity userPasswordHistoryEntity) throws RuntimeException {
        UserPasswordHistoryEntity entity = (UserPasswordHistoryEntity) setFieldDefaultEntityBase.setDefaultFieldBaseUpdate(userPasswordHistoryEntity);
        return entity;
    }

    @Override
    public UserPasswordHistoryEntity setDefaultFieldBaseDelete(UserPasswordHistoryEntity userPasswordHistoryEntity) throws RuntimeException {
        UserPasswordHistoryEntity entity = (UserPasswordHistoryEntity) setFieldDefaultEntityBase.setDefaultFieldBaseDelete(userPasswordHistoryEntity);
        return entity;
    }

    @Override
    public UserPasswordHistoryDomain setDefaultFieldBaseUpdated(UserPasswordHistoryDomain domainTarget, UserPasswordHistoryDomain domainSource) throws RuntimeException {
        UserPasswordHistoryDomain domain = (UserPasswordHistoryDomain) setFieldDefaultEntityBase.setDefaultFieldBaseUpdated(domainTarget, domainSource);
        return domain;
    }
}
