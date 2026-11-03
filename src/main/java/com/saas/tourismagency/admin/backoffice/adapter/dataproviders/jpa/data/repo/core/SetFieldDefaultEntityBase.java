package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.core;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.base.BaseEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.base.area.AreaConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.security.user.UserConverter;
import com.saas.tourismagency.admin.backoffice.core.context.UserResolver;
import com.saas.tourismagency.admin.backoffice.domain.security.UserDomain;
import dev.mm.javaee.core.domain.BaseDomain;
import dev.mm.javaee.core.repo.SetFieldDefaultEntity;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class SetFieldDefaultEntityBase implements SetFieldDefaultEntity<BaseEntity, BaseDomain> {

    @Autowired
    private UserConverter userConverter;

    @Override
    public BaseEntity setDefaultFieldBaseCreate(BaseEntity baseEntity) throws RuntimeException {
        baseEntity.setEnabled(true);
        baseEntity.setDeleted(false);
        baseEntity.setMigrated(false);
        baseEntity.setCreatedAt(LocalDateTime.now());
        if (UserResolver.resolve() != null){
            UserDomain userDomain = UserResolver.resolveUser();
            if (userDomain != null){
               baseEntity.setCreatedBy(userConverter.toEntity(userDomain));
            }
        }
        return baseEntity;
    }

    @Override
    public BaseEntity setDefaultFieldBaseUpdate(BaseEntity baseEntity) throws RuntimeException {
        baseEntity.setUpdatedAt(LocalDateTime.now());
        if (UserResolver.resolve() != null){
            UserDomain userDomain = UserResolver.resolveUser();
            if (userDomain != null){
                baseEntity.setUpdatedBy(userConverter.toEntity(userDomain));
            }
        }
        return baseEntity;
    }

    @Override
    public BaseEntity setDefaultFieldBaseDelete(BaseEntity baseEntity) throws RuntimeException {
        baseEntity.setDeletedAt(LocalDateTime.now());
        baseEntity.setDeleted(true);
        if (UserResolver.resolve() != null){
            UserDomain userDomain = UserResolver.resolveUser();
            if (userDomain != null){
                baseEntity.setDeletedBy(userConverter.toEntity(userDomain));
            }
        }
        return baseEntity;
    }

    @Override
    public BaseDomain setDefaultFieldBaseUpdated(BaseDomain domainTarget, BaseDomain domainSource) throws RuntimeException {
        domainTarget.setEnabled(domainSource.getEnabled());
        domainTarget.setDeleted(domainSource.getDeleted());
        domainTarget.setMigrated(domainSource.getMigrated());
        domainTarget.setCreatedAt(domainSource.getCreatedAt());
        domainTarget.setUpdatedAt(domainSource.getUpdatedAt());
        domainTarget.setDeletedAt(domainSource.getDeletedAt());
        domainTarget.setCreatedBy(domainSource.getCreatedBy());
        domainTarget.setUpdatedBy(domainSource.getUpdatedBy());
        domainTarget.setDeletedBy(domainSource.getDeletedBy());
        domainTarget.setTenant(domainSource.getTenant());
        return domainTarget;
    }
}
