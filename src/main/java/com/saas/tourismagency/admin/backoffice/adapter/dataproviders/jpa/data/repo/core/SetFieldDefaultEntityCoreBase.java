package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.core;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.base.BaseEntity;
import dev.mm.javaee.core.repo.SetFieldDefaultEntityCore;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class SetFieldDefaultEntityCoreBase implements SetFieldDefaultEntityCore<BaseEntity> {
    @Override
    public BaseEntity setDefaultFieldBaseCreate(BaseEntity baseEntity) throws RuntimeException {
        baseEntity.setEnabled(true);
        baseEntity.setDeleted(false);
        baseEntity.setMigrated(false);
        baseEntity.setCreatedAt(LocalDateTime.now());
        return baseEntity;
    }

    @Override
    public BaseEntity setDefaultFieldBaseUpdate(BaseEntity baseEntity) throws RuntimeException {
        baseEntity.setUpdatedAt(LocalDateTime.now());
        return baseEntity;
    }

    @Override
    public BaseEntity setDefaultFieldBaseDelete(BaseEntity baseEntity) throws RuntimeException {
        baseEntity.setDeletedAt(LocalDateTime.now());
        baseEntity.setDeleted(true);
        return baseEntity;
    }
}
