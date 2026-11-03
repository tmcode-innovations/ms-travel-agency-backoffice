package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.defaultfield.maintenance;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.maintenance.EntryEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.core.SetFieldDefaultEntityBase;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.EntryDomain;
import dev.mm.javaee.core.repo.SetFieldDefaultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SetFieldDefaultEntry implements SetFieldDefaultEntity<EntryEntity, EntryDomain> {

    @Autowired
    private SetFieldDefaultEntityBase setFieldDefaultEntityBase;

    @Override
    public EntryEntity setDefaultFieldBaseCreate(EntryEntity entryEntity) throws RuntimeException {
        EntryEntity entity = (EntryEntity) setFieldDefaultEntityBase.setDefaultFieldBaseCreate(entryEntity);
        return entity;
    }

    @Override
    public EntryEntity setDefaultFieldBaseUpdate(EntryEntity entryEntity) throws RuntimeException {
        EntryEntity entity = (EntryEntity) setFieldDefaultEntityBase.setDefaultFieldBaseUpdate(entryEntity);
        return entity;
    }

    @Override
    public EntryEntity setDefaultFieldBaseDelete(EntryEntity entryEntity) throws RuntimeException {
        EntryEntity entity = (EntryEntity) setFieldDefaultEntityBase.setDefaultFieldBaseDelete(entryEntity);
        return entity;
    }

    @Override
    public EntryDomain setDefaultFieldBaseUpdated(EntryDomain domainTarget, EntryDomain domainSource) throws RuntimeException {
        EntryDomain domain = (EntryDomain) setFieldDefaultEntityBase.setDefaultFieldBaseUpdated(domainTarget, domainSource);
        return domain;
    }
}
