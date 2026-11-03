package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.defaultfield.core;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.core.PersistConsecutiveNumberEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.core.SetFieldDefaultEntityCoreBase;
import dev.mm.javaee.core.repo.SetFieldDefaultEntityCore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SetFieldDefaultPersistConsecutive implements SetFieldDefaultEntityCore<PersistConsecutiveNumberEntity> {

    @Autowired
    private SetFieldDefaultEntityCoreBase setFieldDefaultEntityCoreBase;

    @Override
    public PersistConsecutiveNumberEntity setDefaultFieldBaseCreate(PersistConsecutiveNumberEntity entityParam) throws RuntimeException {
        PersistConsecutiveNumberEntity entity = (PersistConsecutiveNumberEntity) setFieldDefaultEntityCoreBase.setDefaultFieldBaseCreate(entityParam);
        return entity;
    }

    @Override
    public PersistConsecutiveNumberEntity setDefaultFieldBaseUpdate(PersistConsecutiveNumberEntity entityParam) throws RuntimeException {
        PersistConsecutiveNumberEntity entity = (PersistConsecutiveNumberEntity) setFieldDefaultEntityCoreBase.setDefaultFieldBaseUpdate(entityParam);
        return entity;
    }

    @Override
    public PersistConsecutiveNumberEntity setDefaultFieldBaseDelete(PersistConsecutiveNumberEntity entityParam) throws RuntimeException {
        PersistConsecutiveNumberEntity entity = (PersistConsecutiveNumberEntity) setFieldDefaultEntityCoreBase.setDefaultFieldBaseDelete(entityParam);
        return entity;
    }

}
