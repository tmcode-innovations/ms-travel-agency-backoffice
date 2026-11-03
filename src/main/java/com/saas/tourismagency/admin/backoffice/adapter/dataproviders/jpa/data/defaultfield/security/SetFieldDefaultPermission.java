package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.defaultfield.security;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.security.PermissionEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.core.SetFieldDefaultEntityBase;
import com.saas.tourismagency.admin.backoffice.domain.security.PermissionDomain;
import dev.mm.javaee.core.repo.SetFieldDefaultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SetFieldDefaultPermission implements SetFieldDefaultEntity<PermissionEntity, PermissionDomain> {

    @Autowired
    private SetFieldDefaultEntityBase setFieldDefaultEntityBase;

    @Override
    public PermissionEntity setDefaultFieldBaseCreate(PermissionEntity permissionEntity) throws RuntimeException {
        PermissionEntity entity = (PermissionEntity) setFieldDefaultEntityBase.setDefaultFieldBaseCreate(permissionEntity);
        return entity;
    }

    @Override
    public PermissionEntity setDefaultFieldBaseUpdate(PermissionEntity permissionEntity) throws RuntimeException {
        PermissionEntity entity = (PermissionEntity) setFieldDefaultEntityBase.setDefaultFieldBaseUpdate(permissionEntity);
        return entity;
    }

    @Override
    public PermissionEntity setDefaultFieldBaseDelete(PermissionEntity permissionEntity) throws RuntimeException {
        PermissionEntity entity = (PermissionEntity) setFieldDefaultEntityBase.setDefaultFieldBaseDelete(permissionEntity);
        return entity;
    }

    @Override
    public PermissionDomain setDefaultFieldBaseUpdated(PermissionDomain domainTarget, PermissionDomain domainSource) throws RuntimeException {
        PermissionDomain domain = (PermissionDomain) setFieldDefaultEntityBase.setDefaultFieldBaseUpdated(domainTarget, domainSource);
        return domain;
    }
}
