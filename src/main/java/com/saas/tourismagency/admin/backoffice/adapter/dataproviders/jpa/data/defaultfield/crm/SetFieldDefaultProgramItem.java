package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.defaultfield.crm;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.crm.ProgramItemEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.core.SetFieldDefaultEntityBase;
import com.saas.tourismagency.admin.backoffice.domain.crm.ProgramItemDomain;
import dev.mm.javaee.core.repo.SetFieldDefaultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SetFieldDefaultProgramItem implements SetFieldDefaultEntity<ProgramItemEntity, ProgramItemDomain> {

    @Autowired
    private SetFieldDefaultEntityBase setFieldDefaultEntityBase;

    @Override
    public ProgramItemEntity setDefaultFieldBaseCreate(ProgramItemEntity programItemEntity) throws RuntimeException {
        ProgramItemEntity entity = (ProgramItemEntity) setFieldDefaultEntityBase.setDefaultFieldBaseCreate(programItemEntity);
        return entity;
    }

    @Override
    public ProgramItemEntity setDefaultFieldBaseUpdate(ProgramItemEntity programItemEntity) throws RuntimeException {
        ProgramItemEntity entity = (ProgramItemEntity) setFieldDefaultEntityBase.setDefaultFieldBaseUpdate(programItemEntity);
        return entity;
    }

    @Override
    public ProgramItemEntity setDefaultFieldBaseDelete(ProgramItemEntity programItemEntity) throws RuntimeException {
        ProgramItemEntity entity = (ProgramItemEntity) setFieldDefaultEntityBase.setDefaultFieldBaseDelete(programItemEntity);
        return entity;
    }

    @Override
    public ProgramItemDomain setDefaultFieldBaseUpdated(ProgramItemDomain domainTarget, ProgramItemDomain domainSource) throws RuntimeException {
        ProgramItemDomain domain = (ProgramItemDomain) setFieldDefaultEntityBase.setDefaultFieldBaseUpdated(domainTarget, domainSource);
        return domain;
    }
}
