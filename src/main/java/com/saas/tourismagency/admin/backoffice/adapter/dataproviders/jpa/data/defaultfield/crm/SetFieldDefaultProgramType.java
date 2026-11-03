package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.defaultfield.crm;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.crm.ProgramTypeEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.core.SetFieldDefaultEntityBase;
import com.saas.tourismagency.admin.backoffice.domain.crm.ProgramTypeDomain;
import dev.mm.javaee.core.repo.SetFieldDefaultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SetFieldDefaultProgramType implements SetFieldDefaultEntity<ProgramTypeEntity, ProgramTypeDomain> {

    @Autowired
    private SetFieldDefaultEntityBase setFieldDefaultEntityBase;

    @Override
    public ProgramTypeEntity setDefaultFieldBaseCreate(ProgramTypeEntity programTypeEntity) throws RuntimeException {
        ProgramTypeEntity entity = (ProgramTypeEntity) setFieldDefaultEntityBase.setDefaultFieldBaseCreate(programTypeEntity);
        return entity;
    }

    @Override
    public ProgramTypeEntity setDefaultFieldBaseUpdate(ProgramTypeEntity programTypeEntity) throws RuntimeException {
        ProgramTypeEntity entity = (ProgramTypeEntity) setFieldDefaultEntityBase.setDefaultFieldBaseUpdate(programTypeEntity);
        return entity;
    }

    @Override
    public ProgramTypeEntity setDefaultFieldBaseDelete(ProgramTypeEntity programTypeEntity) throws RuntimeException {
        ProgramTypeEntity entity = (ProgramTypeEntity) setFieldDefaultEntityBase.setDefaultFieldBaseDelete(programTypeEntity);
        return entity;
    }

    @Override
    public ProgramTypeDomain setDefaultFieldBaseUpdated(ProgramTypeDomain domainTarget, ProgramTypeDomain domainSource) throws RuntimeException {
        ProgramTypeDomain domain = (ProgramTypeDomain) setFieldDefaultEntityBase.setDefaultFieldBaseUpdated(domainTarget, domainSource);
        return domain;
    }
}
