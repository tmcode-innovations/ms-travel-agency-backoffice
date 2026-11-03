package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.defaultfield.maintenance;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.maintenance.SectorEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.core.SetFieldDefaultEntityBase;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.SectorDomain;
import dev.mm.javaee.core.repo.SetFieldDefaultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SetFieldDefaultSector implements SetFieldDefaultEntity<SectorEntity, SectorDomain> {

    @Autowired
    private SetFieldDefaultEntityBase setFieldDefaultEntityBase;

    @Override
    public SectorEntity setDefaultFieldBaseCreate(SectorEntity sectorEntity) throws RuntimeException {
        SectorEntity entity = (SectorEntity) setFieldDefaultEntityBase.setDefaultFieldBaseCreate(sectorEntity);
        return entity;
    }

    @Override
    public SectorEntity setDefaultFieldBaseUpdate(SectorEntity sectorEntity) throws RuntimeException {
        SectorEntity entity = (SectorEntity) setFieldDefaultEntityBase.setDefaultFieldBaseUpdate(sectorEntity);
        return entity;
    }

    @Override
    public SectorEntity setDefaultFieldBaseDelete(SectorEntity sectorEntity) throws RuntimeException {
        SectorEntity entity = (SectorEntity) setFieldDefaultEntityBase.setDefaultFieldBaseDelete(sectorEntity);
        return entity;
    }

    @Override
    public SectorDomain setDefaultFieldBaseUpdated(SectorDomain domainTarget, SectorDomain domainSource) throws RuntimeException {
        SectorDomain domain = (SectorDomain) setFieldDefaultEntityBase.setDefaultFieldBaseUpdated(domainTarget, domainSource);
        return domain;
    }
}
